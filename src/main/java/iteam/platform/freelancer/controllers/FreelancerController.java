package iteam.platform.freelancer.controllers;

import iteam.platform.freelancer.dtos.FreelancerDto;
import iteam.platform.freelancer.dtos.JobApplicationDto;
import iteam.platform.freelancer.dtos.ShowJob;
import iteam.platform.freelancer.entities.Freelancer;
import iteam.platform.freelancer.entities.JobApplications;
import iteam.platform.freelancer.repositories.FreelancerRepository;
import iteam.platform.freelancer.repositories.JobApplicationsRepository;
import iteam.platform.freelancer.services.JobService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/freelance")
public class FreelancerController {

    private final FreelancerRepository freelancerRepository;
    private final JobApplicationsRepository jobApplicationsRepository;
    private final JobService jobService;


    public FreelancerController(FreelancerRepository freelancerRepository,
                                JobService jobService,
                                JobApplicationsRepository jobApplicationsRepository) {
        this.freelancerRepository = freelancerRepository;
        this.jobService = jobService;
        this.jobApplicationsRepository = jobApplicationsRepository;
    }



    @GetMapping("/register")
    public String showRegistrationForm(Model model, HttpServletRequest request) {
        model.addAttribute("freelancer", new FreelancerDto());
        model.addAttribute("currentUri", request.getRequestURI());
        return "freelance-register";
    }

    @PostMapping("/register")
    public String registerFreelancer(@ModelAttribute("freelancer") @Valid FreelancerDto freelancerDto,
                                     BindingResult result,
                                     Model model) {
        if (result.hasErrors()) {
            return "freelance-register";
        }

        if (!freelancerDto.getPassword().equals(freelancerDto.getCpassword())) {
            model.addAttribute("passwordError", "Passwords do not match");
            return "freelance-register";
        }

        if (freelancerRepository.existsByEmail(freelancerDto.getEmail())) {
            model.addAttribute("emailError", "Email is already registered");
            return "freelance-register";
        }

        Freelancer freelancer = new Freelancer();
        BeanUtils.copyProperties(freelancerDto, freelancer, "cpassword");

        System.out.println("Saving freelancer with email: " + freelancer.getEmail());

        freelancerRepository.save(freelancer);

        return "redirect:/login";
    }





    @GetMapping("/explorejob")
    public String exploreJobs(Model model) {
        List<ShowJob> jobs = jobService.getRandomJobs();
        model.addAttribute("jobdataofcompany", jobs);
        return "explorejobs"; // your Thymeleaf template
    }

    @GetMapping("/job/view/{id}")
    public String viewJobDetails(@PathVariable("id") UUID id, Model model) {
        ShowJob job = jobService.getJobById(id);
        if (job == null) {
            return "redirect:/explorejobs?error=notfound";
        }
        model.addAttribute("job", job);
        return "job-details"; // Thymeleaf template for job details
    }




    // Show apply form pre-filled with job info
    @GetMapping("/apply/{id}")
    public String showApplyForm(@PathVariable("id") UUID jobId, Model model) {
        ShowJob job = jobService.getJobById(jobId);
        if (job == null) {
            return "redirect:/explorejobs?error=notfound";
        }

        JobApplicationDto applicationDto = new JobApplicationDto();
        applicationDto.setCompanyname(job.getJcname());
        applicationDto.setPosition(job.getJtittle());
        applicationDto.setCid(job.getId().toString());
        applicationDto.setCompanyemail(job.getJcemail());

        model.addAttribute("application", applicationDto);
        model.addAttribute("job", job);

        return "apply-job";
    }

    // Handle form submission
    @PostMapping("/apply")
    public String submitApplication(@ModelAttribute("application") @Valid JobApplicationDto applicationDto,
                                    BindingResult result,
                                    Model model) {
        if (result.hasErrors()) {
            // Re-populate job info for the form
            ShowJob job = jobService.getJobById(UUID.fromString(applicationDto.getCid()));
            model.addAttribute("job", job);
            return "apply-job";
        }

        // Map DTO to entity
        JobApplications application = new JobApplications();
        application.setCompanyname(applicationDto.getCompanyname());
        application.setPosition(applicationDto.getPosition());
        application.setCandidatename(applicationDto.getCandidatename());
        application.setCandidateemail(applicationDto.getCandidateemail());
        application.setCandidateresume(applicationDto.getCandidateresume());
        application.setCid(UUID.fromString(applicationDto.getCid()));
        application.setCompanyemail(applicationDto.getCompanyemail());
        application.setStatus("Pending");

        jobApplicationsRepository.save(application);

        return "redirect:/freelance/explorejob?Success";
    }


    @GetMapping("/myapplications")
    public String getMyApplications(@RequestParam("email") String email, Model model) {
        List<JobApplications> applications = jobApplicationsRepository.findByCandidateemail(email);
        model.addAttribute("applications", applications);
        return "my-applications"; // Thymeleaf template to show applications
    }


}
