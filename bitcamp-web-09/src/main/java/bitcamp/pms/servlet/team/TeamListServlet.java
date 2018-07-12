package bitcamp.pms.servlet.team;

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
@WebServlet("/team/list")
public class TeamListServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>팀 목록</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>팀 목록</h1>");
        
        out.println("<p><a href='form.html'>새 팀</a></p>");
        out.println("<table border='1'>");
        out.println("<tr>");
        out.println("    <th>팀명</th><th>최대인원</th><th>기간</th>");
        out.println("</tr>");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (
                Connection con = DriverManager.getConnection(
                    "jdbc:mysql://52.79.234.169:3306/studydb",
                    "study", "1111");
                PreparedStatement stmt = con.prepareStatement(
                        "select name, sdt, edt, max_qty from pms2_team");
                ResultSet rs = stmt.executeQuery();) {
                while (rs.next()) {
                    out.println("<tr>");
                    out.printf("    <td><a href='view?name=%s'>%s</a></td><td>%d</td><td>%s~%s</td>\n",
                        rs.getString("name"),
                        rs.getString("name"),
                        rs.getInt("max_qty"),
                        rs.getString("sdt"),
                        rs.getString("edt")
                        );
                    out.println("</tr>");
                }
            }
        } catch (Exception e) {
            out.println("<tr><td>목록 가져오기 실패!</td></tr>");
            e.printStackTrace(out);
        }
        out.println("</body>");
        out.println("</table>");
        out.println("</html>");
    }
}
