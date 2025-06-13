package iteam.platform.freelancer.repositories;

import iteam.platform.freelancer.entities.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FreelancerRepository extends JpaRepository<Freelancer, UUID> {
    boolean existsByEmail(String email);
    Freelancer findByEmail(String email);
}
