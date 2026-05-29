package hei.school.add.endpoint.rest.controller.calculator;

import hei.school.add.service.calculator.CalculatorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DivisionController {

  private final CalculatorService calculatorService;

  @GetMapping("/calculator/division")
  public ResponseEntity<?> division(@RequestParam double a, @RequestParam double b) {
    try {
      return ResponseEntity.ok(calculatorService.division(a, b));
    } catch (IllegalArgumentException | ArithmeticException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
