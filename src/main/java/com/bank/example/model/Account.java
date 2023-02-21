package com.bank.example.model;

import com.bank.example.dto.DocumentScansDto;
import com.bank.example.dto.SettingsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_generator")
    @SequenceGenerator(sequenceName = "account_sequence", name = "account_generator", allocationSize = 10)
    private Long id;

    private String firstName;

    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

    @ManyToMany
    @JoinTable(name = "account_on_cash_back_categories",
            joinColumns = {@JoinColumn(name = "account_id")}, inverseJoinColumns = {@JoinColumn(name = "cash_back_category_id")})
    private Set<CashBackCategory> cashBackCategories = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "account_on_cash_back_companies",
            joinColumns = {@JoinColumn(name = "account_id")}, inverseJoinColumns = {@JoinColumn(name = "cash_back_company_id")})
    private Set<CashBackCompany> cashBackCompanies = new HashSet<>();

    @OneToMany(mappedBy = "account", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Deposit> deposits = new HashSet<>();

    @OneToMany(mappedBy = "account", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Card> cards = new HashSet<>();

    @OneToOne(mappedBy = "account", orphanRemoval = true, cascade = CascadeType.ALL)
    private CloseRequest closeRequest;

    @OneToOne(mappedBy = "account", orphanRemoval = true, cascade = CascadeType.ALL)
    private DocumentScans documentScans;

    public Account() {
    }

    public Account(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Account(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Set<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits.clear();
        if (deposits != null && !deposits.isEmpty()) {
            this.deposits.addAll(deposits);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(firstName, account.firstName) &&
                Objects.equals(lastName, account.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
}
