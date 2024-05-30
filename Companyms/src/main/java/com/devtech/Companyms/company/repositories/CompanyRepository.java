package com.devtech.Companyms.company.repositories;

import com.devtech.Companyms.company.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
