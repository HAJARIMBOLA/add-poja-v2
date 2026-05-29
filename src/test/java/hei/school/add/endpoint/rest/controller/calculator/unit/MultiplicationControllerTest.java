package hei.school.add.endpoint.rest.controller.calculator.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import hei.school.add.endpoint.rest.controller.calculator.MultiplicationController;
import hei.school.add.service.calculator.CalculatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class MultiplicationControllerTest {

  @Mock private CalculatorService calculatorService;
  @InjectMocks private MultiplicationController controller;

  @Test
  void multiplication_calls_service_and_returns_result() {
    when(calculatorService.multiplication(4, 5)).thenReturn(20.0);

    ResponseEntity<?> response = controller.multiplication(4, 5);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(20.0, response.getBody());
    verify(calculatorService, times(1)).multiplication(4, 5);
  }

  @Test
  void multiplication_when_negative_returns_bad_request() {
    when(calculatorService.multiplication(-3, 4))
        .thenThrow(new IllegalArgumentException("Les valeurs négatives ne sont pas autorisées"));

    ResponseEntity<?> response = controller.multiplication(-3, 4);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Les valeurs négatives ne sont pas autorisées", response.getBody());
  }

  @Test
  void multiplication_service_called_once_no_more() {
    when(calculatorService.multiplication(6, 7)).thenReturn(42.0);

    controller.multiplication(6, 7);

    verify(calculatorService, times(1)).multiplication(6, 7);
    verifyNoMoreInteractions(calculatorService);
  }
}
