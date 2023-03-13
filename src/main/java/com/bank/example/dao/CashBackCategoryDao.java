package com.bank.example.dao;

import com.bank.example.model.CashBackCategory;

import java.util.List;

public interface CashBackCategoryDao extends GenericDao<Long, CashBackCategory> {
    List<CashBackCategory> getCashBackCategoryJoinFetch ();
}
