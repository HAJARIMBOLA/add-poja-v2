package hei.school.add.endpoint.rest.controller.calculator;

import static org.junit.jupiter.api.Assertions.*;
import hei.school.add.conf.FacadeIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class DivisionControllerIT extends FacadeIT {

  @LocalServerPort private int port;
  @Autowired private TestRestTemplate restTemplate;

  private String url(String path) { return "http://localhost:" + port + path; }

  @Test
  void division_two_positive_numbers() {
    Double result = restTemplate.getForObject(url("/calculator/division?a=10&b=2"), Double.class);
    assertEquals(5.0, result);
  }

  @Test
  void division_decimal_result() {
    Double result = restTemplate.getForObject(url("/calculator/division?a=7&b=2"), Double.class);
    assertEquals(3.5, result);
  }

  @Test
  void division_by_zero_returns_bad_request() {
    ResponseEntity<String> response = restTemplate.getForEntity(url("/calculator/division?a=10&b=0"), String.class);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Division par zéro interdite", response.getBody());
  }

  @Test
  void division_negative_returns_bad_request() {
    ResponseEntity<String> response = restTemplate.getForEntity(url("/calculator/division?a=-9&b=3"), String.class);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Les valeurs négatives ne sont pas autorisées", response.getBody());
  }
}
