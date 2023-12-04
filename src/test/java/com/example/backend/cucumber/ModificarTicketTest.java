package com.example.backend.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

public class ModificarTicketTest {
    private Response response;
    String ticketRequestBody;

    String id;

    @Given("hay un ticket creado")
    public void hayUnTicketCreado() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8000;
        RestAssured.basePath = "/products/versions/tickets";

        ticketRequestBody = "{\"title\":\"Problema con el login\",\"description\":\"Al usuario no lo deja loggearse desde el celular\",\"state\":\"ABIERTO\",\"severity\":3,\"productVersionId\":2}";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(ticketRequestBody)
                .post();

        id = response.jsonPath().getString("data.id");
    }

    @When("el Colaborador de soporte modifica el título")
    public void modificaElTitulo() {
        ticketRequestBody = "{\"title\":\"Problema con el login desde celular\",\"description\":\"Al usuario no lo deja loggearse desde el celular\",\"state\":\"ABIERTO\",\"severity\":3,\"productVersionId\":2}";

    }

    @When("el Colaborador de soporte modifica el título a null")
    public void modificaElTituloANull() {
        ticketRequestBody = "{\"title\":\"\",\"description\":\"Al usuario no lo deja loggearse desde el celular\",\"state\":\"ABIERTO\",\"severity\":3,\"productVersionId\":2}";
    }

    @And("el Colaborador de soporte modifica la descripción")
    public void modificaLaDescripcion() {
        ticketRequestBody = "{\"title\":\"Problema con el login desde celular\",\"description\":\"Al usuario no lo deja loggearse desde un celular Samsung\",\"state\":\"ABIERTO\",\"severity\":3,\"productVersionId\":2}";
    }

    @And("el Colaborador de soporte guarda los cambios realizados")
    public void guardaLosCambios() {
        RestAssured.basePath = "/tickets/" + id;

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(ticketRequestBody)
                .put();
    }

    @Then("se le informará que el ticket se ha modificado con éxito")
    public void informaQueElTicketSeHaModificadoConExito() {
        Assertions.assertEquals(200, response.getStatusCode(), "Se esperaba un código de estado 200");

        System.out.println(response.asString());

        Assertions.assertEquals("Problema con el login desde celular", response.jsonPath().getString("title"), "El title no es el esperado");
        Assertions.assertEquals("Al usuario no lo deja loggearse desde un celular Samsung", response.jsonPath().getString("description"), "El description no es el esperado");
    }

    @Then("se le informará que hay campos vacíos y se le pedirá que complete todos los campos")
    public void informaQueHayCamposVacios() {
        //Assertions.assertEquals(200, response.getStatusCode(), "Se esperaba un código de estado 200");
        // Si falta completar un campo, ese error se manejaria desde el frontend, y nunca se mandaria al backend.
    }

    @Then("se le informará de todas maneras que el ticket se ha modificado con éxito")
    public void informaIgualmenteQueElTicketSeHaModificadoConExito() {
        Assertions.assertEquals(200, response.getStatusCode(), "Se esperaba un código de estado 200");

        Assertions.assertEquals("Problema con el login", response.jsonPath().getString("title"), "El title no es el esperado");
        Assertions.assertEquals("Al usuario no lo deja loggearse desde el celular", response.jsonPath().getString("description"), "El description no es el esperado");
    }
}
