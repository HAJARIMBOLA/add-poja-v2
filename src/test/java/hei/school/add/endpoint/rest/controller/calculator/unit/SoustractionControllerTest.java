package hei.school.add.endpoint.rest.controller.calculator.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import hei.school.add.endpoint.rest.controller.calculator.SoustractionController;
import hei.school.add.service.calculator.CalculatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class SoustractionControllerTest {

  @Mock private CalculatorService calculatorService;
  @InjectMocks private SoustractionController controller;

  @Test
  void soustraction_calls_service_and_returns_result() {
    when(calculatorService.soustraction(10, 3)).thenReturn(7.0);

    ResponseEntity<?> response = controller.soustraction(10, 3);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(7.0, response.getBody());
    verify(calculatorService, times(1)).soustraction(10, 3);
  }

  @Test
  void soustraction_when_negative_result_returns_bad_request() {
    when(calculatorService.soustraction(3, 10))
        .thenThrow(new IllegalArgumentException("Le résultat ne peut pas être négatif"));

    ResponseEntity<?> response = controller.soustraction(3, 10);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Le résultat ne peut pas être négatif", response.getBody());
  }

  @Test
  void soustraction_when_negative_input_returns_bad_request() {
    when(calculatorService.soustraction(-5, 3))
        .thenThrow(new IllegalArgumentException("Les valeurs négatives ne sont pas autorisées"));

    ResponseEntity<?> response = controller.soustraction(-5, 3);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Les valeurs négatives ne sont pas autorisées", response.getBody());
  }
}
