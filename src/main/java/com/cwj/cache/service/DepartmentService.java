package com.cwj.cache.service;

import com.cwj.cache.bean.Department;
import com.cwj.cache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "dept")
public class DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Cacheable(key = "#id")
    public Department getDeptById(Integer id) {
        return departmentMapper.getDepartmentById(id);
    }
}
