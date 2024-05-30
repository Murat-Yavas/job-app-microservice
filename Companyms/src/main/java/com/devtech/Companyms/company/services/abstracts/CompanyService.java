package com.devtech.Companyms.company.services.abstracts;

import com.devtech.Companyms.company.entities.Company;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();
    Company getOneCompany(Long id);
    Company createOneCompany(Company company);
    Company updateOneCompany(Long companyId, Company company);
    boolean deleteOneCompany(Long companyId);
}
