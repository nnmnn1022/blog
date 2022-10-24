# 오류 모음
### o.h.engine.jdbc.spi.SqlExceptionHelper   : Zero date value prohibited
- MySQL에서 timestamp 컬럼을 조회할 때 발생하는데 값이 zero date(0000-00-00 00:00:00)로 저장되어 있는 경우에 java의 date 객체로 변환화는 과정에서 발생합니다.
- 문제를 해결하기 위해서는 datasource 설정에서 jdbc url 뒤에 다음과 같이 zeroDateTimeBehavior=convertToNull 옵션을 추가해주면 됩니다.
```jdbc:mysql://{IP}:{PORT}/{DB_NAME}?zeroDateTimeBehavior=convertToNull```

출처: https://freestrokes.tistory.com/123 [FREESTROKES DEVLOG:티스토리]

