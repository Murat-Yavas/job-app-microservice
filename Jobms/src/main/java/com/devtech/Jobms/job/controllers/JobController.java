package com.devtech.Jobms.job.controllers;

import com.devtech.Jobms.job.dto.responses.JobResponse;
import com.devtech.Jobms.job.entities.Job;
import com.devtech.Jobms.job.services.abstracts.JobService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jobs")
@AllArgsConstructor
public class JobController {

    private JobService jobService;

    @GetMapping
    public ResponseEntity<List<JobResponse>> receiveJobsWithParam(@RequestParam Optional<Long> companyId) {
        return new ResponseEntity<>(jobService.getAllJobsWithParam(companyId), HttpStatus.OK);
    }


    @GetMapping("/{jobId}")
    public ResponseEntity<JobResponse> receiveOneJob(@PathVariable Long jobId) {
        return new ResponseEntity<>(jobService.getOneJob(jobId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Job> addOneJob(@RequestBody Job job) {
        return new ResponseEntity<>(jobService.createOneJob(job), HttpStatus.CREATED);
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<Job> editOneJob(@PathVariable Long jobId, @RequestBody Job job) {
        return new ResponseEntity<>(jobService.updateOneJob(jobId,job), HttpStatus.OK);
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<String> deleteOneJob(@PathVariable Long jobId) {
        boolean isJobDeleted = jobService.deleteOneJob(jobId);
        if(isJobDeleted)
            return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
        else return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
    }
}
