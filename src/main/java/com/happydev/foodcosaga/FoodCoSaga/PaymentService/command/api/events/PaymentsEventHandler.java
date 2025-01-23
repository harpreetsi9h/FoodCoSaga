package com.happydev.foodcosaga.FoodCoSaga.PaymentService.command.api.events;

import com.happydev.foodcosaga.FoodCoSaga.CommonService.events.PaymentCancelledEvent;
import com.happydev.foodcosaga.FoodCoSaga.CommonService.events.PaymentProcessedEvent;
import com.happydev.foodcosaga.FoodCoSaga.PaymentService.command.api.data.Payment;
import com.happydev.foodcosaga.FoodCoSaga.PaymentService.command.api.data.PaymentRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PaymentsEventHandler {

    private PaymentRepository paymentRepository;

    public PaymentsEventHandler(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @EventHandler
    public void on(PaymentProcessedEvent event) {
        Payment payment
                = Payment.builder()
                .paymentId(event.getPaymentId())
                .orderId(event.getOrderId())
                .paymentStatus("COMPLETED")
                .timeStamp(new Date())
                .build();

        paymentRepository.save(payment);
    }

    @EventHandler
    public void on(PaymentCancelledEvent event) {
        Payment payment
                = paymentRepository.findById(event.getPaymentId()).get();

        payment.setPaymentStatus(event.getPaymentStatus());

        paymentRepository.save(payment);
    }
}
