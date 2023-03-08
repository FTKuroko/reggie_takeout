package com.edu.DYC.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.DYC.reggie.common.R;
import com.edu.DYC.reggie.entity.Employee;
import com.edu.DYC.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author :   Kuroko
 * @date :     2023/2/23
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    /**
     * 登录
     * 业务逻辑
     * 1、将页面提交的密码 password 进行 MD5 加密处理
     * 2、根据页面提交的用户名 username 查询数据库
     * 3、如果没有查询到则返回登录失败结果
     * 4、密码比对，如果不一致则返回登录失败结果
     * 5、查看员工状态，如果为已禁用状态，则返回员工已禁用结果
     * 6、登录成功，将员工 id 存入 Session 并返回登录成功结果
     * @param request   前端传来的请求
     * @param employee  前端传来的 json 数据
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        // 1、将页面提交的密码 password 进行 MD5 加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        // 2、根据页面提交的用户名 username 查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        // 3、如果没有查询到则返回登录失败结果
        if(emp == null){
            return R.error("登录失败");
        }

        // 4、密码比对，如果不一致则返回登录失败结果
        if(!emp.getPassword().equals(password)){
            return R.error("密码错误");
        }

        // 5、查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        if(emp.getStatus() == 0){
            return R.error("账号已禁用");
        }

        // 6、登录成功，将员工 id 存入 Session 并返回登录成功结果
        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);
    }

    /**
     * 员工退出
     * 将当前员工 id 从 Session 中删除
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        // 清理 Session 中保存的当前员工登录 id
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    /**
     * 新增员工
     * 1、从前端页面接收到 json 数据
     * 2、补全数据库中相应字段值
     * 3、Service 调用 Mapper 保存数据
     * @param request
     * @param employee
     * @return
     */
    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee){

        log.info("新增员工，员工信息:{}", employee.toString());
        // 设置员工初始密码 123456, 需要进行 MD5 加密处理
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
//        // 给新增员工的其他字段赋值
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
//        // 表示该员工是由谁新增的
//        Long empId = (Long) request.getSession().getAttribute("employee");
//        employee.setCreateUser(empId);
//        employee.setUpdateUser(empId);
        employeeService.save(employee);

        return R.success("新增员工成功");
    }

    /**
     * 员工信息分页查询
     * 1、页面发送 ajax 请求，将分页查询参数(page, pageSize, name) 提交到服务端
     * 2、服务端调用 Service 查询数据
     * 3、Service 调用 Mapper 操作数据库，查询分页数据
     * 4、页面展示
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        log.info("page = {}, pageSize = {}, name = {}", page, pageSize, name);

        // 分页构造器
        Page pageInfo = new Page(page, pageSize);

        // 条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        // 过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name);
        // 添加排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);

        employeeService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 根据 id 修改员工信息
     * 只有管理员可以修改，普通用户界面没有启用、禁用按钮
     * 前端已经处理好了，所以该方法只有管理员会用到，就不需要判断当前用户是否是管理员了
     * @param request
     * @param employee
     * @return
     */
    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee){
        log.info(employee.toString());

//        Long empId = (Long) request.getSession().getAttribute("employee");
//        employee.setUpdateTime(LocalDateTime.now());
//        employee.setUpdateUser(empId);
        employeeService.updateById(employee);

        return R.success("员工信息修改成功");
    }

    /**
     * 编辑员工
     * 根据前端传来的 id 获取员工信息并修改
     * 再将修改后的员工信息返回给前端页面
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable String id){
        log.info("根据 id 查对象");
        Employee employee = employeeService.getById(id);
        if(employee != null){
            return R.success(employee);
        }
        return R.error("没有查到该用户信息");
    }
}
