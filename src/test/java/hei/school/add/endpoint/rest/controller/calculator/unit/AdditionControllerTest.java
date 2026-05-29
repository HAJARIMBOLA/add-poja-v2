package hei.school.add.endpoint.rest.controller.calculator.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import hei.school.add.endpoint.rest.controller.calculator.AdditionController;
import hei.school.add.service.calculator.CalculatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class AdditionControllerTest {

  @Mock private CalculatorService calculatorService;
  @InjectMocks private AdditionController controller;

  @Test
  void addition_calls_service_and_returns_result() {
    when(calculatorService.addition(5, 3)).thenReturn(8.0);

    ResponseEntity<?> response = controller.addition(5, 3);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(8.0, response.getBody());
    verify(calculatorService, times(1)).addition(5, 3);
  }

  @Test
  void addition_when_service_throws_returns_bad_request() {
    when(calculatorService.addition(-3, 5))
        .thenThrow(new IllegalArgumentException("Les valeurs négatives ne sont pas autorisées"));

    ResponseEntity<?> response = controller.addition(-3, 5);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Les valeurs négatives ne sont pas autorisées", response.getBody());
  }

  @Test
  void addition_service_called_exactly_once() {
    when(calculatorService.addition(10, 20)).thenReturn(30.0);

    controller.addition(10, 20);

    verify(calculatorService, times(1)).addition(10, 20);
    verifyNoMoreInteractions(calculatorService);
  }
}
