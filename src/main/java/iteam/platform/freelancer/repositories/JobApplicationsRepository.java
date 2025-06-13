package iteam.platform.freelancer.repositories;

import iteam.platform.freelancer.entities.JobApplications;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JobApplicationsRepository extends JpaRepository<JobApplications, UUID> {

    List<JobApplications> findByCandidateemail(String candidateemail);

    List<JobApplications> findByCompanyname(String companyname);


}
