package com.bank.example.controller;

import com.bank.example.model.Account;
import com.bank.example.model.Card;
import com.bank.example.model.Deposit;
import com.bank.example.service.AccountService;
import com.bank.example.service.CardService;
import com.bank.example.sqltracker.AssertSqlCount;
import com.bank.example.sqltracker.QueryCountInfoHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account/{accountId}/card")
public class CardAccountRestController {

    private final CardService cardService;
    private final AccountService accountService;

    public CardAccountRestController(CardService cardService, AccountService accountService) {
        this.cardService = cardService;
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Void> createDeposit(@RequestBody Card card, @PathVariable Long accountId) {
        System.out.println("создание");
        AssertSqlCount.reset();
        Account account = accountService.getByKey(accountId);
        card.setAccount(account);
        cardService.persist(card);
        System.out.println(QueryCountInfoHolder.getReport());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<Void> deleteDeposit(@PathVariable Long cardId) {
        System.out.println("удаление");
        AssertSqlCount.reset();
        Card card = cardService.getByKey(cardId);
        cardService.remove(card);
        System.out.println(QueryCountInfoHolder.getReport());
        return ResponseEntity.ok().build();
    }
}
