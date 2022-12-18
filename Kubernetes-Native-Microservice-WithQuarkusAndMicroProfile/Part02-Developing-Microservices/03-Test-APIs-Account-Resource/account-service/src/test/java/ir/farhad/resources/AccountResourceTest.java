package ir.farhad.resources;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ir.farhad.model.entities.AccountEntity;
import ir.farhad.model.enums.AccountStatus;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@QuarkusTest
class AccountResourceTest {

    @Test
    void getAllAccounts() {
        Response result = given().when()
                .get("/api/accounts").then().statusCode(200)
                .body(
                        containsString("George Baird"),
                        containsString("Mary Taylor"),
                        containsString("Diana Rigg"))
                .extract().response();
        List<AccountEntity> accounts = result.jsonPath().getList("$");
        assertThat(accounts, not(empty()));
        assertThat(accounts, hasSize(3));
    }

    @Test
    void getAccount() {
        AccountEntity accountEntity =
                given().when()
                .get("/api/accounts/123456789")
                .then().statusCode(200)
                .body(containsString("George Baird")).extract()
                .as(AccountEntity.class);
        assertThat(accountEntity.getAccountNumber(), equalTo(123456789L));
        assertThat(accountEntity.getCustomerNumber(), equalTo(987654321L));
        assertThat(accountEntity.getCustomerName(), equalTo("George Baird"));
        assertThat(accountEntity.getBalance(), equalTo(new BigDecimal("354.23")));
        assertThat(accountEntity.getAccountStatus(), equalTo(AccountStatus.OPEN));
    }

    @Test
    @Order(3)
    void testCreateAccount() {
        AccountEntity newAccount = new AccountEntity(324324L, 112244L, "Sandy Holmes", new
                BigDecimal("154.55"));
        AccountEntity returnedAccount =
                given()
                        .contentType(ContentType.JSON)
                        .body(newAccount)
                        .when().post("/api/accounts")
                        .then()
                        .statusCode(201)
                        .extract()
                        .as(AccountEntity.class);
        assertThat(returnedAccount, notNullValue());
        assertThat(returnedAccount, equalTo(newAccount));
        Response result =
                given()
                        .when().get("/api/accounts")
                        .then()
                        .statusCode(200)
                        .body(
                                containsString("George Baird"),
                                containsString("Mary Taylor"),
                                containsString("Diana Rigg"),
                                containsString("Sandy Holmes")
                        )
                        .extract()
                        .response();
        List<AccountEntity> accounts = result.jsonPath().getList("$");
        assertThat(accounts, not(empty()));
        assertThat(accounts, hasSize(4));
    }
}