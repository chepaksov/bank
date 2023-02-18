package com.bank.example.controller;

import com.bank.example.converter.AccountSettingsScansDtoConverter;
import com.bank.example.dto.AccountInfoDto;
import com.bank.example.dto.AccountSettingsScansDto;
import com.bank.example.model.Account;
import com.bank.example.model.DocumentScans;
import com.bank.example.model.Settings;
import com.bank.example.service.AccountService;
import com.bank.example.service.DocumentScansService;
import com.bank.example.service.SettingsService;
import com.bank.example.sqltracker.AssertSqlCount;
import com.bank.example.sqltracker.QueryCountInfoHolder;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountRestController {

    private final AccountService accountService;

    private final ModelMapper modelMapper;

    private final SettingsService settingsService;

    private final DocumentScansService documentScansService;

    public AccountRestController(AccountService accountService, ModelMapper modelMapper, SettingsService settingsService, DocumentScansService documentScansService) {
        this.accountService = accountService;
        this.modelMapper = modelMapper;
        this.settingsService = settingsService;
        this.documentScansService = documentScansService;
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountInfoDto> getInfoDto(@PathVariable Long accountId) {

        AssertSqlCount.reset();

        Account account = accountService.getByKey(accountId);
        AccountInfoDto accountInfoDto = modelMapper.map(account, AccountInfoDto.class);

        System.out.println(QueryCountInfoHolder.getReport());

        return ResponseEntity.ok(accountInfoDto);
    }

    @GetMapping("/{accountId}/withSettingsAndScans")
    public ResponseEntity<AccountSettingsScansDto> getAccountSettingsScansDto(@PathVariable Long accountId) {
        Account account = accountService.getByKey(accountId);
        Settings settings = settingsService.getByKey(account.getId());
        DocumentScans documentScans = documentScansService.getByKey(account.getId());
        AccountSettingsScansDto dto = AccountSettingsScansDtoConverter.convertToDto(account, settings, documentScans);
        return ResponseEntity.ok(dto);
    }

}
