package bitcamp.pms.servlet.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.MemberDao;
import bitcamp.pms.domain.Member;


@SuppressWarnings("serial")
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
                Member member = new Member();
                
                member.setEmail(request.getParameter("email"));
                member.setPassword(request.getParameter("password"));
                member.setId(request.getParameter("id"));
                
                if (((MemberDao)getServletContext().getAttribute("memberDao")).update(member) == 0) {
                    request.setAttribute("view", "/member/updatefail.jsp");
                } else {
                    request.setAttribute("view", "redirect:list");
                }
        } catch (Exception e) {
            request.setAttribute("error", e);
        }
    }
    
}
