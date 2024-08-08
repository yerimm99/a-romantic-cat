# 🐈‍⬛ 낭만고양이
<br>

## ✨ 프로젝트 소개
- 모바일 편지 서비스
- 개발 기간: 2024. 01 ~ 2024. 02
- 진행 인원: FE 4, BE 4, PM 1, DESIGNER 1
- 주요 기능
  - 소셜 로그인 
  - 내 우편함
    - 편지 작성, 수신 
  - 낭만 우편함
    - 편지 익명 작성, 랜덤 수신 
  - 느린 우체통
    - 일정 기간 뒤에 수신받는 편지 
  - 상점
    - 미션 성공시 지급받는 코인으로 편지지, 우표 아이템 구매

<br><br>

## 🔧 Skills

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=OpenJDK&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/spring data JPA-3DB33F?style=for-the-badge&logo=springdataJPA&logoColor=white"> <img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white"> ![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens) <img src="https://img.shields.io/badge/OAuth2.0-EB5424?style=for-the-badge&logo=OAuth2.0&logoColor=white"> <img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white"> <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white"> <img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=Redis&logoColor=white"> <img src="https://img.shields.io/badge/Amazon%20EC2-FF9900?style=for-the-badge&logo=Amazon%20EC2&logoColor=white"> <img src="https://img.shields.io/badge/Amazon%20RDS-%23009639.svg?style=for-the-badge&logo=Amazon%20RDS&logoColor=white"> <img src="https://img.shields.io/badge/Amazon%20EB-981E32.svg?style=for-the-badge&logo=Amazon%20EasticBeanstalk&logoColor=white"> <img src="https://img.shields.io/badge/Amazon%20S3-5C1F87?style=for-the-badge&logo=Amazon%20S3&logoColor=white"> <img src="https://img.shields.io/badge/Amazon%20Elasticache-005571?style=for-the-badge&logo=Amazon%20Elasticache&logoColor=white"> <img src="https://img.shields.io/badge/GitHub Actions-2088FF?style=for-the-badge&logo=GitHub Actions&logoColor=white"> <img src="https://img.shields.io/badge/Swagger-E9568E?style=for-the-badge&logo=Swagger&logoColor=white"> 
<img src="https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=React&logoColor=white">

<br><br>
## 📃 Commit Message Convention
|커밋명|내용|
|:------|:---|
|✨ Feat|파일, 폴더, 새로운 기능 추가|
|🐛 Fix|버그 수정|
|🎨 Style|코드 포맷 변경, 세미 콜론 누락, 코드 수정이 없는 경우|
|♻️ Refactor|프로덕션 코드 리팩토링|
|💡 Comment|필요한 주석 추가 및 변경|
|📝 Docs|README 등의 문서 수정|
|🔧 Chore|환경설정, 빌드 업무, 패키지 매니저 설정 등(프로덕션 코드 변경 X)|
|✏️ Rename|파일 혹은 폴더명을 수정하거나 옮기는 작업만 수행한 경우|
|🔥 Remove|파일 등을 삭제하는 작업만 수행한 경우|
|🚑️ !HOTFIX|급하게 치명적인 버그를 고쳐야하는 경우|
> 예시: "✨ 로그인 API 연동"

<br><br>

## 🌲 Backend
```.
├── main
│   ├── java
│   │   └── aromanticcat
│   │       └── umcproject
│   │           ├── S3
│   │           │   ├── S3Service.java
│   │           │   └── config
│   │           │       └── AmazonConfig.java
│   │           ├── UmcProjectApplication.java
│   │           ├── apiPayload
│   │           │   ├── ApiResponse.java
│   │           │   ├── code
│   │           │   │   ├── BaseCode.java
│   │           │   │   ├── BaseErrorCode.java
│   │           │   │   ├── ErrorReasonDTO.java
│   │           │   │   ├── ReasonDTO.java
│   │           │   │   └── status
│   │           │   │       ├── ErrorStatus.java
│   │           │   │       └── SuccessStatus.java
│   │           │   └── exception
│   │           │       ├── ExceptionAdvice.java
│   │           │       ├── GeneralException.java
│   │           │       └── handler
│   │           │           └── MemberHandler.java
│   │           ├── config
│   │           │   ├── CorsConfig.java
│   │           │   ├── S3Config.java
│   │           │   └── SwaggerConfig.java
│   │           ├── converter
│   │           │   ├── FriendConverter.java
│   │           │   ├── MemberConverter.java
│   │           │   ├── MissionConverter.java
│   │           │   ├── MyCollectionConverter.java
│   │           │   ├── NangmanCollectionConverter.java
│   │           │   ├── NangmanLetterBoxConverter.java
│   │           │   └── StoreConverter.java
│   │           ├── entity
│   │           │   ├── AcquiredItem.java
│   │           │   ├── BaseEntity.java
│   │           │   ├── Friend.java
│   │           │   ├── FriendStatus.java
│   │           │   ├── Letter.java
│   │           │   ├── LetterPaper.java
│   │           │   ├── Letterbox.java
│   │           │   ├── Member.java
│   │           │   ├── MemberMission.java
│   │           │   ├── Mission.java
│   │           │   ├── MissionStatus.java
│   │           │   ├── MyLetterPaper.java
│   │           │   ├── MyStamp.java
│   │           │   ├── NangmanLetter.java
│   │           │   ├── NangmanReply.java
│   │           │   ├── Reward.java
│   │           │   ├── SlowLetter.java
│   │           │   ├── Stamp.java
│   │           │   └── Uuid.java
│   │           ├── repository
│   │           │   ├── AcquiredItemRepository.java
│   │           │   ├── FriendRepository.java
│   │           │   ├── LetterBoxRepository.java
│   │           │   ├── LetterPaperRepository.java
│   │           │   ├── LetterRepository.java
│   │           │   ├── MemberMissionRepository.java
│   │           │   ├── MemberRepository.java
│   │           │   ├── MissionRepository.java
│   │           │   ├── MyLetterPaperRepository.java
│   │           │   ├── MyStampRepository.java
│   │           │   ├── NangmanLetterRepository.java
│   │           │   ├── NangmanReplyRepository.java
│   │           │   ├── RefreshTokenRepository.java
│   │           │   ├── SlowLetterRepository.java
│   │           │   ├── StampRepository.java
│   │           │   └── UuidRepository.java
│   │           ├── security
│   │           │   ├── Role.java
│   │           │   ├── SecurityConfig.java
│   │           │   ├── SecurityUserDto.java
│   │           │   ├── StatusResponseDto.java
│   │           │   ├── jwt
│   │           │   │   ├── GeneratedToken.java
│   │           │   │   ├── JwtAuthFilter.java
│   │           │   │   ├── JwtExceptionFilter.java
│   │           │   │   ├── JwtProperties.java
│   │           │   │   ├── JwtUtil.java
│   │           │   │   ├── RefreshToken.java
│   │           │   │   └── RefreshTokenService.java
│   │           │   ├── oauth
│   │           │   │   ├── CustomOAuth2UserService.java
│   │           │   │   ├── MyAuthenticationFailureHandler.java
│   │           │   │   ├── MyAuthenticationSuccessHandler.java
│   │           │   │   └── OAuth2Attribute.java
│   │           │   └── redis
│   │           │       ├── RedisConfig.java
│   │           │       └── RedisProperties.java
│   │           ├── service
│   │           │   ├── FriendService
│   │           │   │   ├── FriendCommandService.java
│   │           │   │   ├── FriendCommandServiceImpl.java
│   │           │   │   ├── FriendQueryService.java
│   │           │   │   └── FriendQueryServiceImpl.java
│   │           │   ├── MemberService.java
│   │           │   ├── MemberServiceImpl.java
│   │           │   ├── MissionService
│   │           │   │   ├── MissionCommandService.java
│   │           │   │   ├── MissionCommandServiceImpl.java
│   │           │   │   ├── MissionQueryService.java
│   │           │   │   └── MissionQueryServiceImpl.java
│   │           │   ├── StampService.java
│   │           │   ├── letterbox
│   │           │   │   ├── LetterService.java
│   │           │   │   └── LetterboxService.java
│   │           │   ├── myCollectionService
│   │           │   │   ├── MyCollectionService.java
│   │           │   │   ├── MyCollectionServiceImpl.java
│   │           │   │   └── MyDesignService.java
│   │           │   ├── nangmanLetterboxService
│   │           │   │   ├── NangmanCollectionService.java
│   │           │   │   ├── NangmanCollectionServiceImpl.java
│   │           │   │   ├── NangmanLetterboxService.java
│   │           │   │   ├── NangmanLetterboxServiceImpl.java
│   │           │   │   └── RandomNicknameService.java
│   │           │   ├── slowLetter
│   │           │   │   └── SlowLetterService.java
│   │           │   └── storeService
│   │           │       ├── StoreService.java
│   │           │       └── StoreServiceImpl.java
│   │           └── web
│   │               ├── controller
│   │               │   ├── AuthController.java
│   │               │   ├── FriendController.java
│   │               │   ├── LetterController.java
│   │               │   ├── LetterboxController.java
│   │               │   ├── MemberController.java
│   │               │   ├── MissionController.java
│   │               │   ├── MyCollectionController.java
│   │               │   ├── MyDesignController.java
│   │               │   ├── NangmanCollectionController.java
│   │               │   ├── NangmanLetterboxController.java
│   │               │   ├── RootController.java
│   │               │   ├── SlowLetterController.java
│   │               │   └── StoreController.java
│   │               └── dto
│   │                   ├── Friend
│   │                   │   ├── FriendRequestDTO.java
│   │                   │   └── FriendResponseDTO.java
│   │                   ├── Letterbox
│   │                   │   ├── LetterRequest.java
│   │                   │   ├── LetterResponse.java
│   │                   │   ├── LetterboxRequest.java
│   │                   │   └── LetterboxResponse.java
│   │                   ├── Member
│   │                   │   ├── MemberRequestDTO.java
│   │                   │   └── MemberResponseDTO.java
│   │                   ├── Mission
│   │                   │   └── MissionResponseDTO.java
│   │                   ├── MyCollectionResponseDTO.java
│   │                   ├── MyDesign
│   │                   │   ├── MyDesignGetResponse.java
│   │                   │   └── MyDesignRequest.java
│   │                   ├── nangmanLetterbox
│   │                   │   ├── NangmanCollectionResponseDTO.java
│   │                   │   ├── NangmanLetterBoxResponseDTO.java
│   │                   │   └── NangmanLetterboxRequestDTO.java
│   │                   ├── slowLetter
│   │                   │   ├── SlowLetterCalResponse.java
│   │                   │   ├── SlowLetterGetResponse.java
│   │                   │   ├── SlowLetterRequest.java
│   │                   │   └── SlowLetterResponse.java
│   │                   └── store
│   │                       └── StoreResponseDTO.java
│   └── resources
│       └── application.yml
└── test
    └── java
        └── aromanticcat
            └── umcproject
                └── UmcProjectApplicationTests.java

```
<br><br>

## 🌲 Frontend
```

.
├── README.md
├── package-lock.json
├── package.json
├── public
│   ├── images
│   ├── index.html
│   ├── manifest.json
│   └── robots.txt
├── src
│   ├── App.css
│   ├── App.js
│   ├── App.test.js
│   ├── assets
│   │   └── img
│   ├── components
│   │   ├── AddressBook
│   │   │   ├── Address
│   │   │   │   ├── Address.jsx
│   │   │   │   └── AddressList.jsx
│   │   │   ├── AddressBookMain.jsx
│   │   │   ├── Friends
│   │   │   │   ├── Friends.jsx
│   │   │   │   └── FriendsList.jsx
│   │   │   ├── PlusFriends
│   │   │   │   ├── FindFriend.jsx
│   │   │   │   ├── NothingFriend.jsx
│   │   │   │   ├── PlusFriends.jsx
│   │   │   │   ├── ReceiveFriend.jsx
│   │   │   │   └── SendFriend.jsx
│   │   │   └── SearchForm.jsx
│   │   ├── Footer
│   │   │   └── Footer.jsx
│   │   ├── Header
│   │   │   └── Header.jsx
│   │   ├── Home
│   │   │   └── Mainpage.jsx
│   │   ├── Login
│   │   │   ├── BoxSetting1.jsx
│   │   │   ├── BoxSetting2.jsx
│   │   │   ├── BoxSetting3.jsx
│   │   │   ├── Calender.jsx
│   │   │   ├── Login.jsx
│   │   │   ├── Logincontrol.jsx
│   │   │   ├── Loginstart.jsx
│   │   │   ├── MakeLetterbox.jsx
│   │   │   ├── RenderCells.jsx
│   │   │   ├── SettingEnd.jsx
│   │   │   ├── Signin.jsx
│   │   │   ├── Signup.jsx
│   │   │   └── Terms.jsx
│   │   ├── MyLetterbox
│   │   │   ├── Answer1.jsx
│   │   │   ├── Answer2.jsx
│   │   │   ├── Answer3.jsx
│   │   │   ├── Answer4.jsx
│   │   │   ├── Check1.jsx
│   │   │   ├── Check2.jsx
│   │   │   ├── Info.jsx
│   │   │   ├── MyLetterboxMain.jsx
│   │   │   ├── OpenLetter1.jsx
│   │   │   ├── OpenLetter2.jsx
│   │   │   ├── PastLetterbox1.jsx
│   │   │   ├── PastLetterbox2.jsx
│   │   │   ├── PastLetterbox3.jsx
│   │   │   ├── PastLetterboxModal.jsx
│   │   │   └── SlowLetterboxToday.jsx
│   │   ├── MyPage
│   │   │   ├── Delete.jsx
│   │   │   ├── Logout.jsx
│   │   │   ├── MyPageMain.jsx
│   │   │   ├── Privacy.jsx
│   │   │   └── Use.jsx
│   │   ├── RomanticLetterbox
│   │   │   ├── Collection
│   │   │   │   ├── CollectionLetter.jsx
│   │   │   │   ├── CollectionMain.jsx
│   │   │   │   ├── MyCollection.jsx
│   │   │   │   └── MyWriting.jsx
│   │   │   ├── ReplyingLetter
│   │   │   │   ├── CompletedLetterReplying.jsx
│   │   │   │   ├── ReplyingLetter.jsx
│   │   │   │   └── ReplyingLetterMain.jsx
│   │   │   ├── RomainticLetterboxBackground.jsx
│   │   │   ├── RomainticLetterboxMain.jsx
│   │   │   └── WritingLetter
│   │   │       ├── CompletedLetterWriting.jsx
│   │   │       └── WritingLetter.jsx
│   │   └── Store
│   │       ├── CollectionBoxMain.jsx
│   │       ├── MissionMain.jsx
│   │       └── StoreMain.jsx
│   ├── index.css
│   ├── index.js
│   ├── logo.svg
│   ├── pages
│   │   ├── AddressBook.jsx
│   │   ├── Home.jsx
│   │   ├── Login.jsx
│   │   ├── MyLetterbox.jsx
│   │   ├── MyPage.jsx
│   │   ├── RomanticLetterbox.jsx
│   │   └── Store.jsx
│   ├── redux
│   │   └── completeMission.js
│   ├── reportWebVitals.js
│   ├── setupTests.js
│   └── store.js
└── yarn.lock


```
<br><br>

## IA
![image](https://github.com/user-attachments/assets/15a79043-9da1-4b74-ace7-b12836eb419d)
<br><br>

## UI
![image](https://github.com/user-attachments/assets/44288819-750c-4068-9a43-c16e98f62bbc)
![Untitled](https://github.com/user-attachments/assets/3a765004-9e8b-4417-b77a-e35fea720831)

<br><br>
