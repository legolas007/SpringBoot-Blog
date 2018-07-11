package com.usher.springboot.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Author: Usher
 * @Description:
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 600)
public class SessionConfig {

}
