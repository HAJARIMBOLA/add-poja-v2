package hei.school.add.endpoint.rest.controller.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import hei.school.add.conf.FacadeIT;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class SoustractionControllerIT extends FacadeIT {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  private String url(String path) {
    return "http://localhost:" + port + path;
  }

  @Test
  void soustraction_positive_result() {
    BigDecimal result = restTemplate.getForObject(url("/calculator/soustraction?a=10&b=3"), BigDecimal.class);
    assertEquals(new BigDecimal("7"), result);
  }

  @Test
  void soustraction_result_zero() {
    BigDecimal result = restTemplate.getForObject(url("/calculator/soustraction?a=5&b=5"), BigDecimal.class);
    assertEquals(new BigDecimal("0"), result);
  }

  @Test
  void soustraction_large_numbers() {
    BigDecimal result = restTemplate.getForObject(
        url("/calculator/soustraction?a=999999999999999999&b=1"), BigDecimal.class);
    assertEquals(new BigDecimal("999999999999999998"), result);
  }

  @Test
  void soustraction_negative_result_returns_bad_request() {
    ResponseEntity<String> response =
        restTemplate.getForEntity(url("/calculator/soustraction?a=3&b=10"), String.class);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Le résultat ne peut pas être négatif", response.getBody());
  }

  @Test
  void soustraction_with_negative_returns_bad_request() {
    ResponseEntity<String> response =
        restTemplate.getForEntity(url("/calculator/soustraction?a=-5&b=3"), String.class);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Les valeurs négatives ne sont pas autorisées", response.getBody());
  }
}
