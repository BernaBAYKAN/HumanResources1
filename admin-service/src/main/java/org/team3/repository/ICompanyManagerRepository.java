package org.team3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.team3.repository.entity.CompanyManager;

@Repository
public interface ICompanyManagerRepository extends JpaRepository<CompanyManager,Long> {
}
