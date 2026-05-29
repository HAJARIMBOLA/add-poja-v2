package hei.school.add.endpoint.rest.controller.calculator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiplicationController {

  @GetMapping("/calculator/multiplication")
  public double multiplication(@RequestParam double a, @RequestParam double b) {
    return a * b;
  }
}
