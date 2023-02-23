package com.bank.example.listener;

import com.bank.example.model.CashBackCategory;
import com.bank.example.service.CashBackCategoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.PreRemove;

@Component
@AllArgsConstructor
@RequiredArgsConstructor

public class CashBackCategoryListener {

    private CashBackCategoryService cashBackCategoryService;

    @PreRemove
    public void preDelete(CashBackCategory cashBackCategory) {
        cashBackCategoryService.removeLinksAccount(cashBackCategory);
        cashBackCategoryService.removeLinksCompany(cashBackCategory);
    }

}
