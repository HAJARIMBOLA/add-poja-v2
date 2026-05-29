package hei.school.add.endpoint.rest.controller.calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DivisionController {

  @GetMapping("/calculator/division")
  public ResponseEntity<?> division(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
    if (a.compareTo(BigDecimal.ZERO) < 0 || b.compareTo(BigDecimal.ZERO) < 0) {
      return ResponseEntity.badRequest().body("Les valeurs négatives ne sont pas autorisées");
    }
    if (b.compareTo(BigDecimal.ZERO) == 0) {
      return ResponseEntity.badRequest().body("Division par zéro interdite");
    }
    // MathContext.DECIMAL128 = précision 34 chiffres significatifs (norme bancaire)
    return ResponseEntity.ok(a.divide(b, MathContext.DECIMAL128));
  }
}
