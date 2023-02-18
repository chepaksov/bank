package com.bank.example.model;

import com.bank.example.dto.DocumentScansDto;
import com.bank.example.dto.SettingsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private List<CashBackCategory> cashBackCategories = new ArrayList<>();

    @ManyToMany
    private List<CashBackCompany> cashBackCompanies = new ArrayList<>();

    @OneToMany
    private List<Deposit> deposits = new ArrayList<>();

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

    public List<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
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
