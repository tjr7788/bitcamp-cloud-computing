package bitcamp.pms.servlet.classroom;

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
@WebServlet("/classroom/view")
public class ClassroomViewServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>게시판 뷰</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>게시판</h1>");
        out.println("<table border='1'>");
        out.println("<form action='update' method='post'>");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (
                Connection con = DriverManager.getConnection(
                    "jdbc:mysql://52.79.234.169:3306/studydb",
                    "study", "1111");
                PreparedStatement stmt = con.prepareStatement(
                        "select crno,titl,sdt,edt,room from pms2_classroom where crno=?");) {
                stmt.setString(1, request.getParameter("no"));
                
                try (ResultSet rs = stmt.executeQuery();) {
                    if (rs.next()) {
                        out.println("<tr><th>번호</th><td>");
                        out.printf("    <input readonly type='text' name='no' value='%d' readonly></td></tr>\n", 
                                rs.getInt("crno"));
                        out.println("<tr><th>제목</th>");
                        out.printf("    <td><input type='text' name='title' value='%s'></td></tr>\n",
                                rs.getString("titl"));
                        out.println("<tr><th>시작일</th>");
                        out.printf("    <td><input type='date' name='startDate' value='%s'></td></tr>\n",
                                rs.getString("sdt"));
                        out.println("<tr><th>종료일</th>");
                        out.printf("    <td><input type='date' name='endDate' value='%s'></td></tr>\n",
                                rs.getString("edt"));
                        out.println("<tr><th>강의실</th>");
                        out.printf("    <td><input type='text' name='room' value='%s'></td></tr>\n",
                                rs.getString("room"));
                        out.printf("<p><button>변경하기</button><a href='delete?no=%d'>삭제하기</a></p>\n",rs.getInt("crno") );
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
