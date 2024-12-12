---

**진행기간** : 2024-11-04 ~ 2024-12-11

**개발 배경 및 목적**

### 개발 배경

최근 건강과 피트니스에 대한 관심이 급증하면서, 많은 사람들이 운동과 식단 관리를 통해 건강을 개선하고자 노력하고 있습니다. 그러나 관련 정보와 기록 도구가 분산되어 있어 이를 효과적으로 기록하고 관리할 수 있는 시스템이 부족한 상황입니다.

이에 따라 운동, 식단, 커뮤니티 활동을 한 곳에서 통합적으로 관리할 수 있는 플랫폼의 필요성이 대두되었습니다.

### 목적

이 프로젝트는 사용자가 운동과 식단을 손쉽게 기록하고 관리하며, 커뮤니티를 통해 피트니스 정보를 공유할 수 있는 통합 플랫폼을 제공하는 것을 목표로 합니다.

---

### **프로젝트 목표**

1. **운동과 식단 관리의 체계화**
    - 매일의 운동 및 식단을 체계적으로 기록 및 관리
    - 시각적 자료(그래프, 캘린더)를 통해 건강 상태를 직관적으로 확인
2. **사용자 경험 강화**
    - 사용자의 건강 상태와 목표에 맞는 맞춤형 피드백 제공
    - 인증된 트레이너와 연결해 전문적인 상담 및 관리 제공
3. **커뮤니티 활성화**
    - 피트니스 정보 공유 및 사용자 간 동기 부여 촉진
    - 커뮤니티 활동을 통해 정보 교류와 소통 강화
4. **지속 가능한 건강한 습관 형성**
    - 건강 데이터를 기반으로 개인화된 인사이트 제공
    - 사용자들이 성장과 소통의 경험을 통해 건강한 라이프스타일을 구축할 수 있도록 지원

---

**기술 스택**

### **프론트엔드 ( FRONT )**

- **React**: 사용자 인터페이스 개발
- **TypeScript**: 안정적인 타입 시스템으로 코드 품질 강화
- **Figma**: 디자인 시안 작성 및 협업
- **VSCode** 확장 프로그램:
    - Auto Close Tag, Auto Rename Tag, Better Comments
    - ES7+ React/Redux/React-Native for React 개발
    - Eslint: 코드 품질 유지

### **백엔드 ( BACK )**

- **Spring Boot (Java 21)**: RESTful API 개발 및 비즈니스 로직 처리
- **Lombok**: 코드 간소화를 위한 어노테이션 활용
- **Gradle**: 프로젝트 빌드 및 의존성 관리
- **Firebase Storage**: 이미지 및 파일 저장소로 사용
- **VSCode** 확장 프로그램:
    - Spring Boot Dashboard, Spring Tools, Spring Extension Pack
    - Debugger for Java, Project Manager for Java

### **데이터베이스 (** DB **)**

- **AWS RDS**: 클라우드에서 안정적이고 확장 가능한 데이터베이스 서비스 제공.
- **RDS용 MySQL**: RDS를 통해 MySQL을 관리하며, 백업, 확장, 보안 등의 작업을 자동화.
- **MySQL**: 운동, 식단, 사용자 데이터 저장 및 관리
- **MySQL Extension (Weijan Chen)**: DB 관리 지원

### **배포 및 협업**

- **GitHub Actions**: CI/CD 자동화 파이프라인 구축
- **AWS EC2**: 서버 호스팅 및 관리
- **Notion**: 프로젝트 관리 및 협업

---

### **시스템 아키텍쳐**
![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/ecac8552-ad76-424c-9466-fc8b84aa8b25/cdf6e33c-a082-4674-a591-177ef07f3a3c/image.png)

---

### **플로우차트**
![플로우차트-F.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/ecac8552-ad76-424c-9466-fc8b84aa8b25/661857de-0f98-4954-872f-e84d4c19d8a4/%ED%94%8C%EB%A1%9C%EC%9A%B0%EC%B0%A8%ED%8A%B8-F.png)
