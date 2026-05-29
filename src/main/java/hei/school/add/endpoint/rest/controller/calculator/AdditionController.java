package hei.school.add.endpoint.rest.controller.calculator;

import java.math.BigDecimal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdditionController {

  @GetMapping("/calculator/addition")
  public ResponseEntity<?> addition(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
    if (a.compareTo(BigDecimal.ZERO) < 0 || b.compareTo(BigDecimal.ZERO) < 0) {
      return ResponseEntity.badRequest().body("Les valeurs négatives ne sont pas autorisées");
    }
    return ResponseEntity.ok(a.add(b));
  }
}
