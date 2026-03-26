package com.example.complaint_management.service;


import com.example.complaint_management.exception.ComplaintNotFoundException;
import com.example.complaint_management.model.Complaint;
import com.example.complaint_management.repository.ComplaintRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    public Complaint getComplaintById(Long id) {
        return complaintRepository.findById(id)
                .orElseThrow(() -> new ComplaintNotFoundException(id));
    }

    public Complaint createComplaint(Complaint complaint) {
        // Status will be set to PENDING in entity if blank
        return complaintRepository.save(complaint);
    }

    public Complaint updateComplaint(Long id, Complaint updated) {
        Complaint existing = getComplaintById(id);

        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setCategory(updated.getCategory());
        existing.setDescription(updated.getDescription());
        existing.setStatus(updated.getStatus());

        return complaintRepository.save(existing);
    }

    public Complaint updateComplaintStatus(Long id, String status) {
        Complaint existing = getComplaintById(id);
        existing.setStatus(status);
        return complaintRepository.save(existing);
    }

    public void deleteComplaint(Long id) {
        Complaint existing = getComplaintById(id);
        complaintRepository.delete(existing);
    }
}
