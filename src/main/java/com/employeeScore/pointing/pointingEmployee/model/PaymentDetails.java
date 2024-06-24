package com.employeeScore.pointing.pointingEmployee.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PaymentDetails {
    private int totalWorkHours;
    private int overtimeHours;
    private double totalPay;
}
