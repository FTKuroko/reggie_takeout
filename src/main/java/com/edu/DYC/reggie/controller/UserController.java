package com.edu.DYC.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edu.DYC.reggie.Utils.SMSUtils;
import com.edu.DYC.reggie.Utils.ValidateCodeUtils;
import com.edu.DYC.reggie.common.R;
import com.edu.DYC.reggie.entity.User;
import com.edu.DYC.reggie.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author :   Kuroko
 * @date :     2023/2/25
 */
@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 发送手机短信验证码
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session){
        // 获取手机号
        String phone = user.getPhone();
        if(StringUtils.isNotEmpty(phone)){
            // 生成随机的4位验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code:{}", code);

            // 调用阿里云提供的短信服务 API 完成发送短信
            //SMSUtils.sendMessage("瑞吉外卖", "", phone, code);

            // 需要将生成的验证码保存到 session
            session.setAttribute(phone, code);
            return R.success("手机验证码短信发送成功");
        }

        return R.success("手机短信发送失败");
    }

    /**
     * 移动端登录
     * @param map
     * @param session
     * @return
     */
    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session){
        // 获取手机号
        String phone = map.get("phone").toString();

        // 获取验证码
        String code = map.get("code").toString();

        // 从 session 中获取保存的验证码
        Object codeInSession = session.getAttribute(phone);

        // 进行验证码的比对
        if(codeInSession != null && codeInSession.equals(code)){
            // 如果能够比对成功，说明登录成功
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone, phone);

            User user = userService.getOne(queryWrapper);
            if(user == null){
                // 判断当前手机号对应的用户是否为新用户，如果为新用户就自动完成注册
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user", user.getId());
            return R.success(user);
        }

        return R.error("登录失败");
    }

    /**
     * 用户登出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        // 清理 Session 中保存的当前用户 id
        request.getSession().removeAttribute("user");
        return R.success("退出成功");
    }
}
