package iteam.platform.freelancer.controllers;


import iteam.platform.freelancer.dtos.CompanyDto;
import iteam.platform.freelancer.dtos.JobApplicationsStatusUpdateDto;
import iteam.platform.freelancer.entities.Company;
import iteam.platform.freelancer.entities.JobApplications;
import iteam.platform.freelancer.entities.Postjob;
import iteam.platform.freelancer.helper.LoggedInUser;
import iteam.platform.freelancer.services.company.CompanyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model, HttpServletRequest request) {
        model.addAttribute("company", new CompanyDto());
        model.addAttribute("currentUri", request.getRequestURI());
        return "register";
    }

    @PostMapping("/register")
    public String registerCompany(@ModelAttribute("company") @Valid CompanyDto companyDto,
                                  BindingResult result,
                                  Model model,
                                  @RequestParam("profilec") MultipartFile profilec,
                                  HttpServletRequest request) {

        model.addAttribute("currentUri", request.getRequestURI());

        if (result.hasErrors()) {
            return "register";
        }

        if (!companyDto.getPassword().equals(companyDto.getCpassword())) {
            model.addAttribute("passwordError", "Passwords do not match");
            return "register";
        }

        if (companyService.existsByEmail(companyDto.getEmail())) {
            model.addAttribute("emailError", "Email is already registered");
            return "register";
        }

        if (companyDto.getProfilec().isEmpty()) {
            model.addAttribute("imageError", "Please select an image");
            return "register";
        }

        if (!Arrays.asList("image/jpeg", "image/png", "image/gif").contains(companyDto.getProfilec().getContentType())) {
            model.addAttribute("imageError", "Only JPG, PNG, and GIF files are allowed");
            return "register";
        }

        if (companyDto.getProfilec().getSize() > 5 * 1024 * 1024) {
            model.addAttribute("imageError", "File must be less than 5MB");
            return "register";
        }
        try {
            String imagePath = companyService.saveImage(companyDto.getProfilec());
            companyDto.setProfilecPath(imagePath);
        } catch (IOException e) {
            model.addAttribute("imageError", "Failed to upload profile picture");
            return "register";
        }

        Company company = new Company();

        company.setName(companyDto.getName());
        company.setEmail(companyDto.getEmail());
        company.setPassword(companyDto.getPassword());
        company.setNumber(companyDto.getNumber());
        company.setWebsite(companyDto.getWebsite());
        company.setAbout(companyDto.getAbout());
        company.setProfilec(companyDto.getProfilecPath());


        companyService.saveCompany(company);

        return "redirect:/login";
    }

    @GetMapping("/viewprofilec")
    public String getMyProfilec(@RequestParam("email") String email, Model model) {
        Company company = companyService.findByEmail(email);
        if (company == null) {
            return "redirect:/login";
        }

        model.addAttribute("company", company);
        return "profilec";
    }


    @PostMapping("/updateProfile")
    public String updateProfile(
            @RequestParam("name") String name,
            @RequestParam("website") String website,
            @RequestParam("about") String about,
            @RequestParam("profilec") MultipartFile profilec,
            @RequestParam("email") String email,
            HttpSession session,
            RedirectAttributes redirectAttrs) {

        LoggedInUser user = (LoggedInUser) session.getAttribute("user");
        if (user == null || !user.getRole().equals("company")) {
            return "redirect:/login";
        }

        try {
            Company company = companyService.findByEmail(email);

            company.setName(name);
            company.setWebsite(website);
            company.setAbout(about);

            if (!profilec.isEmpty()) {
                String imagePath = companyService.saveImage(profilec);
                company.setProfilec(imagePath);
            }

            companyService.saveCompany(company);

            redirectAttrs.addFlashAttribute("successMessage", "✅ Profile updated successfully!");

        } catch (IOException e) {
            redirectAttrs.addFlashAttribute("errorMessage", "❌ Failed to upload image.");
            return "redirect:/profilec";
        }

        return "redirect:/dashboard";
    }


    @GetMapping("/postjob")
    public String showPostJobForm(Model model) {
        model.addAttribute("postjob", new Postjob());
        return "postjob";
    }

    @PostMapping("/postjob")
    public String addPostJob(@ModelAttribute("postjob") @Valid Postjob postjob,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            return "postjob";
        }

        companyService.saveJob(postjob);

        return "redirect:/company/postjob?success";
    }


    @GetMapping("/condidatesapplications")
    public String getMyApplications(@RequestParam("name") String name, Model model) {
        List<JobApplications> applications = companyService.findByCompanyname(name);
        model.addAttribute("applications", applications);
        return "condidate-application";
    }

    @PostMapping("/updateApplicationStatus")
    public String updateApplicationStatus(@Valid JobApplicationsStatusUpdateDto dto,
                                          BindingResult result,
                                          RedirectAttributes redirectAttributes,
                                          HttpSession session) {

        LoggedInUser user = (LoggedInUser) session.getAttribute("user");
        if (user == null || !user.getRole().equals("company")) {
            return "redirect:/login";
        }

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid data submitted. Please check and try again.");
            return "redirect:/company/condidatesapplications?error";
        }

        boolean allFound = true;

        for (JobApplicationsStatusUpdateDto.JobApplicationStatusDto appDto : dto.getApplications()) {
            try {
                UUID id = UUID.fromString(appDto.getId());

                Optional<JobApplications> optionalApp = companyService.findById(id);
                if (optionalApp.isPresent()) {
                    JobApplications app = optionalApp.get();
                    app.setStatus(appDto.getStatus());
                    companyService.saveJobApplications(app);

                } else {
                    allFound = false;
                }
            } catch (IllegalArgumentException e) {
                allFound = false;
            }
        }

        if (allFound) {
            redirectAttributes.addFlashAttribute("successMessage", "✅ Status was updated successfully.");
            return "redirect:/dashboard";
        }
        redirectAttributes.addFlashAttribute("successMessage", "✅ Status was updated successfully.");

        return "redirect:/dashboard";
    }


}