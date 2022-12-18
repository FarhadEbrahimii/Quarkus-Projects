package ir.farhad.resources;

import ir.farhad.exceptions.ErrorResponse;
import ir.farhad.exceptions.mapper.ErrorMapper;
import ir.farhad.model.entities.AccountEntity;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

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
    @Operation(description = "Get all bank account.")
    @APIResponse(responseCode = "200", description = "Retrieved all Accounts",
            content = @Content(
            schema = @Schema(
                    type = SchemaType.ARRAY,
                    implementation = AccountEntity.class)
    )
    )
    public Set<AccountEntity> getAllAccounts() {
        return accounts;
//        return Collections.emptySet();
    }

    @GET
    @Path(value = "/{accountNumber}")
    @Produces(value = MediaType.APPLICATION_JSON)
    @Operation(description = "Get a bank account.")
    @APIResponse(responseCode = "200",
            description = "Successfully retrieved an account.",
            content = @Content(
                    schema = @Schema(implementation = AccountEntity.class))
    )
    @APIResponse(responseCode = "400",
            description = "Account with id of {accountNumber} does not exist.",
            content = @Content(
            schema = @Schema(
                    implementation = ErrorResponse.class, example = """
                    {
                        exceptionType: javax.ws.rs.WebApplicationException,
                        code: 400,
                        error: No Account number specified.
                    }
                          """)
    )
    )
    public AccountEntity getAccount(@PathParam(value = "accountNumber") Long accountNumber){
        Optional<AccountEntity> optionalAccount = accounts.stream().filter(item -> item.getAccountNumber().equals(accountNumber)).findFirst();
        return optionalAccount.orElseThrow(() -> new WebApplicationException("Account with id of " + accountNumber + " does not exist.", 404));
//        return optionalAccount.orElseThrow(()-> new NotFoundException("Account with id of "+accountNumber+" does not exist."));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Create a new bank account.")
    @APIResponse(responseCode = "201", description = "Successfully created a new account.",

            content = @Content(
                    schema = @Schema(implementation = AccountEntity.class))
    )
    @APIResponse(responseCode = "400",

            description = "No account number was specified on the Account.",
            content = @Content(
            schema = @Schema(
                    implementation = ErrorResponse.class, example = """
                    {
                        exceptionType: javax.ws.rs.WebApplicationException,
                        code: 400,
                        error: No Account number specified.
                    }
                          """)
    )
    )
    public Response createAccount(AccountEntity account) {
        if (account.getAccountNumber() == null) {
            throw new WebApplicationException("No Account number specified.", 400);
        }
        accounts.add(account);
        return Response.status(201).entity(account).build();
    }

    @APIResponse(responseCode = "200", description = "Successfully deposited funds to an account.",
            content = @Content(
            schema = @Schema(implementation = AccountEntity.class))
    )
    @RequestBody(  name = "amount", description = "Amount to be deposited into the account.",
            required = true, content = @Content(
                    schema = @Schema( name = "amount", type = SchemaType.STRING,
                            required = true,   minLength = 4),
                    example = "435.61"
            )
    )
    public AccountEntity deposit(
            @Parameter(
                    name = "accountNumber", description = "Number of the Account to deposit into.",
                    required = true,in = ParameterIn.PATH
            )
            @PathParam("accountNumber") Long accountNumber,
            String amount) {
        return null;
    }
}
