package ir.farhad.model.entities;

import ir.farhad.model.enums.AccountStatus;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(name = "AccountEntity", description = "POJO representing an account.", type = SchemaType.OBJECT)
public class AccountEntity {

    @Schema(required = true, example = "123456789", minLength = 8,type = SchemaType.INTEGER)
    private Long accountNumber;

    @Schema(required = true, example = "432542374", minLength = 6, type = SchemaType.INTEGER)
    public Long customerNumber;

    @Schema(example = "Steve Hanger", type = SchemaType.STRING)
    public String customerName;

    @Schema(required = true, example = "438.32")
    public BigDecimal balance;

    @Schema(required = true, example = "OPEN")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountEntity that = (AccountEntity) o;

        if (!accountNumber.equals(that.accountNumber)) return false;
        return customerNumber.equals(that.customerNumber);
    }

    @Override
    public int hashCode() {
        int result = accountNumber.hashCode();
        result = 31 * result + customerNumber.hashCode();
        return result;
    }
}
