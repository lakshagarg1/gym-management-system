package com.gym.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.model.Schedule;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    private List<Schedule> schedules = new ArrayList<>();

    @GetMapping
    public List<Schedule> getAllSchedules() {
        return schedules;
    }

    @PostMapping
    public Schedule addSchedule(@RequestBody Schedule schedule) {
        schedules.add(schedule);
        return schedule;
    }

    @PutMapping("/{id}")
    public Schedule updateSchedule(@PathVariable Long id, @RequestBody Schedule scheduleDetails) {
        for (Schedule schedule : schedules) {
            if (schedule.getId().equals(id)) {
                schedule.setTrainerId(scheduleDetails.getTrainerId());
                schedule.setStartTime(scheduleDetails.getStartTime());
                schedule.setEndTime(scheduleDetails.getEndTime());
                return schedule;
            }
        }
        throw new RuntimeException("Schedule not found");
    }

    @DeleteMapping("/{id}")
    public String deleteSchedule(@PathVariable Long id) {
        Iterator<Schedule> iterator = schedules.iterator();
        while (iterator.hasNext()) {
            Schedule schedule = iterator.next();
            if (schedule.getId().equals(id)) {
                iterator.remove();
                return "Schedule deleted successfully";
            }
        }
        return "Schedule not found";
    }
}
