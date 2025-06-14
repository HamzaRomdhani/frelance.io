package iteam.platform.freelancer.services.company;

import iteam.platform.freelancer.entities.Company;
import iteam.platform.freelancer.entities.JobApplications;
import iteam.platform.freelancer.entities.Postjob;
import iteam.platform.freelancer.repositories.CompanyRepository;
import iteam.platform.freelancer.repositories.JobApplicationsRepository;
import iteam.platform.freelancer.repositories.PostJobRepository;
import iteam.platform.freelancer.utils.FileUploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;
    private final PostJobRepository postjobRepository;
    private final JobApplicationsRepository jobApplicationsRepository;
    private final FileUploadUtil fileUploadUtil;

    public CompanyServiceImpl(CompanyRepository companyRepository,
                              PostJobRepository postjobRepository,
                              JobApplicationsRepository jobApplicationsRepository,
                              FileUploadUtil fileUploadUtil) {
        this.postjobRepository = postjobRepository;
        this.companyRepository = companyRepository;
        this.jobApplicationsRepository = jobApplicationsRepository;
        this.fileUploadUtil = fileUploadUtil;
    }

    @Override
    public boolean existsByEmail(String email) {
      return  companyRepository.existsByEmail(email);
    }

    @Override
    public Company findByEmail(String email) {
        return  companyRepository.findByEmail(email);
    }

    @Override
    public Company saveCompany(Company company) {
        return  companyRepository.save(company);
    }

    @Override
    public Postjob saveJob(Postjob postjob) {
        return postjobRepository.save(postjob);
    }

    @Override
    public JobApplications saveJobApplications(JobApplications jobapplications) {
        return jobApplicationsRepository.save(jobapplications);
    }

    @Override
    public Optional<JobApplications> findById(UUID id) {
       return jobApplicationsRepository.findById(id);
    }

    @Override
    public List<JobApplications> findByCompanyname(String name) {
        return jobApplicationsRepository.findByCompanyname(name);
    }

    @Override
    public String saveImage(MultipartFile profilec) throws IOException {
        return fileUploadUtil.saveImage(profilec);
    }
}
