package hei.school.add.endpoint.rest.controller.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import hei.school.add.conf.FacadeIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

class SoustractionControllerIT extends FacadeIT {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  private String url(String path) {
    return "http://localhost:" + port + path;
  }

  @Test
  void soustraction_two_positive_numbers() {
    Double result =
        restTemplate.getForObject(url("/calculator/soustraction?a=10&b=3"), Double.class);
    assertEquals(7.0, result);
  }

  @Test
  void soustraction_gives_negative_result() {
    Double result =
        restTemplate.getForObject(url("/calculator/soustraction?a=3&b=10"), Double.class);
    assertEquals(-7.0, result);
  }

  @Test
  void soustraction_with_zero() {
    Double result =
        restTemplate.getForObject(url("/calculator/soustraction?a=5&b=0"), Double.class);
    assertEquals(5.0, result);
  }

  @Test
  void soustraction_two_negative_numbers() {
    Double result =
        restTemplate.getForObject(url("/calculator/soustraction?a=-3&b=-2"), Double.class);
    assertEquals(-1.0, result);
  }
}
