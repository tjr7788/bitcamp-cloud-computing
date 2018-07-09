package bitcamp.pms.servlet.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.domain.Board;


@SuppressWarnings("serial")
@WebServlet("/board/view")
public class BoardViewServlet extends HttpServlet {
    
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
            Board board = ((BoardDao)getServletContext().getAttribute("boardDao")).selectOne(request.getParameter("no"));
            out.println("<tr><th>번호</th><td>");
            out.printf("    <input readonly type='text' name='no' value='%d' readonly></td></tr>\n", 
                    board.getNo());
            out.println("<tr><th>제목</th>");
            out.printf("    <td><input type='text' name='title' value='%s'></td></tr>\n",
                    board.getTitle());
            out.println("<tr><th>내용</th>");
            out.printf("    <td><input type='text' name='content' value='%s'></td></tr>\n",
                    board.getContent());
            out.printf("<p><button>변경하기</button><a href='delete?no=%d'>삭제하기</a></p>\n",board.getNo());
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
