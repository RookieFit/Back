package com.rookiefit.back.common;

public interface ResponseCode {
    String SUCCESS = "SU";
    String VALIDATION_ERROR = "VE";
    String DATABASE_ERROR = "DBE";
    String SIGN_IN_FAIL = "SF";
    String CERTIFICATION_FAIL = "CF";
    String DUPLICATE_ID = "DI";
    String DUPLICATE_PHONENUMBER = "DP";
    String PASSWORD_MISMATCH = "PM";
    String SMS_FAIL = "SMS_F";
    String PHONENUMBER_NOT_FOUND = "PF";
    String ID_NOT_FOUND = "IF";
    String COMMUNITY_LIST_NOT_FOUND = "CLNF";
    String MARKET_LIST_NOT_FOUND = "MLNF";
    String DATABASE_DELETE_FAIL = "DDF";
    String FOODINFO_INSERT_FAIL = "FIF";
    String NOTIFICATION_NOT_FOUND = "NNF";
}
