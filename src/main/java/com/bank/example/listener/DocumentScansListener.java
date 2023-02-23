package com.bank.example.listener;

import com.bank.example.model.Department;
import com.bank.example.model.DocumentScans;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class DocumentScansListener {
    @PrePersist
    public void prePersist(DocumentScans documentScans) {
        System.out.println(documentScans.toString());

        if ((documentScans.getInsuranceNumber() == null || documentScans.getInsuranceNumber().isEmpty() || documentScans.getInsuranceNumber().isBlank()) && ( documentScans.getITN() == null || documentScans.getITN().isEmpty() || documentScans.getITN().isBlank())) {
            throw new RuntimeException("ошибка");
        }

        System.out.println();
    }

    @PreUpdate
    public void preUpdate(DocumentScans documentScans) {
        System.out.println();
        if ((documentScans.getInsuranceNumber() == null || documentScans.getInsuranceNumber().isEmpty() || documentScans.getInsuranceNumber().isBlank()) && ( documentScans.getITN() == null || documentScans.getITN().isEmpty() || documentScans.getITN().isBlank())) {
            throw new RuntimeException("ошибка");
        }
        System.out.println();
    }

}
