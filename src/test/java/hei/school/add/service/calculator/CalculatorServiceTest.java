package hei.school.add.service.calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorServiceTest {

  private CalculatorService service;

  @BeforeEach
  void setUp() {
    service = new CalculatorService();
  }

  @Test
  void addition_positive_numbers() {
    assertEquals(8.0, service.addition(5, 3));
  }

  @Test
  void addition_with_zero() {
    assertEquals(7.0, service.addition(7, 0));
  }

  @Test
  void addition_negative_throws() {
    assertThrows(IllegalArgumentException.class, () -> service.addition(-1, 5));
  }

  @Test
  void soustraction_positive_result() {
    assertEquals(7.0, service.soustraction(10, 3));
  }

  @Test
  void soustraction_result_zero() {
    assertEquals(0.0, service.soustraction(5, 5));
  }

  @Test
  void soustraction_negative_result_throws() {
    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
        () -> service.soustraction(3, 10));
    assertEquals("Le résultat ne peut pas être négatif", ex.getMessage());
  }

  @Test
  void soustraction_negative_input_throws() {
    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
        () -> service.soustraction(-5, 3));
    assertEquals("Les valeurs négatives ne sont pas autorisées", ex.getMessage());
  }

  @Test
  void multiplication_positive_numbers() {
    assertEquals(20.0, service.multiplication(4, 5));
  }

  @Test
  void multiplication_by_zero() {
    assertEquals(0.0, service.multiplication(99, 0));
  }

  @Test
  void multiplication_negative_throws() {
    assertThrows(IllegalArgumentException.class, () -> service.multiplication(-3, 4));
  }

  @Test
  void division_positive_numbers() {
    assertEquals(5.0, service.division(10, 2));
  }

  @Test
  void division_decimal_result() {
    assertEquals(3.5, service.division(7, 2));
  }

  @Test
  void division_by_zero_throws() {
    ArithmeticException ex = assertThrows(ArithmeticException.class,
        () -> service.division(10, 0));
    assertEquals("Division par zéro interdite", ex.getMessage());
  }

  @Test
  void division_negative_throws() {
    assertThrows(IllegalArgumentException.class, () -> service.division(-9, 3));
  }
}
