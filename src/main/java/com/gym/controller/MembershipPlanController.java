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

import com.gym.model.MembershipPlan;
import com.gym.service.MembershipPlanService;

@RestController
@RequestMapping("/membership-plans")
public class MembershipPlanController {
    @Autowired
    private MembershipPlanService membershipPlanService;

    @GetMapping
    public List<MembershipPlan> getAllMembershipPlans() {
        return membershipPlanService.getAllMembershipPlans();
    }

    @GetMapping("/{id}")
    public MembershipPlan getMembershipPlanById(@PathVariable Long id) {
        return membershipPlanService.getMembershipPlanById(id);
    }

    @PostMapping
    public MembershipPlan addMembershipPlan(@RequestBody MembershipPlan membershipPlan) {
        return membershipPlanService.addMembershipPlan(membershipPlan);
    }

    @PutMapping("/{id}")
    public MembershipPlan updateMembershipPlan(@PathVariable Long id, @RequestBody MembershipPlan membershipPlanDetails) {
        return membershipPlanService.updateMembershipPlan(id, membershipPlanDetails);
    }

    @DeleteMapping("/{id}")
    public String deleteMembershipPlan(@PathVariable Long id) {
        membershipPlanService.deleteMembershipPlan(id);
        return "Membership plan deleted successfully";
    }
}
