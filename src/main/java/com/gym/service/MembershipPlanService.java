package com.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.model.MembershipPlan;
import com.gym.repository.MembershipPlanRepository;

@Service
public class MembershipPlanService {
    @Autowired
    private MembershipPlanRepository membershipPlanRepository;

    public List<MembershipPlan> getAllMembershipPlans() {
        return membershipPlanRepository.findAll();
    }

    public MembershipPlan getMembershipPlanById(Long id) {
        return membershipPlanRepository.findById(id).orElseThrow(() -> new RuntimeException("MembershipPlan not found"));
    }

    public MembershipPlan addMembershipPlan(MembershipPlan membershipPlan) {
        return membershipPlanRepository.save(membershipPlan);
    }

    public MembershipPlan updateMembershipPlan(Long id, MembershipPlan membershipPlanDetails) {
        MembershipPlan membershipPlan = membershipPlanRepository.findById(id).orElseThrow(() -> new RuntimeException("MembershipPlan not found"));
        membershipPlan.setName(membershipPlanDetails.getName());
        membershipPlan.setDescription(membershipPlanDetails.getDescription());
        membershipPlan.setPrice(membershipPlanDetails.getPrice());
        membershipPlan.setDurationMonths(membershipPlanDetails.getDurationMonths());
        membershipPlan.setBenefits(membershipPlanDetails.getBenefits());
        return membershipPlanRepository.save(membershipPlan);
    }

    public void deleteMembershipPlan(Long id) {
        membershipPlanRepository.deleteById(id);
    }
}
