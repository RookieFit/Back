package com.rookiefit.back.common;

public interface ResponseMessage {
    String SUCCESS = "Success..";
    String VALIDATION_ERROR = "Validation failed..";
    String DATABASE_ERROR = "Database Error..";
    String Sign_IN_FAIL = "Login Information mismatched error";
    String CERTIFICATION_FAIL = "Certification Error..";
    String DUPLICATE_ID = "Duplicated ID ..";
    String SMS_FAIL = "SMS SEND FAIL ..";
    String PhoneNumber_NOT_FOUND = "PhoneNumber is not found..";
    String ID_NOT_FOUND = "Id not Found..";
}
