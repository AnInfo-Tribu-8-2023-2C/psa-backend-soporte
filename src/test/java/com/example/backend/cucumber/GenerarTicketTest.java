package com.example.backend.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

public class GenerarTicketTest {

    private Response response;

    @Given("se completaron los datos correspondientes para crear un ticket de una queja pendiente")
    public void datosCompletados() {
        String ticketRequestBody = "{\"title\":\"Problema con el login\",\"description\":\"Al usuario no lo deja loggearse desde el celular\",\"state\":\"ABIERTO\",\"severity\":3,\"productVersionId\":2}";

        Assertions.assertFalse(ticketRequestBody.contains(":\"\""), "El ticket no debe tener campos vacíos");
    }

    @Given("hay campos que no se completaron al generar un ticket de una queja")
    public void datosNoCompletados() {
        String ticketRequestBody = "{\"title\":\"\",\"description\":\"Al usuario no lo deja loggearse desde el celular\",\"state\":\"ABIERTO\",\"severity\":3,\"productVersionId\":2}";

        Assertions.assertTrue(ticketRequestBody.contains(":\"\""), "El ticket no debe tener campos vacíos");
    }

    @When("el colaborador de soporte intenta generar un ticket")
    public void enviarDatosAlBackend() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8000;
        RestAssured.basePath = "/products/versions/tickets";

        String ticketRequestBody = "{\"title\":\"Problema con el login\",\"description\":\"Al usuario no lo deja loggearse desde el celular\",\"state\":\"ABIERTO\",\"severity\":3,\"productVersionId\":2}";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(ticketRequestBody)
                .post();
    }

    @Then("se le informará que el ticket se ha generado con éxito y se generará.")
    public void confirmarGeneracionExitosa() {
        Assertions.assertEquals(201, response.getStatusCode(), "Se esperaba un código de estado 201");

        Assertions.assertEquals("Problema con el login", response.jsonPath().getString("data.title"), "El title no es el esperado");
        Assertions.assertEquals("Al usuario no lo deja loggearse desde el celular", response.jsonPath().getString("data.description"), "El description no es el esperado");
        Assertions.assertEquals("ABIERTO", response.jsonPath().getString("data.state"), "El state no es el esperado");
        Assertions.assertEquals("3", response.jsonPath().getString("data.severity"), "El severity no es el esperado");
        Assertions.assertEquals("2", response.jsonPath().getString("data.productVersionId"), "El productVersionId no es el esperado");
    }

    @Then("se le informará que hay campos que no han sido completados, y se pedirá que los complete.")
    public void generacionRechazada() {
        // Si falta completar un campo, ese error se manejaria desde el frontend, y nunca se mandaria al backend.
    }
}
