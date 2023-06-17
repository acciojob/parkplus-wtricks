package com.driver.services.impl;

import com.driver.model.Payment;
import com.driver.model.PaymentMode;
import com.driver.model.Reservation;
import com.driver.repository.PaymentRepository;
import com.driver.repository.ReservationRepository;
import com.driver.services.PaymentService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    ReservationRepository reservationRepository2;
    @Autowired
    PaymentRepository paymentRepository2;
    @Override
    public Payment pay(Integer reservationId, int amountSent, String mode) throws Exception {
        Optional<Reservation> reservations = reservationRepository2.findById(reservationId);
        if (reservations.isEmpty()) {
            throw new Exception("No Reservation is exist.");
        }

        Reservation res = reservations.get();
        PaymentMode pMode = null;
        if (mode.equalsIgnoreCase("card")) {
            pMode = PaymentMode.CARD;
        } else if (mode.equalsIgnoreCase("upi")) {
            pMode = PaymentMode.UPI;
        } else if (mode.equalsIgnoreCase("cash")) {
            pMode = PaymentMode.CASH;
        }

        if (pMode == null) {
            throw new Exception("Payment mode not detected");
        }

        int totalAmount = res.getSpot().getPricePerHour() * res.getNumberOfHours();
        if (amountSent < totalAmount) {
            throw new Exception("Insufficient Amount");
        }

        Payment payment = new Payment();
        payment.setPaymentComplete(false);
        payment.setPaymentMode(pMode);
        payment.setReservation(res);
        // Payment payment = Payment.builder()
        //                 .paymentComplete(true)
        //                 .paymentMode(pMode)
        //                 .reservation(res)
        //                 .build();

        return paymentRepository2.save(payment);
    }
}
