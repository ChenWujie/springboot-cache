package com.cwj.cache;

import com.cwj.cache.bean.Employee;
import com.cwj.cache.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SpringbootCacheApplicationTests {
    @Autowired
    EmployeeMapper employeeMapper;
    @Test
    void contextLoads() {
        Employee employee = employeeMapper.getEmpById(1);
        System.out.println(employee);
    }

}
