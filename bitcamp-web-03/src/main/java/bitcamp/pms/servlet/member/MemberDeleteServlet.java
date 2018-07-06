package bitcamp.pms.servlet.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.MemberDao;


@SuppressWarnings("serial")
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta http-equiv='Refresh' content='1;url=list'>");
        out.println("<title>멤버 삭제</title>");
        out.println("</head>");
        out.println("<body>");
        try {
            MemberDao memberDao = new MemberDao("jdbc:mysql://52.79.234.169:3306/studydb", "study", "1111");
            if (memberDao.delete(request.getParameter("id")) == 0) {
                out.println("<tr><td>해당회원이없습니다.!</td></tr>");
            } else {
                out.println("<tr><td>삭제성공!</td></tr>");
            }
        } catch (Exception e) {
            out.println("<tr><td>삭제실패!</td></tr>");
            e.printStackTrace(out);
        }
        out.println("</body>");
        out.println("</table>");
        out.println("</html>");
    }
}
