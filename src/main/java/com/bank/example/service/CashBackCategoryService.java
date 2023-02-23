package com.bank.example.service;

import com.bank.example.model.CashBackCategory;

public interface CashBackCategoryService extends GenericService<Long, CashBackCategory> {

    void removeLinksAccount(CashBackCategory entity);
    void removeLinksCompany(CashBackCategory entity);
}
