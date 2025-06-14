package iteam.platform.freelancer.services.company;

import iteam.platform.freelancer.entities.Company;
import iteam.platform.freelancer.entities.JobApplications;
import iteam.platform.freelancer.entities.Postjob;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompanyService {

    boolean existsByEmail(String email);
    Company findByEmail(String email);
    Company saveCompany(Company company);
    Postjob saveJob(Postjob postjob);
    JobApplications saveJobApplications(JobApplications jobapplications);
    Optional<JobApplications> findById(UUID id);
    List<JobApplications> findByCompanyname(String name);
    String saveImage(MultipartFile profilec) throws IOException;
}
