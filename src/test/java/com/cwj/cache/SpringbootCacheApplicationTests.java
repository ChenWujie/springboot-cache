package com.cwj.cache;

import com.cwj.cache.bean.Department;
import com.cwj.cache.bean.Employee;
import com.cwj.cache.mapper.EmployeeMapper;
import com.cwj.rabbitmq.bean.Book;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@SpringBootTest
class SpringbootCacheApplicationTests {
    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    RedisTemplate redisTemplate;    //操作k-v都是对象的

    @Autowired
    StringRedisTemplate stringRedisTemplate;    //操作k-v都是字符串的

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void createExchange(){   //创建一个DirectExchange
        //amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));

        //amqpAdmin.declareQueue(new Queue("amqpadmin.queue", true));

        //创建绑定规则
        amqpAdmin.declareBinding(new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE, "amqpadmin.exchange", "amqp.hh", null));
    }


    @Test
    public void contextLoads1(){
        Map<String ,Object> map = new HashMap<>();
        map.put("msg", "这是第一条消息");
        map.put("data", Arrays.asList("helloworld", 456, true));
        rabbitTemplate.convertAndSend("exchange.direct", "atguigu.news", new Book("红楼梦", 58.50));
    }

    @Test
    public void receiveMsg(){
        Object o = rabbitTemplate.receiveAndConvert("atguigu.emps");
        System.out.println(o.getClass());
        System.out.println(o);
    }
    @Test
    void redisTest() {
        String s = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(s);
    }

    @Test
    void contextLoads() {
        Employee employee = employeeMapper.getEmpById(1);
        System.out.println(employee);
    }

}
