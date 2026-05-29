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

class AdditionControllerIT extends FacadeIT {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  private String url(String path) {
    return "http://localhost:" + port + path;
  }

  @Test
  void addition_two_positive_numbers() {
    BigDecimal result = restTemplate.getForObject(url("/calculator/addition?a=5&b=3"), BigDecimal.class);
    assertEquals(new BigDecimal("8"), result);
  }

  @Test
  void addition_with_zero() {
    BigDecimal result = restTemplate.getForObject(url("/calculator/addition?a=7&b=0"), BigDecimal.class);
    assertEquals(new BigDecimal("7"), result);
  }

  @Test
  void addition_large_numbers() {
    BigDecimal result = restTemplate.getForObject(
        url("/calculator/addition?a=999999999999999999&b=999999999999999999"), BigDecimal.class);
    assertEquals(new BigDecimal("1999999999999999998"), result);
  }

  @Test
  void addition_very_large_numbers() {
    BigDecimal result = restTemplate.getForObject(
        url("/calculator/addition?a=123456789012345678901234567890&b=987654321098765432109876543210"),
        BigDecimal.class);
    assertEquals(new BigDecimal("1111111110111111111011111111100"), result);
  }

  @Test
  void addition_with_negative_returns_bad_request() {
    ResponseEntity<String> response =
        restTemplate.getForEntity(url("/calculator/addition?a=-3&b=5"), String.class);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Les valeurs négatives ne sont pas autorisées", response.getBody());
  }

  @Test
  void addition_both_negative_returns_bad_request() {
    ResponseEntity<String> response =
        restTemplate.getForEntity(url("/calculator/addition?a=-3&b=-2"), String.class);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Les valeurs négatives ne sont pas autorisées", response.getBody());
  }
}
