package com.gym.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.model.Trainer;

@RestController
@RequestMapping("/trainers")
public class TrainerController {
    private List<Trainer> trainers = new ArrayList<>();
    private AtomicLong idCounter = new AtomicLong(0);

    @GetMapping
    public List<Trainer> getAllTrainers() {
        return trainers;
    }

    @PostMapping
    public Trainer addTrainer(@RequestBody Trainer trainer) {
        trainer.setId(idCounter.incrementAndGet());
        trainers.add(trainer);
        return trainer;
    }

    @PutMapping("/{id}")
    public Trainer updateTrainer(@PathVariable Long id, @RequestBody Trainer trainerDetails) {
        for (Trainer trainer : trainers) {
            if (trainer.getId().equals(id)) {
                trainer.setName(trainerDetails.getName());
                trainer.setSpecialization(trainerDetails.getSpecialization());
                return trainer;
            }
        }
        throw new RuntimeException("Trainer not found");
    }

    @DeleteMapping("/{id}")
    public String deleteTrainer(@PathVariable Long id) {
        Iterator<Trainer> iterator = trainers.iterator();
        while (iterator.hasNext()) {
            Trainer trainer = iterator.next();
            if (trainer.getId().equals(id)) {
                iterator.remove();
                return "Trainer deleted successfully";
            }
        }
        return "Trainer not found";
    }
}
