package com.rookiefit.back.dto.response.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LicenseApproveResponseDto {

    private String statusCode; // API 상태 코드
    private int matchCnt; // 조회 매칭 수
    private int requestCnt; // 조회 요청 수
    private List<Data> data; // 사업자등록 상태조회 결과 리스트

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Data {
        private String bNo; // 사업자등록번호
        private String bStt; // 사업자등록상태
        private String bSttCd; // 사업자등록상태코드
        private String taxType; // 세금 유형
        private String taxTypeCd; // 세금 유형 코드
        private String endDt; // 종료 일자
        private String utccYn; // 단위과세 여부
        private String taxTypeChangeDt; // 세금 유형 변경 일자
        private String invoiceApplyDt; // 전자세금계산서 적용 일자
        private String rbfTaxType; // 간이과세 여부
        private String rbfTaxTypeCd; // 간이과세 코드
    }
}
