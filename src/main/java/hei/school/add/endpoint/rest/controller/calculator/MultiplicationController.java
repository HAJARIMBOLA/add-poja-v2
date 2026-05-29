package hei.school.add.endpoint.rest.controller.calculator;

import hei.school.add.service.calculator.CalculatorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MultiplicationController {

  private final CalculatorService calculatorService;

  @GetMapping("/calculator/multiplication")
  public ResponseEntity<?> multiplication(@RequestParam double a, @RequestParam double b) {
    try {
      return ResponseEntity.ok(calculatorService.multiplication(a, b));
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
