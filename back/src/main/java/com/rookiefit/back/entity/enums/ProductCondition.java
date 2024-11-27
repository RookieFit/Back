package com.rookiefit.back.entity.enums;

import lombok.Getter;

@Getter
public enum ProductCondition {
    NEW("NEW"),
    USED("USED"),
    LIKE_NEW("LIKE_NEW"),
    REFURBISHED("REFURBISHED");

    private final String condition;

    ProductCondition(String condition) {
        this.condition = condition;
    }
}
