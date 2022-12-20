package org.team3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.team3.dto.response.AllCompanyDtoResponse;
import org.team3.repository.entity.Company;

import java.util.List;

@Repository
public interface ICompanyRepository extends JpaRepository<Company,Long> {



}
