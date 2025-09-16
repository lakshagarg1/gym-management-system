package com.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.model.HealthMetric;

@Repository
public interface HealthMetricRepository extends JpaRepository<HealthMetric, Long> {
}
