package com.example.backend.cucumber;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

public class GenerarTicketTest {

    private Response response;
    //private String ticketRequestBody;

    @Dado("se completaron los datos correspondientes para crear un ticket de una queja pendiente")
    public void datosCompletados() {
        String ticketRequestBody = "{\"product_id\":\"1\",\"name\":\"Windows 3.2.4\",\"description\":\"Version 3.2.4 para Windows\"}";

        Assertions.assertFalse(ticketRequestBody.contains(":\"\""), "El ticket no debe tener campos vacíos");
    }

    @Dado("hay campos que no se completaron al generar un ticket de una queja")
    public void datosNoCompletados() {
        String ticketRequestBody = "{\"product_id\":\"1\",\"name\":\"\",\"description\":\"Version 3.2.4 para Windows\"}";

        Assertions.assertFalse(ticketRequestBody.contains(":\"\""), "El ticket no debe tener campos vacíos");
    }

    @Cuando("el colaborador de soporte intenta generar un ticket")
    public void enviarDatosAlBackend() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3307;
        RestAssured.basePath = "/products/versions/tickets";

        String ticketRequestBody = """
        {
            "title": "Ticket title8",
            "description": "Ticket description8",
            "state": "ABIERTO",
            "severity": 3,
            "productVersionId": 3
        }
         """;

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(ticketRequestBody)
                .post();
    }

    @Entonces("se le informará que el ticket se ha generado con éxito y se generará.")
    public void confirmarGeneracionExitosa() {
        Assertions.assertEquals(200, response.getStatusCode(), "Se esperaba un código de estado 200");

        Assertions.assertEquals("1", response.jsonPath().getString("product_id"), "El product_id no es el esperado");
        Assertions.assertEquals("Windows 3.2.4", response.jsonPath().getString("name"), "El name no es el esperado");
        Assertions.assertEquals("Version 3.2.4 para Windows", response.jsonPath().getString("description"), "El description no es el esperado");
    }

    @Entonces("se le informará que hay campos que no han sido completados, y se pedirá que los complete.")
    public void generacionRechazada() {
        //Assertions.assertEquals(400, response.getStatusCode(), "Se esperaba un código de estado 400");
        //Assertions.assertTrue(response.body().asString().contains("Campos incompletos"), "El mensaje de error esperado no fue recibido");
    }
}
