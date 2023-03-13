package com.bank.example.controller;

import com.bank.example.converter.CashBackCategoryDetailedConverter;
import com.bank.example.dao.CashBackCategoryDao;
import com.bank.example.dto.CashBackCategoryDetailedDto;
import com.bank.example.model.CashBackCategory;
import com.bank.example.service.CashBackCategoryService;
import com.bank.example.sqltracker.AssertSqlCount;
import com.bank.example.sqltracker.QueryCountInfoHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/manager/cashback/category")
public class CashBackCategoryManagerController {
    private final CashBackCategoryDao cashBackCategoryDao;
    private final CashBackCategoryService cashBackCategoryService;

    public CashBackCategoryManagerController(CashBackCategoryDao cashBackCategoryDao, CashBackCategoryService cashBackCategoryService) {
        this.cashBackCategoryDao = cashBackCategoryDao;
        this.cashBackCategoryService = cashBackCategoryService;
    }

    @GetMapping("/detailed")
    public ResponseEntity<List<CashBackCategoryDetailedDto>> getCashBackCategory() {
        AssertSqlCount.reset();

        List<CashBackCategory> category = cashBackCategoryDao.getCashBackCategoryJoinFetch();

        List<CashBackCategoryDetailedDto> cashBackCategoryDetailedDtos = CashBackCategoryDetailedConverter.convertEntityToDtoList(category);

        String report = QueryCountInfoHolder.getReport();
        System.out.println(report);
        return ResponseEntity.ok(cashBackCategoryDetailedDtos);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteDeposit(@PathVariable Long categoryId) {
        AssertSqlCount.reset();
        CashBackCategory category = cashBackCategoryService.getByKey(categoryId);
        cashBackCategoryService.remove(category);
        String report = QueryCountInfoHolder.getReport();
        System.out.println(report);
        return ResponseEntity.ok().build();
    }
}
