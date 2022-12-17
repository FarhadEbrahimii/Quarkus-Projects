package ir.farhad.model.entities;

import ir.farhad.model.enums.AccountStatus;

import java.math.BigDecimal;

public class AccountEntity {
    public Long accountNumber;
    public Long customerNumber;
    public String customerName;
    public BigDecimal balance;
    public AccountStatus accountStatus = AccountStatus.OPEN;
    public AccountEntity() {
    }
    public AccountEntity(Long accountNumber, Long customerNumber, String
            customerName, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.balance = balance;
    }
    public void markOverdrawn() {
        accountStatus = AccountStatus.OVERDRAWN;
    }

    public void removeOverdrawnStatus() {
        accountStatus = AccountStatus.OPEN;
    }
    public void close() {
        accountStatus = AccountStatus.CLOSED;
        balance = BigDecimal.valueOf(0);
    }
    public void withdrawFunds(BigDecimal amount) {
        balance = balance.subtract(amount);
    }
    public void addFunds(BigDecimal amount) {
        balance = balance.add(amount);
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public Long getAccountNumber() {
        return accountNumber;
    }
    public String getCustomerName() {
        return customerName;
    }
    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Long customerNumber) {
        this.customerNumber = customerNumber;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }
}
