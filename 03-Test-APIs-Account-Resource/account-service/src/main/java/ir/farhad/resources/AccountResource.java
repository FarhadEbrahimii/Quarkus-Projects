package ir.farhad.resources;

import ir.farhad.model.entities.AccountEntity;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
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

    @GET
    @Path(value = "/{accountNumber}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public AccountEntity getAccount(@PathParam(value = "accountNumber") Long accountNumber){
        Optional<AccountEntity> optionalAccount = accounts.stream().filter(item -> item.getAccountNumber().equals(accountNumber)).findFirst();
        return optionalAccount.orElseThrow(() -> new WebApplicationException("Account with id of " + accountNumber + " does not exist.", 404));
//        return optionalAccount.orElseThrow(()-> new NotFoundException("Account with id of "+accountNumber+" does not exist."));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(AccountEntity account) {
        if (account.getAccountNumber() == null) {
            throw new WebApplicationException("No Account number specified.", 400);
        }
        accounts.add(account);
        return Response.status(201).entity(account).build();
    }
}
