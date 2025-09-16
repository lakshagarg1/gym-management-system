package com.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.model.HealthMetric;
import com.gym.repository.HealthMetricRepository;

@Service
public class HealthMetricService {
    @Autowired
    private HealthMetricRepository healthMetricRepository;

    public List<HealthMetric> getAllHealthMetrics() {
        return healthMetricRepository.findAll();
    }

    public HealthMetric getHealthMetricById(Long id) {
        return healthMetricRepository.findById(id).orElseThrow(() -> new RuntimeException("HealthMetric not found"));
    }

    public HealthMetric addHealthMetric(HealthMetric healthMetric) {
        return healthMetricRepository.save(healthMetric);
    }

    public HealthMetric updateHealthMetric(Long id, HealthMetric healthMetricDetails) {
        HealthMetric healthMetric = healthMetricRepository.findById(id).orElseThrow(() -> new RuntimeException("HealthMetric not found"));
        healthMetric.setMemberId(healthMetricDetails.getMemberId());
        healthMetric.setDate(healthMetricDetails.getDate());
        healthMetric.setWeight(healthMetricDetails.getWeight());
        healthMetric.setHeight(healthMetricDetails.getHeight());
        healthMetric.setBmi(healthMetricDetails.getBmi());
        healthMetric.setNotes(healthMetricDetails.getNotes());
        return healthMetricRepository.save(healthMetric);
    }

    public void deleteHealthMetric(Long id) {
        healthMetricRepository.deleteById(id);
    }
}
