package com.cwj.cache.service;

import com.cwj.cache.bean.Employee;
import com.cwj.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 将方法的返回值进行缓存，以后再要相同的数据，直接从缓存获取
     *
     * CacheManager管理多个Cache组件，对缓存的CRUD操作在Cache组件中，每一个缓存有自己唯一的名字
     * 几个属性：
     *      cacheName/value：指定缓存的名字，是数组的方式，可以指定多个缓存
     *      key：缓存数据使用的key，可以它指定。默认是方法参数的值
     *      keyGenerator：key的生成器，可以自己指定key的生成器的组件id
     *          key/keyGenerator 二选一
     *      cacheManager：指定缓存管理器，或者cacheResolver获取指定解析器
     *      condition：指定符合条件的情况下才缓存
     *      unless：指定的条件返回true时不缓存
     *      sync：是否使用异步模式
     *
     * 运行流程：
     * @Cacheable
     * 1、方法运行之前，先去查询Cache（缓存组件），按照cacheNames指定的名字获取
     *      （CacheManager先获取相应的缓存），第一次获取缓存如果没有Cache组件会自动创建
     * 2、去Cache中查找缓存的内容，使用一个key，默认就是方法的参数
     *      key是按照某种策略生成的，默认是使用keyGenerator生成，默认使用SimpleKeyGenerator生成key
     *          SimpleKeyGenerator生成key的策略：
     *              如果没有参数：key = new SimpleKeyGenerator(),
     *              如果有一个参数：key = 参数值,
     *              如果有多个参数：key = new SimpleKeyGenerator(params)
     * 3、没有查到目标缓存就调用目标方法
     * 4、将目标方法的返回值放入缓存
     *
     * @Cacheable标注的方法执行之前先检查缓存中有没有这个数据，默认按照参数的值作为key去查询缓存
     * 如果没有就将方法返回结果放入缓存
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = {"emp"}, unless = "#result==null") //condition="#id>1"
    public Employee getEmployee(Integer id){
        System.out.println("查询开始");
        Employee employee = employeeMapper.getEmpById(id);
        return employee;
    }

    /**
     * @CachePut ：即调用方法，又更新缓存
     * 修改了数据库中的某条数据，同时又更新缓存
     * 运行时机：
     * 1、先调用目标方法
     * 2、将目标方法的结果缓存
     *      将目标方法返回值放入缓存时，缓存的key是Employee对象了，缓存中就会存在两条缓存数据，
     *      一条是修改前的（key=id），一条是修改后的（key=employee），
     *      因此需要给CachePut指定key（修改前和修改后要是同一个key）
     *      1 key = #employee.id
     *      2 key = #result.id （#result是方法的返回值）
     * @Cacheable 的key是不能用#result取的，因为在方法运行之前就需要key
     */
    @CachePut(value = "emp", key = "#result.id")
    public Employee updateEmp(Employee employee) {
        employeeMapper.updateEmp(employee);
        return employee;
    }

}
