package bitcamp.pms.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.annotation.Autowired;
import bitcamp.pms.annotation.Controller;
import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.dao.MemberDao;

@Controller("/member/list")
public class MemberListController {
    
    MemberDao memberDao;
    
    
    
    public MemberListController() {}
    
    public MemberListController(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    @Autowired
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    @RequestMapping
    public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        if (request.getParameter("page") != null && request.getParameter("size") != null) {
            int page = Integer.parseInt(request.getParameter("page"));
            int size = Integer.parseInt(request.getParameter("size"));
            params.put("startIndex", (page - 1) * size);
            params.put("pageSize", size);
        }
            request.setAttribute("list", memberDao.selectList(params));
            return "/member/list.jsp";
    }
}
