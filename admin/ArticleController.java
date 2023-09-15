package com.example.demobk.controller;

import com.example.demobk.common.AjaxResult;
import com.example.demobk.common.UserSessionUtils;
import com.example.demobk.entity.Articleinfo;
import com.example.demobk.entity.Userinfo;
import com.example.demobk.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.channels.InterruptedByTimeoutException;
import java.util.List;

@RestController
@RequestMapping("/art")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @RequestMapping("/mylist")
    public AjaxResult getMyList(HttpServletRequest request){
        Userinfo userinfo = UserSessionUtils.getUser(request);
        if (userinfo == null){
            return  AjaxResult.fail(-1,"非法");
        }
        List<Articleinfo> list =articleService.getMyList(userinfo.getId());
        return  AjaxResult.success(list);
    }

    @RequestMapping("/del")
    public  AjaxResult del(HttpServletRequest request, Integer id){
        if (id == null|| id<=0){

            return AjaxResult.fail(-1,"参数异常");
        }
        Userinfo userinfo = UserSessionUtils.getUser(request);
        if (userinfo == null){
            return AjaxResult.fail(-2,"参数异常");
        }

        return AjaxResult.success(id,userinfo.getId());

    }
    @RequestMapping("/detail")
    public AjaxResult getDetail(Integer id){
        if (id == null ||id<=0){
            return AjaxResult.fail(-1,"参数异常");
        }
        return AjaxResult.success(articleService.getDetail(id));
    }
    @RequestMapping("/incr-rcount")
    public  AjaxResult incrRcount(Integer id){
          if (id!= null||id>0){
              return AjaxResult.success(articleService.incrRcount(id));
          }
        return AjaxResult.fail(-1,"参数异常");
    }
    @RequestMapping("/add")
    public  AjaxResult add(HttpServletRequest request,Articleinfo articleinfo){

        if(articleinfo ==null|| !StringUtils.hasLength(articleinfo.getTitle())|| !StringUtils.hasLength(articleinfo.getContent()))
        {
            return AjaxResult.fail(-1,"参数异常");
        }
       Userinfo userinfo = UserSessionUtils.getUser(request);
        if (userinfo == null||userinfo.getId()<=0){
            return AjaxResult.fail(-2,"参数异常");
        }

    return AjaxResult.success(articleService.add(articleinfo));
    }
 @RequestMapping("/update")
    public AjaxResult update(HttpServletRequest request,Articleinfo articleinfo){
        if (articleinfo == null|| !StringUtils.hasLength(articleinfo.getTitle())|| !StringUtils.hasLength(articleinfo.getContent())
        ||articleinfo.getId()== null) {
            return AjaxResult.fail(-1,"参数异常");
        }
      Userinfo userinfo = UserSessionUtils.getUser(request);
        if (userinfo == null||userinfo.getId()<=0){
            return AjaxResult.fail(-2,"参数异常");
        }
        articleinfo.setUid(userinfo.getId());
        return AjaxResult.success(articleService.update(articleinfo));
    }
    @RequestMapping("/listbypage")
    public  AjaxResult getListPage(Integer pindex,Integer psize){
        if (pindex== null||pindex<=1){
            pindex =1;
        }
        if (psize== null||psize<=1){
            psize =2;
        }
        int offset = (pindex-1)*psize;
        List<Articleinfo> list =articleService.getListPage(psize,offset);
       return AjaxResult.success(list);
    }

}
