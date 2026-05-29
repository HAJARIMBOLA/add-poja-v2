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

class DivisionControllerIT extends FacadeIT {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  private String url(String path) {
    return "http://localhost:" + port + path;
  }

  @Test
  void division_two_positive_numbers() {
    BigDecimal result = restTemplate.getForObject(url("/calculator/division?a=10&b=2"), BigDecimal.class);
    assertEquals(new BigDecimal("5"), result.stripTrailingZeros());
  }

  @Test
  void division_gives_decimal_result() {
    BigDecimal result = restTemplate.getForObject(url("/calculator/division?a=7&b=2"), BigDecimal.class);
    assertEquals(new BigDecimal("3.5"), result.stripTrailingZeros());
  }

  @Test
  void division_large_numbers() {
    BigDecimal result = restTemplate.getForObject(
        url("/calculator/division?a=999999999999999999&b=3"), BigDecimal.class);
    assertEquals(new BigDecimal("333333333333333333"), result.stripTrailingZeros());
  }

  @Test
  void division_by_zero_returns_bad_request() {
    ResponseEntity<String> response =
        restTemplate.getForEntity(url("/calculator/division?a=10&b=0"), String.class);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Division par zéro interdite", response.getBody());
  }

  @Test
  void division_with_negative_returns_bad_request() {
    ResponseEntity<String> response =
        restTemplate.getForEntity(url("/calculator/division?a=-9&b=3"), String.class);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Les valeurs négatives ne sont pas autorisées", response.getBody());
  }
}
