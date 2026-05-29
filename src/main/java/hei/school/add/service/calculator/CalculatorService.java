package hei.school.add.service.calculator;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

  private static final String NEGATIVE_ERROR = "Les valeurs négatives ne sont pas autorisées";
  private static final String NEGATIVE_RESULT_ERROR = "Le résultat ne peut pas être négatif";
  private static final String DIVISION_ZERO_ERROR = "Division par zéro interdite";

  public double addition(double a, double b) {
    validatePositive(a, b);
    return a + b;
  }

  public double soustraction(double a, double b) {
    validatePositive(a, b);
    double result = a - b;
    if (result < 0) {
      throw new IllegalArgumentException(NEGATIVE_RESULT_ERROR);
    }
    return result;
  }

  public double multiplication(double a, double b) {
    validatePositive(a, b);
    return a * b;
  }

  public double division(double a, double b) {
    validatePositive(a, b);
    if (b == 0) {
      throw new ArithmeticException(DIVISION_ZERO_ERROR);
    }
    return a / b;
  }

  private void validatePositive(double a, double b) {
    if (a < 0 || b < 0) {
      throw new IllegalArgumentException(NEGATIVE_ERROR);
    }
  }
}
