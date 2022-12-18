package ir.farhad.config;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.core.Application;


@OpenAPIDefinition(
        tags = {
        @Tag(name = "transactions", description = "Operations manipulating account balances."),
        @Tag(name = "admin",        description = "Operations for managing accounts.")
        },
        info = @Info(
                title = "Account Service",
                description = "Service for maintaining accounts, their balances, and issuing deposit and withdrawal transactions",
                version = "34.0.0",
                license = @License(  name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0.html"
                )
        )
)
public class AccountServiceApplication extends Application {
}
