package com.rookiefit.back.service.implement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.rookiefit.back.dto.request.admin.LicenseApproveRequestDto;
import com.rookiefit.back.dto.response.admin.LicenseApproveResponseDto;
import com.rookiefit.back.service.LicenseStatusService;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.util.*;

@Service
public class LicenseStatusServiceImplement implements LicenseStatusService {

    private final RestTemplate restTemplate;
    private final String apiUrl = "http://api.odcloud.kr/api/nts-businessman/v1/status";

    @Value("${publicdata.servicekey}")
    private String serviceKey;

    public LicenseStatusServiceImplement(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<List<LicenseApproveResponseDto>> checkLicenseStatus(LicenseApproveRequestDto requestDto) {
        // URL 구성 (serviceKey 포함)
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("serviceKey", serviceKey)
                .toUriString();

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // 요청 본문 생성: businesses 배열을 전송
        JSONArray businessesArray = new JSONArray();
        for (LicenseApproveRequestDto.Business business : requestDto.getBusinesses()) {
            for (String bNo : business.getB_no()) { // List<String> 처리
                JSONObject businessJson = new JSONObject();
                businessJson.put("b_no", bNo); // 문자열로 개별 추가
                businessesArray.add(businessJson);
            }
        }

        JSONObject requestBody = new JSONObject();
        requestBody.put("businesses", businessesArray);

        // HttpEntity 생성
        HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);

        try {
            // API 호출
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);

            // API 응답 처리
            List<LicenseApproveResponseDto> result = parseApiResponse(response.getBody());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private List<LicenseApproveResponseDto> parseApiResponse(Map responseBody) {
        List<LicenseApproveResponseDto> resultList = new ArrayList<>();

        if (responseBody != null && responseBody.containsKey("data")) {
            List<Map<String, Object>> dataList = (List<Map<String, Object>>) responseBody.get("data");

            for (Map<String, Object> data : dataList) {
                LicenseApproveResponseDto.Data licenseData = LicenseApproveResponseDto.Data.builder()
                        .bNo((String) data.get("b_no"))
                        .bStt((String) data.get("b_stt"))
                        .bSttCd((String) data.get("b_stt_cd"))
                        .taxType((String) data.get("tax_type"))
                        .taxTypeCd((String) data.get("tax_type_cd"))
                        .endDt((String) data.get("end_dt"))
                        .utccYn((String) data.get("utcc_yn"))
                        .taxTypeChangeDt((String) data.get("tax_type_change_dt"))
                        .invoiceApplyDt((String) data.get("invoice_apply_dt"))
                        .rbfTaxType((String) data.get("rbf_tax_type"))
                        .rbfTaxTypeCd((String) data.get("rbf_tax_type_cd"))
                        .build();

                LicenseApproveResponseDto dto = LicenseApproveResponseDto.builder()
                        .statusCode((String) responseBody.get("status_code"))
                        .matchCnt((Integer) responseBody.get("match_cnt"))
                        .requestCnt((Integer) responseBody.get("request_cnt"))
                        .data(Collections.singletonList(licenseData))
                        .build();

                resultList.add(dto);
            }
        }

        return resultList;
    }
}
