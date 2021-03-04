# 영어 공부 / 단어장 애플리케이션 

Java로 작성했던 rmainf를 리팩토링 하였습니다.
<br>
통신에 사용된 서버는 json데이터를 응답해주는 서버를 제작하여 사용하였습니다.
<br><br>
## 사용 기술

- AAC
- ViewModel , LiveData , Room , Repository 
- Retrofit
- Databinding
<br><br>

## 기능 

Retrofit 통신으로 서버에서 영어데이터를 받아온 뒤 Room에 데이터를 저장합니다.<br>
Livedata , databinding 활용하여 Room에서 영어 단어와 뜻을 화면에 뿌려줍니다. 


1. 메인화면 
<br> 

![메인](https://user-images.githubusercontent.com/58478058/109963568-e989f700-7d2f-11eb-98df-aefc67459850.JPG)
![단어추가](https://user-images.githubusercontent.com/58478058/109963383-b3e50e00-7d2f-11eb-95b8-3b221edd899b.JPG)

<br>

<br><br>
마이크버튼 -> 단어 읽어주기 <br> 
왼쪽하단 +버튼 -> 단어장에추가 <br> 
우측상단 버튼 -> 단어장 화면이동

<br><br>
2. 내 단어장
<br>
![내단어](https://user-images.githubusercontent.com/58478058/109962745-e93d2c00-7d2e-11eb-8cbe-1d0ad3f48feb.JPG)
![단어장추가](https://user-images.githubusercontent.com/58478058/109962925-26a1b980-7d2f-11eb-90d3-3272c019efbd.JPG)
![단어장삭제](https://user-images.githubusercontent.com/58478058/109962864-112c8f80-7d2f-11eb-80e0-9d55b42894a6.JPG)
![추가완료](https://user-images.githubusercontent.com/58478058/109962932-27d2e680-7d2f-11eb-9d66-ad825c3a1ec5.JPG)

<br><br>
단어장에서 사용자가 필요한 단어 추가가능 <br>
recyclerview에서 item longclick시 단어 삭제 가능 <br>


