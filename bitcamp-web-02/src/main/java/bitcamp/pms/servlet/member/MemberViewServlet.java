package bitcamp.pms.servlet.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
            Class.forName("com.mysql.jdbc.Driver");
            try (
                Connection con = DriverManager.getConnection(
                    "jdbc:mysql://52.79.234.169:3306/studydb",
                    "study", "1111");
                PreparedStatement stmt = con.prepareStatement(
                    "select mid,email   from pms2_member where mid=?");) {
                stmt.setString(1, request.getParameter("id"));
                
                try (ResultSet rs = stmt.executeQuery();) {
                    if (rs.next()) {
                        out.println("<tr><th>아이디</th><td>");
                        out.printf("    <input readonly type='text' name='id' value='%s' readonly></td></tr>\n", 
                                rs.getString("mid"));
                        out.println("<tr><th>이메일</th>");
                        out.printf("    <td><input type='email' name='email' value='%s'></td></tr>\n",
                                rs.getString("email"));
                        out.println("<tr><th>암호</th>");
                        out.println("    <td><input type='password' name='password'></td></tr>\n");
                        out.printf("<p><button>변경하기</button><a href='delete?id=%s'>삭제하기</a></p>\n",rs.getString("mid") );
                    } 
                }
            }  
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
