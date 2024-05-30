package com.devtech.Jobms.job.services.abstracts;

import com.devtech.Jobms.job.dto.responses.JobResponse;
import com.devtech.Jobms.job.entities.Job;

import java.util.List;
import java.util.Optional;

public interface JobService {

    List<JobResponse> getAllJobsWithParam(Optional<Long> companyId);
    JobResponse getOneJob(Long jobId);
    Job createOneJob(Job job);
    Job updateOneJob(Long jobId, Job job);
    boolean deleteOneJob(Long jobId);
}
