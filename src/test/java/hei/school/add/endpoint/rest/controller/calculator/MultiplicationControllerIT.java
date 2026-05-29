package hei.school.add.endpoint.rest.controller.calculator;

import static org.junit.jupiter.api.Assertions.*;
import hei.school.add.conf.FacadeIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class MultiplicationControllerIT extends FacadeIT {

  @LocalServerPort private int port;
  @Autowired private TestRestTemplate restTemplate;

  private String url(String path) { return "http://localhost:" + port + path; }

  @Test
  void multiplication_two_positive_numbers() {
    Double result = restTemplate.getForObject(url("/calculator/multiplication?a=4&b=5"), Double.class);
    assertEquals(20.0, result);
  }

  @Test
  void multiplication_by_zero() {
    Double result = restTemplate.getForObject(url("/calculator/multiplication?a=9&b=0"), Double.class);
    assertEquals(0.0, result);
  }

  @Test
  void multiplication_negative_returns_bad_request() {
    ResponseEntity<String> response = restTemplate.getForEntity(url("/calculator/multiplication?a=-3&b=4"), String.class);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Les valeurs négatives ne sont pas autorisées", response.getBody());
  }
}
