package ir.farhad.resources;

import ir.farhad.model.entities.AccountEntity;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Path(value = "/api/accounts")
public class AccountResource {

    Set<AccountEntity> accounts = new HashSet<>();

    @PostConstruct
    public void setup() {
        accounts.add(new AccountEntity(123456789L, 987654321L, "George Baird", new BigDecimal("354.23")));
        accounts.add(new AccountEntity(121212121L, 888777666L, "Mary Taylor", new BigDecimal("560.03")));
        accounts.add(new AccountEntity(545454545L, 222444999L, "Diana Rigg", new BigDecimal("422.00")));
    }

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Set<AccountEntity> getAllAccounts() {
        return accounts;
//        return Collections.emptySet();
    }
}
