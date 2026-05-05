package com.springcrud1.controller;

import com.springcrud1.DTO.ApplyLeaveDTO;
import com.springcrud1.entity.User;
import com.springcrud1.repository.UserRepository;
import com.springcrud1.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private UserRepository userRepository;

    // 🔥 get employeeId from JWT
    private Long getEmployeeId() {
        String username = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getId();
    }

    // APPLY
    @PostMapping("/apply")
    public ResponseEntity<?> apply(@RequestBody ApplyLeaveDTO dto) {
        return ResponseEntity.ok(
                leaveService.applyLeave(dto, getEmployeeId())
        );
    }

    // APPROVE
    @PutMapping("/{id}/approve")
    public ResponseEntity<?> approve(@PathVariable Long id) {
        return ResponseEntity.ok(leaveService.approveLeave(id));
    }

    // REJECT
    @PutMapping("/{id}/reject")
    public ResponseEntity<?> reject(@PathVariable Long id) {
        return ResponseEntity.ok(leaveService.rejectLeave(id));
    }

    // MY LEAVES
    @GetMapping("/my")
    public ResponseEntity<?> myLeaves() {
        return ResponseEntity.ok(
                leaveService.getMyLeaves(getEmployeeId())
        );
    }
}