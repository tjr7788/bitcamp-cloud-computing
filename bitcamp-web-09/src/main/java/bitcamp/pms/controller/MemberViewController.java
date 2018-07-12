package bitcamp.pms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.annotation.Autowired;
import bitcamp.pms.annotation.Controller;
import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.dao.MemberDao;
import bitcamp.pms.domain.Member;

@Controller("/member/view")
public class MemberViewController {
    
    MemberDao memberDao;
    
    public MemberViewController() {}

    public MemberViewController(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    @Autowired
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    

    @RequestMapping
    public String view(HttpServletRequest request, HttpServletResponse response) throws Exception {
            Member member = memberDao.selectOne(request.getParameter("id"));
            request.setAttribute("member", member);
            return "/member/view.jsp";
    }
}
