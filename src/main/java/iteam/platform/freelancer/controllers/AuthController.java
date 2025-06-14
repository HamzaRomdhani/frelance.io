package iteam.platform.freelancer.controllers;

import iteam.platform.freelancer.entities.Company;
import iteam.platform.freelancer.entities.Freelancer;
import iteam.platform.freelancer.helper.LoggedInUser;
import iteam.platform.freelancer.repositories.CompanyRepository;
import iteam.platform.freelancer.repositories.FreelancerRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AuthController {

    private final FreelancerRepository freelancerRepo;
    private final CompanyRepository companyRepo;

    public AuthController(FreelancerRepository freelancerRepo, CompanyRepository companyRepo) {
        this.freelancerRepo = freelancerRepo;
        this.companyRepo = companyRepo;
    }

    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        Freelancer freelancer = freelancerRepo.findByEmail(email);
        if (freelancer != null && password.equals(freelancer.getPassword())) {
            session.setAttribute("user", new LoggedInUser(freelancer.getName(), email, "freelancer", freelancer.getProfilef()));
            return "redirect:/dashboard";
        }

        Company company = companyRepo.findByEmail(email);
        if (company != null && password.equals(company.getPassword())) {
            session.setAttribute("user", new LoggedInUser(company.getName(), email, "company", company.getProfilec()));
            return "redirect:/dashboard";
        }

        model.addAttribute("error", true);
        return "login";
    }


    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model,
                            @ModelAttribute("successMessage") String successMessage,
                            @ModelAttribute("errorMessage") String errorMessage) {

        LoggedInUser user = (LoggedInUser) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);

        if (user.getRole().equals("freelancer")) {
            return "dashboardf";
        } else {
            return "dashboardc";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}