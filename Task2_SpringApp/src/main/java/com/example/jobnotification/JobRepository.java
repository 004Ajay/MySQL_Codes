package com.example.jobnotification;

import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<JobNotification, Long> {
}
