package com.devtech.Jobms.job.services.concretes;

import com.devtech.Jobms.job.clients.CompanyClient;
import com.devtech.Jobms.job.dto.responses.JobResponse;
import com.devtech.Jobms.job.entities.Job;
import com.devtech.Jobms.job.exceptions.JobNotFoundException;
import com.devtech.Jobms.job.external.Company;
import com.devtech.Jobms.job.repositories.JobRepository;
import com.devtech.Jobms.job.services.abstracts.JobService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobManager implements JobService {

    private JobRepository jobRepository;
    private CompanyClient companyClient;

    @Override
    @CircuitBreaker(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    public List<JobResponse> getAllJobsWithParam(Optional<Long> companyId) {
        List<Job> jobs;

        if(companyId.isPresent()) {
            jobs = jobRepository.findJobByCompanyId(companyId.get());
            return jobs.stream().map(job -> {
                Company company = companyClient.getCompany(companyId.get());
                return new JobResponse(job, company);
            }).collect(Collectors.toList());
        } else {
            jobs = jobRepository.findAll();
            return jobs.stream().map(job -> {
                Company company = companyClient.getCompany(job.getCompanyId());
                return new JobResponse(job, company);
            }).collect(Collectors.toList());
        }
    }

    public List<String> companyBreakerFallback(Exception e) {
        List<String> list = new ArrayList<>();
        list.add("Error!");
        return list;
    }

    @Override
    public JobResponse getOneJob(Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new JobNotFoundException("The job with id: " + jobId + " not found!"));
        Company company = companyClient.getCompany(job.getCompanyId());

        return new JobResponse(job,company);
    }

    @Override
    public Job createOneJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job updateOneJob(Long jobId, Job job) {
        Optional<Job> foundJob = jobRepository.findById(jobId);
        if(foundJob.isEmpty()) throw new JobNotFoundException("Job with id: " + jobId + " does not exist");
        else {
            Job jobToUpdate = foundJob.get();
            jobToUpdate.setTitle(job.getTitle());
            jobToUpdate.setDescription(job.getDescription());
            jobToUpdate.setMinSalary(job.getMinSalary());
            jobToUpdate.setMaxSalary(job.getMaxSalary());
            jobToUpdate.setLocation(job.getLocation());
            jobToUpdate.setCompanyId(job.getCompanyId());
            return jobRepository.save(jobToUpdate);
        }
    }

    @Override
    public boolean deleteOneJob(Long jobId) {
        Job job = jobRepository.findById(jobId).orElse(null);
        if(job == null) return false;
        else{
            jobRepository.deleteById(jobId);
            return true;
        }
    }
}
