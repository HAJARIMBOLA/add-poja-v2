package hei.school.add.endpoint.rest.controller.calculator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SoustractionController {

  @GetMapping("/calculator/soustraction")
  public double soustraction(@RequestParam double a, @RequestParam double b) {
    return a - b;
  }
}
