package com.example.jobnotification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class JobNotificationService {

    @Autowired
    private JobRepository jobRepository;

    // Add a new job notification
    public JobNotification addJobNotification(JobNotification job) {
        return jobRepository.save(job);
    }

    // Retrieve all job notifications
    public Iterable<JobNotification> getAllJobNotifications() {
        return jobRepository.findAll();
    }

    // Retrieve a specific job by ID
    public Optional<JobNotification> getJobById(Long jobId) {
        return jobRepository.findById(jobId);
    }
}
