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

import com.gym.model.Equipment;
import com.gym.service.EquipmentService;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @GetMapping
    public List<Equipment> getAllEquipments() {
        return equipmentService.getAllEquipments();
    }

    @GetMapping("/{id}")
    public Equipment getEquipmentById(@PathVariable Long id) {
        return equipmentService.getEquipmentById(id);
    }

    @PostMapping
    public Equipment addEquipment(@RequestBody Equipment equipment) {
        return equipmentService.addEquipment(equipment);
    }

    @PutMapping("/{id}")
    public Equipment updateEquipment(@PathVariable Long id, @RequestBody Equipment equipmentDetails) {
        return equipmentService.updateEquipment(id, equipmentDetails);
    }

    @DeleteMapping("/{id}")
    public String deleteEquipment(@PathVariable Long id) {
        equipmentService.deleteEquipment(id);
        return "Equipment deleted successfully";
    }
}
