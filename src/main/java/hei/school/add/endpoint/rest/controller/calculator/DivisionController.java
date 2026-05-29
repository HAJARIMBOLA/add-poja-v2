package hei.school.add.endpoint.rest.controller.calculator;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DivisionController {

  @GetMapping("/calculator/division")
  public ResponseEntity<?> division(@RequestParam double a, @RequestParam double b) {
    if (b == 0) {
      return ResponseEntity.badRequest().body("Division par zéro interdite");
    }
    return ResponseEntity.ok(a / b);
  }
}
