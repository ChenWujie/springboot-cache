package com.cwj.cache;

import com.cwj.cache.bean.Employee;
import com.cwj.cache.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;


@SpringBootTest
class SpringbootCacheApplicationTests {
    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    RedisTemplate redisTemplate;    //操作k-v都是对象的

    @Autowired
    StringRedisTemplate stringRedisTemplate;    //操作k-v都是字符串的

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
