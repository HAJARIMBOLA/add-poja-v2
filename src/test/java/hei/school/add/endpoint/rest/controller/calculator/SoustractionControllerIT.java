package hei.school.add.endpoint.rest.controller.calculator;

import static org.junit.jupiter.api.Assertions.*;
import hei.school.add.conf.FacadeIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class SoustractionControllerIT extends FacadeIT {

  @LocalServerPort private int port;
  @Autowired private TestRestTemplate restTemplate;

  private String url(String path) { return "http://localhost:" + port + path; }

  @Test
  void soustraction_positive_result() {
    Double result = restTemplate.getForObject(url("/calculator/soustraction?a=10&b=3"), Double.class);
    assertEquals(7.0, result);
  }

  @Test
  void soustraction_result_zero() {
    Double result = restTemplate.getForObject(url("/calculator/soustraction?a=5&b=5"), Double.class);
    assertEquals(0.0, result);
  }

  @Test
  void soustraction_negative_result_returns_bad_request() {
    ResponseEntity<String> response = restTemplate.getForEntity(url("/calculator/soustraction?a=3&b=10"), String.class);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Le résultat ne peut pas être négatif", response.getBody());
  }

  @Test
  void soustraction_negative_input_returns_bad_request() {
    ResponseEntity<String> response = restTemplate.getForEntity(url("/calculator/soustraction?a=-5&b=3"), String.class);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Les valeurs négatives ne sont pas autorisées", response.getBody());
  }
}
