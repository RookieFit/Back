package com.rookiefit.back.common;

public class CertificationNumber {

    //인증번호 6자리 랜덤생성_김민준_2024_11_07_17:10
    public static String getCertificationNumber() {

        String certificationNumber = "";
        for (int count = 0; count < 6; count++) {
            certificationNumber += (int)(Math.random()*10);
        }

        return certificationNumber;
    }
}
