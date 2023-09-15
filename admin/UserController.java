package org.example.controller;

import org.example.exception.AppException;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public Object register(User user, MultipartFile headFile){

        if(headFile != null) {
            String head = userService.saveHead(headFile);

            user.setHead(head);
        }
        userService.register(user);

        return null;
    }

    @PostMapping("/login")
    public Object login(@RequestBody User user, HttpServletRequest req){//username, password
        //根据账号查用户
        User exist = userService.queryByUsername(user.getUsername());
        //用户不存在
        if(exist == null) throw new AppException("LOG001", "用户不存在");
        //用户存在，校验密码
        if(!user.getPassword().equals(exist.getPassword()))
            throw new AppException("LOG002", "账号或密码错误");
        //校验通过，保存数据库的用户(包含所有字段)到session
        HttpSession session = req.getSession();//先创建session
        session.setAttribute("user", exist);
        return null;//登录成功
    }

    @GetMapping("/logout")
    public Object logout(HttpSession session){
        session.removeAttribute("user");
        return null;
    }
}
