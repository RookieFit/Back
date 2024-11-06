package com.rookiefit.back.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Component
@RequiredArgsConstructor
public class SmsCerificationNumberProvider {

    DefaultMessageService messageService;


    @Value("${coolsms.api-key}")
    private String apiKey;

    @Value("${coolsms.api-secret}")
    private String apiSecret;

    @Value("${coolsms.from}")
    private String from;
    
    public boolean sendCertificationSms(String phoneNumber , String certificationNumber ){
        try {

             this.messageService = NurigoApp.INSTANCE.initialize( apiKey , apiSecret , "https://api.coolsms.co.kr" );

            Message message = new Message();
            message.setFrom(from);
            message.setTo(phoneNumber);
            message.setText(certificationNumber);

            this.messageService.sendOne(new SingleMessageSendingRequest(message));

            return true;

        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }

    }
}
