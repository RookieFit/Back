package com.rookiefit.back.entity.enums;

import lombok.Getter;

@Getter
public enum SaleStatus {
    SELLING("SELLING"),
    BUYING("BUYING"),
    SOLD("SOLD"),
    BOUGHT("BOUGHT");

    private final String status;

    SaleStatus(String status) {
        this.status = status;
    }
}
