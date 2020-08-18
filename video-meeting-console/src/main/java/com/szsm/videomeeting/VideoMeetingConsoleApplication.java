package com.szsm.videomeeting;

import com.szsm.videomeeting.base.config.MyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableConfigurationProperties({MyProperties.class})
@EnableTransactionManagement
public class VideoMeetingConsoleApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoMeetingConsoleApplication.class, args);
	}

}
