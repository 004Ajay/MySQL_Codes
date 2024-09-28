package com.example.jobnotification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/jobs")
public class JobNotificationController {

    @Autowired
    private JobNotificationService jobNotificationService;

    // POST: Create a new job notification
    @PostMapping
    public JobNotification createJobNotification(@RequestBody JobNotification job) {
        return jobNotificationService.addJobNotification(job);
    }

    // GET: Retrieve all job notifications
    @GetMapping
    public Iterable<JobNotification> getAllJobNotifications() {
        return jobNotificationService.getAllJobNotifications();
    }

    // GET: Retrieve a specific job by ID
    @GetMapping("/{id}")
    public Optional<JobNotification> getJobById(@PathVariable Long id) {
        return jobNotificationService.getJobById(id);
    }
}
