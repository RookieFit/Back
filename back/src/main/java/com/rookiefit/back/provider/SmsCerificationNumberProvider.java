package com.rookiefit.back.provider;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.KakaoOption;
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

    @Value("${kakaotalk.pfid}")
    private String Pfid;

    @Value("${kakaotalk.templateid}")
    private String templateId;
    
    /*public boolean sendCertificationSms(String phoneNumber , String certificationNumber ){
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

    }*/

    public boolean sendCertificationKakao(String phoneNumber , String certificationNumber) {

        try {

            this.messageService = NurigoApp.INSTANCE.initialize( apiKey , apiSecret , "https://api.coolsms.co.kr" );

            KakaoOption kakaoOption = new KakaoOption();

            //kakaoOption.setDisableSms(true);

            //등록된 카카오 비즈니스 채널의 pfId 입력
            kakaoOption.setPfId(Pfid);
            //등록된 카카오 알림톡 템플릿의 templateId를 입력
            kakaoOption.setTemplateId(templateId);
            // 알림톡 템플릿 내에 #{변수} 형태가 존재할 경우 variables를 설정해주세요.
            HashMap<String, String> variables = new HashMap<>();
            variables.put("#{certificationNumber}", certificationNumber);
            kakaoOption.setVariables(variables);


            Message message = new Message();
            message.setFrom(from);
            message.setTo(phoneNumber);
            message.setKakaoOptions(kakaoOption);

            this.messageService.sendOne(new SingleMessageSendingRequest(message));

            return true;
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
