package com.example.demobk.controller;

import com.example.demobk.common.AjaxResult;
import com.example.demobk.common.AppVariable;
import com.example.demobk.common.PasswordUtils;
import com.example.demobk.common.UserSessionUtils;
import com.example.demobk.entity.Userinfo;
import com.example.demobk.entity.vo.UserinfoVO;
import com.example.demobk.service.ArticleService;
import com.example.demobk.service.UserService;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/reg")
    public AjaxResult reg(Userinfo userinfo){

        if (userinfo == null || !StringUtils.hasLength(userinfo.getUsername())||!StringUtils.hasLength(userinfo.getPassword()))
        {
            return AjaxResult.fail(-1,"非法参数");
        }
        userinfo.setPassword(PasswordUtils.encrypt(userinfo.getPassword()));

       return AjaxResult.success(userService.reg(userinfo));
    }
    @RequestMapping("/login")
    public  AjaxResult login(HttpServletRequest request,String username, String password){
        if (!StringUtils.hasLength(username)||!StringUtils.hasLength(password)){

            return AjaxResult.fail(-1,"非法参数");

        }

        Userinfo userinfo = userService.getUserByName(username);
        if (userinfo == null||userinfo.getId()>0){
            if (password.equals(userinfo.getPassword())){

                HttpSession session= request.getSession();
                session.setAttribute(AppVariable.USER_SESSION_KEY,userinfo);
                    userinfo.setPassword("");
                    return AjaxResult.success(userinfo);
            }

        }
         return AjaxResult.success(0,null);
    }
    @RequestMapping("/showinfo")
    public  AjaxResult showinfo(HttpServletRequest request){

        UserinfoVO userinfoVO = new UserinfoVO();
        // 1.得到当前登录用户（从 session 中获取）
        Userinfo userinfo = UserSessionUtils.getUser(request);
        if (userinfo == null) {
            return AjaxResult.fail(-1, "非法请求");
        }
        // Spring 提供的深克隆方法
        BeanUtils.copyProperties(userinfo, userinfoVO);
        // 2.得到用户发表文章的总数
        userinfoVO.setArtCount(articleService.getArtCountByUid(userinfo.getId()));
        return AjaxResult.success(userinfoVO);



    }
    @RequestMapping("/logout")
    public AjaxResult logout(HttpSession session){
        session.removeAttribute(AppVariable.USER_SESSION_KEY);
        return AjaxResult.success(1);
    }

    @RequestMapping("/getuserbyid")
    public AjaxResult getUserById(Integer id){
        if (id==null||id<=0){
            return AjaxResult.fail(-1,"非法参数");
        }
        Userinfo userinfo = userService.getUserById(id);
        if (userinfo == null||userinfo.getId()<=0){
            return AjaxResult.fail(-1,"非法参数");

        }
        userinfo.setPassword("");
        UserinfoVO userinfoVO = new UserinfoVO();
        BeanUtils.copyProperties(userinfo,userinfoVO);
        userinfoVO.setArtCount(articleService.getArtCountByUid(id));
          return AjaxResult.success(userinfoVO);
    }

    @RequestMapping("/lists")
    public List<Userinfo> getAll(){
        return userService.getAll();
    }
    @RequestMapping("/listbypages")
    public HashMap<String,Object> getListByPage(String username,
                                                String address,
                                                String email,
                                                Integer pindex,
                                                Integer psize){
        HashMap<String,Object> result = new HashMap<>();
        if (pindex == null || pindex < 1) {
            pindex = 1;
        }
        if (psize == null || psize <= 0) {
            psize = 2;
        }
        if (!StringUtils.hasLength(username)) {
            username = null;
        }
        if (!StringUtils.hasLength(address)) {
            address = null;
        }
        if (!StringUtils.hasLength(email)) {
            email = null;
        }
        int offset = (pindex - 1) * psize;
        List<Userinfo> list = userService.getListByPage(username, address, email, psize, offset);
        int totalCount = userService.getListByPageCount(username, address, email);
        result.put("list", list);
        result.put("count", totalCount);
        return result;
    }
    //添加用户
    @RequestMapping("/adds")
    public int adds(Userinfo userinfo, HttpServletRequest request){
        int result = 0;
        //非空验证
        if (userinfo == null ||!StringUtils.hasLength(userinfo.getUsername()) ||
                !StringUtils.hasLength(userinfo.getLoginname()) ||
                !StringUtils.hasLength(userinfo.getPassword()))return result;

        //判断必须为管理员
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(AppVariable.USER_SESSION_KEY) == null)return result;
        Userinfo loginUser = (Userinfo) session.getAttribute(AppVariable.USER_SESSION_KEY);
        if (!loginUser.isIsadmin()) return result;
        //对登录进行消炎
        Userinfo loginNameUser = userService.getUserByLoginName(userinfo.getLoginname());
        if (loginNameUser != null && loginNameUser.getId() > 0)return result;
        //添加数据库
        userinfo.setPassword(PasswordUtils.encrypt(userinfo.getPassword()));
        result = userService.add(userinfo);

        return result;
    }
    @RequestMapping("/getuserbyuids")
    public  Userinfo getUserByid(Integer id){
        Userinfo userinfo = null;
        //非空晓燕
        if(id == null || id <= 0)return  userinfo;
        //查询数据库
        userinfo = userService.getUserByUid(id);
        //将密码隐藏
        userinfo.setPassword("");

        return  userinfo;
    }
    @RequestMapping("/dels")
    public int del( Integer id){
        if (id == null) return 0;
        return userService.del(id);

    }
    @RequestMapping("/delbyidss")
    public int dels(String ids, HttpServletRequest request) {
        if (!StringUtils.hasLength(ids)) return 0;
        String[] idsArr = ids.split(",");
        if (idsArr == null || idsArr.length <= 0) return 0;
        List<Integer> idsList = new ArrayList<>();
        // 得到登录用户的id
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(AppVariable.USER_SESSION_KEY) == null) return 0;
        int id = ((Userinfo) session.getAttribute(AppVariable.USER_SESSION_KEY)).getId();
        for (String item : idsArr) {
            if (StringUtils.hasLength(item)) {
                int thisid = Integer.valueOf(item);
                // 删除之前，要判断删除的数据中不包含当前登录的用户
                if (id == thisid) {
                    return 0;
                }
                idsList.add(thisid);
            }
        }
        int result = userService.dels(idsList);
        System.out.println("删除多条数据结果：" + result);
        return result;
    }
    @RequestMapping("/updates")
    public int update(Userinfo userinfo, HttpServletRequest request) {
        int result = 0;
        // 1.非空效验
        if (userinfo == null || userinfo.getId() <= 0 ||
                !StringUtils.hasLength(userinfo.getUsername())) return result;
        // 2.判断必须为超级管理员，才能进行添加操作
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(AppVariable.USER_SESSION_KEY) == null) return result;
        Userinfo loginUser = (Userinfo) session.getAttribute(AppVariable.USER_SESSION_KEY);
        if (!loginUser.isIsadmin()) return result;
        // 3.执行修改操作
        if (StringUtils.hasLength(userinfo.getPassword())) {
            // 对密码进行加密
            userinfo.setPassword(PasswordUtils.encrypt(userinfo.getPassword()));
        }
        result = userService.update(userinfo);
        return result;
    }

}
