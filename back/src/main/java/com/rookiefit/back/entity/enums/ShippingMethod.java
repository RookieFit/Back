package com.rookiefit.back.entity.enums;

import lombok.Getter;

@Getter
public enum ShippingMethod {
    DIRECT("direct"),
    DELIVERY("delivery"),
    BOTH("both");

    private final String method;

    ShippingMethod(String method) {
        this.method = method;
    }
}
