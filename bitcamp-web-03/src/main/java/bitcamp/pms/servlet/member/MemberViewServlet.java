package bitcamp.pms.servlet.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.MemberDao;
import bitcamp.pms.domain.Member;


@SuppressWarnings("serial")
@WebServlet("/member/view")
public class MemberViewServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>멤버 뷰</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>멤버</h1>");
        out.println("<table border='1'>");
        out.println("<form action='update' method='post'>");
        try {
            Member member = ((MemberDao)getServletContext().getAttribute("memberDao")).selectOne(request.getParameter("id"));
            out.println("<tr><th>아이디</th><td>");
            out.printf("    <input readonly type='text' name='id' value='%s' readonly></td></tr>\n", 
                    member.getId());
            out.println("<tr><th>이메일</th>");
            out.printf("    <td><input type='email' name='email' value='%s'></td></tr>\n",
                    member.getEmail());
            out.println("<tr><th>암호</th>");
            out.println("    <td><input type='password' name='password'></td></tr>\n");
            out.printf("<p><button>변경하기</button><a href='delete?id=%s'>삭제하기</a></p>\n",member.getId());
        } catch (Exception e) {
            out.println("<tr><td>목록 가져오기 실패!</td></tr>");
            e.printStackTrace(out);
        }
        out.println("</form>");
        out.println("</body>");
        out.println("</table>");
        out.println("</html>");
    }
    
}
