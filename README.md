<div align="center">
  <br />
  <img src="https://user-images.githubusercontent.com/60915285/202854226-ad76225c-b50e-44d6-9fef-387a15b43d5e.png" width="80%"/>
  <br />
</div>

<div align="center">
 <h3><b>인생N컷 📸</b></h3>
우주여행을 통해 인생을 되돌아보고 생각을 정리할 수 있는 힐링 회고 웹서비스 🪐<br><br>
  2022.10.11 ~ 2022.11.21
  <br><br>

[Notion](https://sunset-offer-934.notion.site/N-3d2ec3e6d83d4ed0b01358e786997664) | [UCC]()

</div>
<br/>

## 서비스 배경 💡

"바쁘다 바빠 현대사회" 라는 말을 들어보신 적이 있나요? <br>
현대 사회는 태어나는 순간부터 치열한 경쟁에서 뒤쳐지지 않으려면 열심히 사는것을 미덕으로 여기고 그에 맞는 행동을 요구합니다.<br>모두가 각자마다의 이유로 바쁘게 살고 있습니다. <br>그래서 현대인에게는 잠시 쉬어갈 시간, 한번쯤 자신을 돌아볼 시간이 필요하다고 생각했습니다.<br>이러한 이유로 다양한 질문을 통해 사용자의 인생을 되돌아보고 생각을 정리할 수 있는 시간을 부여하고,<br>회고를 통해 "성장의 밑거름" 혹은 "동기부여"가 이루어질 수 있도록 도와주자는 의도를 가지고 서비스를 기획하게 되었습니다.
<br><br><br>

## 서비스 이름 및 설명 🖥

"인생N컷"은 인생을 여러 장면들로 담아낸다는 의미를 이름에 담았습니다. 스토리텔링으로 진행되며, 여행이 끝나면 결과를 기록물로 제공하기 때문에 이러한 뜻을 서비스 이름에 담았습니다.

<br><br>

## 개발 환경 및 기술 스택 🔨

### FE

`HTML` <br>
`CSS` <br>
`JavaScript` <br>
`Node.js 16.16.0` <br>
`Vue 3.2.13` <br>
`Pinia 2.0.23` <br>
`Bootstrap-Vue 0.3.12` <br>

### BE

`Java 8` <br>
`Spring Boot 2.7.5` <br>
`Gradle 7.5` <br>
`JPA 2.7.5` <br>
`Postgresql 12.12` <br>
`Swagger2 3.0.0` <br>

### CI/CD

`AWS EC2` <br>
`Ubuntu 20.0.4 LTS` <br>
`Docker 20.10.17` <br>
`Jenkins 2.346.2` <br>
`Nginx 1.23.1` <br>

### 코드 품질 관리

`Sonarqube 3.4.0.2513` <br>
`Jacoco 0.8.5` <br>

### 버전/이슈 관리

`Jira` <br>
`GitLab` <br>
`Sourcetree` <br>

### TOOL

`Postman` <br>
`Figma` <br>

### 협업

`Cisco Webex Meetings` <br>
`Mattermost` <br>
`Notion` <br>

### IDE

`Visual Studio Code` <br>
`IntelliJ`

<br><br>

## 서비스 구조 및 흐름도 🔍

<img width="849" alt="image" src="https://user-images.githubusercontent.com/60915285/202860971-ab6f91b4-6972-4766-93b0-c11737fc6cdd.png">

<img width="849" alt="image" src="https://user-images.githubusercontent.com/60915285/202861130-96bc17ff-3b8f-4742-bc37-9b4ae14d43ee.png">

<br><br>

## 주요 기능 📚

### 1. 소셜로그인

소셜(카카오) 로그인이 가능합니다.<br>
jwt 로그인으로 구현하였습니다.<br>
![카카오로그인](/uploads/9600a6a73648db2bbbe1ef2a266a455f/카카오로그인.gif)
<br><br>

### 2. 인트로

안내 문구와 함께 인생을 회상하는 추억여행을 시작합니다.<br>
![인트로](https://user-images.githubusercontent.com/77991314/202927464-7692c44a-0d8e-490b-a35f-4a2781a3840c.gif)
<br><br>

### 3. 행성 방문

질문에 맞춘 컨셉을 갖고 있는 행성 목록들이 있습니다.<br>
최소 3개의 행성을 방문해야 여행을 마칠 수 있습니다.<br>

- 하하호호 행성 :
  살면서 가장 행복했던 일은 무었이었나요?<br>
  ![하하호호행성](/uploads/f2cebdcc92801b427a90bd9114c0f17d/하하호호행성.gif)
- 훌쩍훌쩍 행성 :  
   살면서 가장 슬펐던 일은 무었이었나요?<br>
  ![훌쩍훌쩍행성](/uploads/43e00303340009532d49ab122b162879/훌쩍훌쩍행성.gif)
- 반짝반짝 행성 :
  나에게 보물 1호는?<br>
  ![반짝반짝행성](/uploads/3a0e0674ec9372b593a3ad86c7c2d6be/반짝반짝행성.gif)
- 이루지못행성 :
  이루지 못한 꿈이 있나요?<br>
  ![이루지못행성](/uploads/05b68963dba4935fda7f0f03cc22e75d/이루지못행성.gif)
- 괜히글행성 :
  후회되는 일이 있나요? 후회를 버리고 가세요!<br>
  ![괜히글행성](/uploads/7036b28a5a98f66425f3b769387b6989/괜히글행성.gif)
- 둠칫둠칫행성 :
  나만의 인생 노래가 있나요?<br>
  ![둠칫둠칫행성](/uploads/ad5be043fc8dfe4ef4a312f2674a001e/둠칫둠칫행성.gif)

- 지니행성 :
  이루고 싶은 소원이 있나요??<br>
  ![지니행성](/uploads/d2dbd54f68fc6ad03b595a46475eaa9c/지니행성.gif)
- 넌너무소중행성 :
  누구보다 소중한 나에게 한마디!<br>
  ![넌너무소중행성](/uploads/85f834f64fe862a73b8bc26e47522000/넌너무소중행성.gif)
  <br><br>

### 4. 나에게 남기는 한마디

행성 방문을 마치고 세상의 끝에서 나에게 남기고 싶은 한마디를 적어주세요!<br>
![나에게하는한마디](/uploads/6e75f31ad5a58b6d5bd923a1792a1be9/나에게하는한마디.gif)
<br><br>

### 5. 여행일지

여행하는 동안 방문했던 행성들의 답변들을 예쁜 폼으로 기록해두었습니다!<br> 여행일지를 이미지/pdf로 저장할 수 있습니다.<br> 또 카카오톡으로 친구에게 "인생N컷"을 추천할 수도 있습니다!

<br><br>

### 6. 은하갤러리

나에게 남기는 한마디를 공개하게 되면, 은하갤러리에서 다른사람들의 한마디를 볼 수 있습니다!<br>
![은하갤러리](https://user-images.githubusercontent.com/77991314/202927658-1c553766-8ffe-40b4-aea0-01ad32664a7e.gif)
<br><br>

### 7. 마이페이지

마이페이지에서는 내 정보, 최근 나의 여행 횟수 및 행성 방문 횟수를 확인할 수 있습니다.<br>
그리고, 여행일지 목록들을 살펴볼 수가 있습니다!
특정 여행일지를 누르면 상세정보로 들어가게 됩니다.

<br><br>

## 프로젝트 산출물 📝

- [와이어프레임(Figma)](https://www.figma.com/file/jKFjqyaXvcYGDIyHLq5BYl/B105?node-id=0%3A1&t=04b1XfOPTZ72lgKM-1)
- [기능 명세서](https://sunset-offer-934.notion.site/d4233d99c403414f81e9da5306e2a9b2?v=0ba1cfc3542e4e05bc9ea5da4a309934)
- [API 명세서](https://sunset-offer-934.notion.site/8ba5e0dfb8bc4b69819fbbdc978fe168?v=81f9aad2d5af4ae7913dd7edecd18c8a)
- [ERD](hhttps://www.erdcloud.com/d/P7es8HWTE43zREonr)
- [회의록](https://sunset-offer-934.notion.site/864cc75cf62f46db88d5fed1cd93c392)
- [데일리 스크럼](https://sunset-offer-934.notion.site/74a1a2fd88574e98a9b18b3a0547d4c8)
- [포팅 메뉴얼]()
- [외부 API 문서]()
- [시연 시나리오]()

<br><br>

## 개발 문화 💁‍♂️

- 데일리 스크럼을 매일 진행
- 간단한 회의부터 중요한 회의, 모든것을 노션에 기록하는 습관 📝
- 모든 의견을 존중하기!
- 함께 성장하기 위한 코드 리뷰
- 단순한 기능 구현 보다는 좋은 코드를 작성하려 노력하기
  <img width="800" src="https://user-images.githubusercontent.com/60915285/202886678-41bb6126-16c1-4ec8-b30f-84e885ac267c.png">
  <img width="800" src="https://user-images.githubusercontent.com/60915285/202887021-09f36d65-84ab-4acf-9342-d4b21674fe20.png">
  <img width="600" src="https://user-images.githubusercontent.com/60915285/202887023-6c58a4f1-3435-40ec-8ef2-903533ca1fdc.png">

<br><br>

## 팀원 소개 👪

<table>
    <tr>
        <td height="140px" align="center"> <a href="https://github.com/Qulip">
            <img src="https://avatars.githubusercontent.com/Qulip" width="140px" /> <br><br> 👑 유일권 <br>(Back-End) </a> <br></td>
        <td height="140px" align="center"> <a href="https://github.com/platycodonv">
            <img src="https://avatars.githubusercontent.com/platycodonv" width="140px" /> <br><br> 🦀 김성수 <br>(Back-End) </a> <br></td>
        <td height="140px" align="center"> <a href="https://github.com/trifoliateorange">
            <img src="https://avatars.githubusercontent.com/trifoliateorange" width="140px" /> <br><br> 🦥 이정건 <br>(Front-End) </a> <br></td>
        <td height="140px" align="center"> <a href="https://github.com/kwanukwanu">
            <img src="https://avatars.githubusercontent.com/kwanukwanu" width="140px" /> <br><br> 🐬 최재현 <br>(Front-End) </a> <br></td>
        <td height="140px" align="center"> <a href="https://github.com/choijoohee213">
            <img src="https://avatars.githubusercontent.com/choijoohee213" width="140px" /> <br><br> 🐰 최주희 <br>(Back-End) </a> <br></td>
        <td height="140px" align="center"> <a href="https://github.com/niinp28">
            <img src="https://avatars.githubusercontent.com/niinp28" width="140px" /> <br><br> 🐯 최준혁 <br>(Front-End) </a> <br></td>
    </tr>
</table>

<br><br><br>
