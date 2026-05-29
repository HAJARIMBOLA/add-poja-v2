package hei.school.add.endpoint.rest.controller.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import hei.school.add.conf.FacadeIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

class MultiplicationControllerIT extends FacadeIT {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  private String url(String path) {
    return "http://localhost:" + port + path;
  }

  @Test
  void multiplication_two_positive_numbers() {
    Double result =
        restTemplate.getForObject(url("/calculator/multiplication?a=4&b=5"), Double.class);
    assertEquals(20.0, result);
  }

  @Test
  void multiplication_by_zero() {
    Double result =
        restTemplate.getForObject(url("/calculator/multiplication?a=99&b=0"), Double.class);
    assertEquals(0.0, result);
  }

  @Test
  void multiplication_with_negative_number() {
    Double result =
        restTemplate.getForObject(url("/calculator/multiplication?a=-3&b=4"), Double.class);
    assertEquals(-12.0, result);
  }

  @Test
  void multiplication_two_negative_numbers() {
    Double result =
        restTemplate.getForObject(url("/calculator/multiplication?a=-3&b=-4"), Double.class);
    assertEquals(12.0, result);
  }
}
