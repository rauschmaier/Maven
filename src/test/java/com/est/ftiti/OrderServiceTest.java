package com.est.ftiti;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {
    private PaymentService paymentService;
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        paymentService = Mockito.mock(PaymentService.class);
        orderService = new OrderService(paymentService);
    }

    @Test
    public void testPlaceOrder_PaymentSuccessful() {
        Order order = new Order(100.0);
        when(paymentService.processPayment(order.getAmount())).thenReturn(true);

        boolean result = orderService.placeOrder(order);

        assertTrue(result);
        verify(paymentService, times(1)).processPayment(order.getAmount());
    }

    @Test
    public void testPlaceOrder_PaymentFailed() {
        Order order = new Order(100.0);
        when(paymentService.processPayment(order.getAmount())).thenReturn(false);

        boolean result = orderService.placeOrder(order);

        assertFalse(result);
        verify(paymentService, times(1)).processPayment(order.getAmount());
    }
}
