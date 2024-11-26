package com.rookiefit.back.common;

public interface ResponseMessage {
    String SUCCESS = "Success..";
    String VALIDATION_ERROR = "Validation failed..";
    String DATABASE_ERROR = "Database Error..";
    String SIGN_IN_FAIL = "Login Information mismatched error";
    String CERTIFICATION_FAIL = "Certification Error..";
    String DUPLICATE_ID = "Duplicated ID ..";
    String DUPLICATE_PHONENUMBER = "Duplicated PhoneNumber ..";
    String PASSWORD_MISMATCH = "Password Mismatch ..";
    String SMS_FAIL = "SMS SEND FAIL ..";
    String PHONENUMBER_NOT_FOUND = "PhoneNumber is not found..";
    String ID_NOT_FOUND = "Id not Found..";
    String COMMUNITY_LIST_NOT_FOUND = "Coummunity list id is not found..";
    String DATABASE_DELETE_FAIL = "Database Delete Fail ..";
    String FOODINFO_INSERT_FAIL = "FoodInfo Insert Fail ..";
}
