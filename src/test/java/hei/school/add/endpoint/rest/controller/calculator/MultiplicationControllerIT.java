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

class MultiplicationControllerIT extends FacadeIT {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  private String url(String path) {
    return "http://localhost:" + port + path;
  }

  @Test
  void multiplication_two_positive_numbers() {
    BigDecimal result = restTemplate.getForObject(url("/calculator/multiplication?a=4&b=5"), BigDecimal.class);
    assertEquals(new BigDecimal("20"), result);
  }

  @Test
  void multiplication_by_zero() {
    BigDecimal result = restTemplate.getForObject(url("/calculator/multiplication?a=9&b=0"), BigDecimal.class);
    assertEquals(new BigDecimal("0"), result);
  }

  @Test
  void multiplication_large_numbers() {
    BigDecimal result = restTemplate.getForObject(
        url("/calculator/multiplication?a=123456789012345678&b=987654321098765432"),
        BigDecimal.class);
    assertEquals(new BigDecimal("121932631137021795226185032818126896"), result);
  }

  @Test
  void multiplication_with_negative_returns_bad_request() {
    ResponseEntity<String> response =
        restTemplate.getForEntity(url("/calculator/multiplication?a=-3&b=4"), String.class);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Les valeurs négatives ne sont pas autorisées", response.getBody());
  }

  @Test
  void multiplication_both_negative_returns_bad_request() {
    ResponseEntity<String> response =
        restTemplate.getForEntity(url("/calculator/multiplication?a=-3&b=-4"), String.class);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Les valeurs négatives ne sont pas autorisées", response.getBody());
  }
}
