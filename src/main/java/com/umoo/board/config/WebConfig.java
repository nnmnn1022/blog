package com.umoo.board.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${custom.path.upload-images}")
    private String connectPath;

    @Value("${custom.path.files}")
    private String resourcePath;


    /**
     * WebMvcConfigurer interface를 상속받아 addResourceHandlers method를 overriding하고
     * 리소스 등록 및 핸들러를 관리하는 객체인 ResourceHandlerRegistry를 통해
     * 리소스의 위치와 리소스와 매칭 될 url을 설정합니다.
     *
     * addResourceHandler : 리소스와 연결될 URL path를 지정합니다. (클라이언트가 파일에 접근하기 위해 요청하는 url)
     * addResourceLocations: 실제 리소스가 존재하는 외부 경로를 지정합니다.
     * 경로의 마지막은 반드시 " / "로 끝나야 하고, 로컬 디스크 경로일 경우 file:/// 접두어를 꼭 붙여야 합니다.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler(connectPath)
                .addResourceLocations(resourcePath);
    }

    /**
     * CKFinder의 return : json
     * uploaded, url을 포함하고 있음.
     * controller가 modelandview를 통해 json 형식으로 return 할 수 있도록 설정
     */
    @Bean
    MappingJackson2JsonView jsonView() {
        return new MappingJackson2JsonView();
    }


}
