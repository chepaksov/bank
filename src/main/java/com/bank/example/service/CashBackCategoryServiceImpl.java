package com.bank.example.service;

import com.bank.example.dao.CashBackCategoryDao;
import com.bank.example.dao.GenericDao;
import com.bank.example.model.Account;
import com.bank.example.model.CashBackCategory;
import com.bank.example.model.CashBackCompany;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class CashBackCategoryServiceImpl extends AbstractService<Long, CashBackCategory> implements CashBackCategoryService {

    private final CashBackCategoryDao cashBackCategoryDao;

    public CashBackCategoryServiceImpl(CashBackCategoryDao cashBackCategoryDao) {
        super(cashBackCategoryDao);
        this.cashBackCategoryDao = cashBackCategoryDao;
    }
//синхрон удаления у главной сущности
    @Override
    public void removeLinksAccount(CashBackCategory entity) {




        Set<Account> accountSet = new HashSet<>(entity.getAccounts());

        for (Account account : accountSet) {
            entity.removeAccount(account);
        }

    }

    @Override
    public void removeLinksCompany(CashBackCategory entity) {


        Set<CashBackCompany> cashBackCompanyHashSet = new HashSet<>(entity.getCashBackCompanies());

        for (CashBackCompany category : cashBackCompanyHashSet) {
            entity.removeCashBackCompany(category);
        }
    }
}
