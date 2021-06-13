package com.cwj.rabbitmq.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyAmqpConfig {

    @Bean
    public MessageConverter messageConverter(){
        //不使用默认的java序列化
        return new Jackson2JsonMessageConverter();
    }
}
