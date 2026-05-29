package hei.school.add.endpoint.rest.controller.calculator.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import hei.school.add.endpoint.rest.controller.calculator.DivisionController;
import hei.school.add.service.calculator.CalculatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class DivisionControllerTest {

  @Mock private CalculatorService calculatorService;
  @InjectMocks private DivisionController controller;

  @Test
  void division_calls_service_and_returns_result() {
    when(calculatorService.division(10, 2)).thenReturn(5.0);

    ResponseEntity<?> response = controller.division(10, 2);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(5.0, response.getBody());
    verify(calculatorService, times(1)).division(10, 2);
  }

  @Test
  void division_by_zero_returns_bad_request() {
    when(calculatorService.division(10, 0))
        .thenThrow(new ArithmeticException("Division par zéro interdite"));

    ResponseEntity<?> response = controller.division(10, 0);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Division par zéro interdite", response.getBody());
  }

  @Test
  void division_when_negative_returns_bad_request() {
    when(calculatorService.division(-9, 3))
        .thenThrow(new IllegalArgumentException("Les valeurs négatives ne sont pas autorisées"));

    ResponseEntity<?> response = controller.division(-9, 3);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Les valeurs négatives ne sont pas autorisées", response.getBody());
  }

  @Test
  void division_service_called_once_no_more() {
    when(calculatorService.division(9, 3)).thenReturn(3.0);

    controller.division(9, 3);

    verify(calculatorService, times(1)).division(9, 3);
    verifyNoMoreInteractions(calculatorService);
  }
}
