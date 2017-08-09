package com.txl.demo.configration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by TeaBee on 2017/8/8.
 */
@Configuration
public class RestConnectionFactory {

    @Bean
    public RestConnection getRestConnection(){
        return new RestConnection();
    }
}
