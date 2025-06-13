package iteam.platform.freelancer.controllers;


import iteam.platform.freelancer.dtos.CompanyDto;
import iteam.platform.freelancer.dtos.JobApplicationsStatusUpdateDto;
import iteam.platform.freelancer.entities.Company;
import iteam.platform.freelancer.entities.JobApplications;
import iteam.platform.freelancer.entities.Postjob;
import iteam.platform.freelancer.repositories.CompanyRepository;
import iteam.platform.freelancer.repositories.JobApplicationsRepository;
import iteam.platform.freelancer.repositories.PostJobRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/company")
public class CompanyController {

    private final CompanyRepository companyRepository;
    private final PostJobRepository postjobRepository;
    private final JobApplicationsRepository jobApplicationsRepository;



    public CompanyController(CompanyRepository companyRepository,
                              PostJobRepository postjobRepository,
                             JobApplicationsRepository jobApplicationsRepository) {
        this.postjobRepository = postjobRepository;
        this.companyRepository = companyRepository;
        this.jobApplicationsRepository = jobApplicationsRepository;

    }



//    @GetMapping("/register")
//    public String showRegistrationForm(Model model) {
//        model.addAttribute("company", new CompanyDto());
//        return "register";
//    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model, HttpServletRequest request) {
        model.addAttribute("company", new CompanyDto());
        model.addAttribute("currentUri", request.getRequestURI());
        return "register";
    }


    @PostMapping("/register")
    public String registerCompany(@ModelAttribute("company") @Valid CompanyDto companyDto,
                                  BindingResult result,
                                  Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        if (!companyDto.getPassword().equals(companyDto.getCpassword())) {
            model.addAttribute("passwordError", "Passwords do not match");
            return "register";
        }

        if (companyRepository.existsByEmail(companyDto.getEmail())) {
            model.addAttribute("emailError", "Email is already registered");
            return "register";
        }

        Company company = new Company();
        BeanUtils.copyProperties(companyDto, company);

        System.out.println("Saving company with email: " + company.getEmail());

        companyRepository.save(company);


        //return "redirect:/company/register?success";
        return "redirect:/login";
    }






    @GetMapping("/postjob")
    public String showPostJobForm(Model model) {
        model.addAttribute("postjob", new Postjob());
        return "postjob"; // Thymeleaf template name
    }

    @PostMapping("/postjob")
    public String addPostJob(@ModelAttribute("postjob") @Valid Postjob postjob,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            return "postjob";
        }

        postjobRepository.save(postjob);

        // Redirect or show success message
        return "redirect:/company/postjob?success";
    }


    @GetMapping("/condidatesapplications")
    public String getMyApplications(@RequestParam("name") String name, Model model) {
        List<JobApplications> applications = jobApplicationsRepository.findByCompanyname(name);
        model.addAttribute("applications", applications);
        return "condidate-application"; // Thymeleaf template to show applications
    }

    @PostMapping("/updateApplicationStatus")
    public String updateApplicationStatus(@Valid JobApplicationsStatusUpdateDto dto,
                                          BindingResult result,
                                          RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid data submitted. Please check and try again.");
            return "redirect:/company/condidatesapplications?error";
        }

        boolean allFound = true;

        for (JobApplicationsStatusUpdateDto.JobApplicationStatusDto appDto : dto.getApplications()) {
            try {
                UUID id = UUID.fromString(appDto.getId());

                Optional<JobApplications> optionalApp = jobApplicationsRepository.findById(id);
                if (optionalApp.isPresent()) {
                    JobApplications app = optionalApp.get();
                    app.setStatus(appDto.getStatus());
                    jobApplicationsRepository.save(app);

                } else {
                    allFound = false;
                }
            } catch (IllegalArgumentException e) {
                allFound = false;
            }
        }

        if (allFound) {
            redirectAttributes.addFlashAttribute("successMessage", "All application statuses updated successfully.");
            return "redirect:/dashboardc";
        }
        return "dashboard";
    }







}