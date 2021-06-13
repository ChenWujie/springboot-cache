package com.cwj.cache.controller;

import com.cwj.cache.bean.Department;
import com.cwj.cache.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @RequestMapping("/dept/{id}")
    public Department getDeptById(@PathVariable Integer id) {
        return departmentService.getDeptById(id);
    }
}
