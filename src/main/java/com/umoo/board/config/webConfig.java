package com.umoo.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class webConfig {

    /**
     * CKFinder의 return : json
     * uploaded, url을 포함하고 있음.
     * controller가 modelandview를 통해 json 형식으로 return 할 수 있도록 설정
      */
    @Configuration
    public class WebConfig {

        @Bean
        MappingJackson2JsonView jsonView() {
            return new MappingJackson2JsonView();
        }
    }
}
