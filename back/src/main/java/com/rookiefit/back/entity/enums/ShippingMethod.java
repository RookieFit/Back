package com.rookiefit.back.entity.enums;

import lombok.Getter;

@Getter
public enum ShippingMethod {
    DIRECT("DIRECT"),
    DELIVERY("DELIVERY"),
    BOTH("BOTH");

    private final String method;

    ShippingMethod(String method) {
        this.method = method;
    }
}
