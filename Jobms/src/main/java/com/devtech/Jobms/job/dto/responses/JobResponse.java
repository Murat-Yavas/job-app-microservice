package com.devtech.Jobms.job.dto.responses;

import com.devtech.Jobms.job.entities.Job;
import com.devtech.Jobms.job.external.Company;
import lombok.Data;

@Data
public class JobResponse {

    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Company company;

    public JobResponse(Job job, Company company) {
        this.id = job.getId();
        this.title = job.getTitle();
        this.description = job.getDescription();
        this.minSalary = job.getMinSalary();
        this.maxSalary = job.getMaxSalary();
        this.location = job.getLocation();
        this.company = company;
    }
}
