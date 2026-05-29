package hei.school.add.endpoint.rest.controller.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import hei.school.add.conf.FacadeIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

class AdditionControllerIT extends FacadeIT {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  private String url(String path) {
    return "http://localhost:" + port + path;
  }

  @Test
  void addition_two_positive_numbers() {
    Double result = restTemplate.getForObject(url("/calculator/addition?a=5&b=3"), Double.class);
    assertEquals(8.0, result);
  }

  @Test
  void addition_with_negative_number() {
    Double result = restTemplate.getForObject(url("/calculator/addition?a=10&b=-4"), Double.class);
    assertEquals(6.0, result);
  }

  @Test
  void addition_with_zero() {
    Double result = restTemplate.getForObject(url("/calculator/addition?a=7&b=0"), Double.class);
    assertEquals(7.0, result);
  }

  @Test
  void addition_two_negative_numbers() {
    Double result = restTemplate.getForObject(url("/calculator/addition?a=-3&b=-2"), Double.class);
    assertEquals(-5.0, result);
  }
}
