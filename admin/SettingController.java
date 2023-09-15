package org.example.controller;

import org.example.model.Award;
import org.example.model.Member;
import org.example.model.Setting;
import org.example.model.User;
import org.example.service.AwardService;
import org.example.service.MemberService;
import org.example.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/setting")
public class SettingController {

    @Autowired
    private SettingService settingService;

    @Autowired
    private AwardService awardService;

    @Autowired
    private MemberService memberService;

    @GetMapping("/query")
    public Object query(HttpSession session){//已经登录，所以可以直接使用HttpSession
        User user = (User) session.getAttribute("user");
        Setting setting = settingService.queryByUserId(user.getId());
        setting.setUser(user);
        List<Award> awards =  awardService.queryBySettingId(setting.getId());
        setting.setAwards(awards);
        List<Member> members =  memberService.queryBySettingId(setting.getId());
        setting.setMembers(members);
        return  setting;

    }

    @GetMapping("/update")
    public Object update(Integer batchNumber, HttpSession session){
        User user = (User) session.getAttribute("user");
        int n = settingService.update(batchNumber, user.getId());
        return null;
    }
}
