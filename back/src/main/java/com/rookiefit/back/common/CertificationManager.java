package com.rookiefit.back.common;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class CertificationManager {
    private final ConcurrentHashMap<String, String> certificationMap = new ConcurrentHashMap<>();

    public void saveCertificationNumber(String userId, String certifiacationNumber) {
        certificationMap.put(userId, certifiacationNumber);
    }

    public boolean verifyAndDelete(String userId, String inputCertificationNumber) {

        boolean isSuccessed = false;

        try {
            String storedCertificationNumber = certificationMap.get(userId);
            System.out.println("fir" + storedCertificationNumber);
            if (storedCertificationNumber == null)
                return false;
            if (storedCertificationNumber.equals(inputCertificationNumber)) {
                System.out.println(storedCertificationNumber + inputCertificationNumber);
                certificationMap.remove(userId);
                isSuccessed = true;
                return isSuccessed;
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            isSuccessed = false;
            return isSuccessed;
        }
        return isSuccessed;
    }
}
