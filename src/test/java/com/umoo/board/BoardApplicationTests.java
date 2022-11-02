package com.umoo.board;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class BoardApplicationTests {
	@Autowired
	Environment environment;

	@Test
	void contextLoads() {
	}

	@Test
	void 프로필_적용_여부(){
		String[] activeProfiles = environment.getActiveProfiles();
		assertThat(activeProfiles).containsExactly("prod");
	}

}
