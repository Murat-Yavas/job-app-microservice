package com.devtech.Jobms.job.repositories;

import com.devtech.Jobms.job.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findJobByCompanyId(Long companyId);
}
