package bitcamp.pms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import bitcamp.pms.dao.MemberDao;
import bitcamp.pms.domain.Member;

@Controller
public class MemberUpdateController {
    @Autowired
    MemberDao memberDao;
    
    public MemberUpdateController() {}

    public MemberUpdateController(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    
    @RequestMapping("/member/update")
    public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
                Member member = new Member();
                
                member.setEmail(request.getParameter("email"));
                member.setPassword(request.getParameter("password"));
                member.setId(request.getParameter("id"));
                
                if (memberDao.update(member) == 0) {
                    return "member/updatefail";
                } else {
                    return "redirect:list";
                }
    }
    
}
