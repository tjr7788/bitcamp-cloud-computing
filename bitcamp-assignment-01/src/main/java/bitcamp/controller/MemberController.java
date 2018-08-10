package bitcamp.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import bitcamp.domain.Member;
import bitcamp.service.MemberService;

@RestController
@RequestMapping("/member")
@SessionAttributes("loginUser")
public class MemberController {
    
    @Autowired
    MemberService memberService;
    
    @RequestMapping("add")
    public Object add(Member member) {
        HashMap<String, Object> result = new HashMap<>();
        memberService.add(member);
        result.put("status", "success");
        
        return result;
    }
    
    @RequestMapping("login")
    public Object login(Member member, HttpSession session, Model model) {
        HashMap<String, Object> result = new HashMap<>();
        Member user = memberService.login(member);
        if (user == null) {
            model.addAttribute("loginUser", null);
            result.put("status", "fail");
        } else {
            model.addAttribute("loginUser", user);
            result.put("status", "success");
        }
        
        return result;
    }
    
    @RequestMapping("loginUser")
    public Object loginUser(HttpSession session) {

        Member member = (Member) session.getAttribute("loginUser");
        HashMap<String, Object> result = new HashMap<>();
        if (member != null) {
            result.put("status", "success");
            result.put("member", member);
        } else {
            result.put("status", "fail");
        }

        return result;
    }
}
