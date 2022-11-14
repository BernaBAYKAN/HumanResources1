package org.team3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.team3.repository.entity.Admin;
import org.team3.repository.entity.CompanyManager;

import java.util.Optional;

@Repository
public interface ICompanyManagerRepository extends JpaRepository<CompanyManager,Long> {
    Optional<CompanyManager> findOptionalByUsernameIgnoreCaseAndPassword(String username, String password);
}
