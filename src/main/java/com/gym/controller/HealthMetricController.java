package com.gym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.model.HealthMetric;
import com.gym.service.HealthMetricService;

@RestController
@RequestMapping("/health-metrics")
public class HealthMetricController {
    @Autowired
    private HealthMetricService healthMetricService;

    @GetMapping
    public List<HealthMetric> getAllHealthMetrics() {
        return healthMetricService.getAllHealthMetrics();
    }

    @GetMapping("/{id}")
    public HealthMetric getHealthMetricById(@PathVariable Long id) {
        return healthMetricService.getHealthMetricById(id);
    }

    @PostMapping
    public HealthMetric addHealthMetric(@RequestBody HealthMetric healthMetric) {
        return healthMetricService.addHealthMetric(healthMetric);
    }

    @PutMapping("/{id}")
    public HealthMetric updateHealthMetric(@PathVariable Long id, @RequestBody HealthMetric healthMetricDetails) {
        return healthMetricService.updateHealthMetric(id, healthMetricDetails);
    }

    @DeleteMapping("/{id}")
    public String deleteHealthMetric(@PathVariable Long id) {
        healthMetricService.deleteHealthMetric(id);
        return "Health metric deleted successfully";
    }
}
