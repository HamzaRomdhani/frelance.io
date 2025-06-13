package iteam.platform.freelancer.services;
import iteam.platform.freelancer.dtos.ShowJob;
import iteam.platform.freelancer.repositories.PostJobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JobService {

    private final PostJobRepository postjobRepository;

    public JobService(PostJobRepository postjobRepository) {
        this.postjobRepository = postjobRepository;
    }

    public List<ShowJob> getRandomJobs() {
        List<Object[]> results = postjobRepository.findRandomJobsWithCompanyInfoNative();

        return results.stream().map(row -> new ShowJob(
                (UUID) row[0],
                (String) row[1],
                (String) row[2],
                (String) row[3],
                (String) row[4],
                (String) row[5],
                (String) row[6],
                (String) row[7],
                (String) row[8],
                (String) row[9]
        )).collect(Collectors.toList());
    }


    public ShowJob getJobById(UUID id) {
        // You can write a native query or JPQL to fetch job + company info by job id
        // For example, a native query similar to your previous one but with WHERE p.id = :id

        List<Object[]> result = postjobRepository.findJobWithCompanyById(id);
        if (result.isEmpty()) {
            return null;
        }
        Object[] row = result.get(0);
        return new ShowJob(
                (UUID) row[0],
                (String) row[1],
                (String) row[2],
                (String) row[3],
                (String) row[4],
                (String) row[5],
                (String) row[6],
                (String) row[7],
                (String) row[8],
                (String) row[9]
        );
    }










}
