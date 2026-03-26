package com.example.complaint_management.controller;

import com.example.complaint_management.model.Complaint;
import com.example.complaint_management.service.ComplaintService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    // GET all complaints
    @GetMapping
    public List<Complaint> getAllComplaints() {
        return complaintService.getAllComplaints();
    }

    // GET one complaint by ID
    @GetMapping("/{id}")
    public Complaint getComplaintById(@PathVariable Long id) {
        return complaintService.getComplaintById(id);
    }

    // CREATE new complaint
    @PostMapping
    public ResponseEntity<Complaint> createComplaint(@Valid @RequestBody Complaint complaint) {
        Complaint saved = complaintService.createComplaint(complaint);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // UPDATE full complaint
    @PutMapping("/{id}")
    public Complaint updateComplaint(@PathVariable Long id,
                                     @Valid @RequestBody Complaint complaint) {
        return complaintService.updateComplaint(id, complaint);
    }

    // UPDATE status only
    @PatchMapping("/{id}/status")
    public Complaint updateComplaintStatus(@PathVariable Long id,
                                           @RequestParam String status) {
        return complaintService.updateComplaintStatus(id, status);
    }

    // DELETE complaint
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComplaint(@PathVariable Long id) {
        complaintService.deleteComplaint(id);
        return ResponseEntity.noContent().build();
    }
}
