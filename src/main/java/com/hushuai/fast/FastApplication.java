package com.hushuai.fast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
/* session使用redis共享的注解 */
@EnableRedisHttpSession
public class FastApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastApplication.class, args);
    }

}
