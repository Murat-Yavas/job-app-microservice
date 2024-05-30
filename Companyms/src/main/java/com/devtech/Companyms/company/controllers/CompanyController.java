package com.devtech.Companyms.company.controllers;

import com.devtech.Companyms.company.entities.Company;
import com.devtech.Companyms.company.services.abstracts.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
@AllArgsConstructor
public class CompanyController {

    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> receiveAllCompanies() {
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<Company> receiveOneCompany(@PathVariable Long companyId) {
        return new ResponseEntity<>(companyService.getOneCompany(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Company> addOneCompany(@RequestBody Company company) {
        return new ResponseEntity<>(companyService.createOneCompany(company), HttpStatus.CREATED);
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<Company> editOneCompany(@PathVariable Long companyId, @RequestBody Company company) {
        return new ResponseEntity<>(companyService.updateOneCompany(companyId,company), HttpStatus.OK);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<String> deleteOneCompany(@PathVariable Long companyId) {
        boolean isCompanyDeleted = companyService.deleteOneCompany(companyId);
        if(isCompanyDeleted)
            return new ResponseEntity<>("Company deleted successfully", HttpStatus.OK);
        else return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
    }
}
