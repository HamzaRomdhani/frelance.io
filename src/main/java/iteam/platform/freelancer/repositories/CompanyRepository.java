package iteam.platform.freelancer.repositories;

import iteam.platform.freelancer.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    boolean existsByEmail(String email);
    Company findByEmail(String email);
}