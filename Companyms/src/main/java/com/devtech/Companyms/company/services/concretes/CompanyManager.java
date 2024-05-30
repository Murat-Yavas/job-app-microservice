package com.devtech.Companyms.company.services.concretes;

import com.devtech.Companyms.company.entities.Company;
import com.devtech.Companyms.company.exceptions.CompanyNotFoundException;
import com.devtech.Companyms.company.repositories.CompanyRepository;
import com.devtech.Companyms.company.services.abstracts.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyManager implements CompanyService {

    private CompanyRepository companyRepository;

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getOneCompany(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("The company with id: " + id + " not found!"));
    }

    @Override
    public Company createOneCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company updateOneCompany(Long companyId, Company company) {
        Optional<Company> companyOptional = companyRepository.findById(companyId);

        if(companyOptional.isPresent()) {
            Company companyToUpdate = companyOptional.get();
            companyToUpdate.setDescription(company.getDescription());
            companyToUpdate.setName(company.getName());
            return companyRepository.save(companyToUpdate);
        } else throw new CompanyNotFoundException("The company with id: " + companyId + " not found!");
    }

    @Override
    public boolean deleteOneCompany(Long companyId) {
        Optional<Company> company = companyRepository.findById(companyId);
        if(company.isPresent()) {
            companyRepository.deleteById(companyId);
            return true;
        } else return false;
    }
}
