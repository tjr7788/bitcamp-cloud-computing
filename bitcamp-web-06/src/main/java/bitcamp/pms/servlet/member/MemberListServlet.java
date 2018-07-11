package bitcamp.pms.servlet.member;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.MemberDao;


@SuppressWarnings("serial")
@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, Object> params = new HashMap<>();
        if (request.getParameter("page") != null && request.getParameter("size") != null) {
            int page = Integer.parseInt(request.getParameter("page"));
            int size = Integer.parseInt(request.getParameter("size"));
            params.put("startIndex", (page - 1) * size);
            params.put("pageSize", size);
        }
        try {
            request.setAttribute("list", ((MemberDao)getServletContext().getAttribute("memberDao")).selectList(params));
            request.setAttribute("view", "/member/list.jsp");
        } catch (Exception e) {
            request.setAttribute("error", e);
        }
    }
}
