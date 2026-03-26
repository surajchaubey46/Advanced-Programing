package com.example.complaint_management.exception;


public class ComplaintNotFoundException extends RuntimeException {

    public ComplaintNotFoundException(Long id) {
        super("Complaint not found with id: " + id);
    }
}
