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
@WebServlet("/board/add")
public class BoardAddServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta http-equiv='Refresh' content='1;url=list'>");
        out.println("<title>게시판 등록</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>게시판 등록 결과</h1>");
        try {   
            Board board = new Board();
            board.setTitle(request.getParameter("title"));
            board.setContent(request.getParameter("content"));
            ((BoardDao)getServletContext().getAttribute("boardDao")).add(board);
            out.println("<p>등록 성공!</p>");
        } catch (Exception e) {
            out.println("<p>등록 실패!</p>");
            e.printStackTrace(out);
        }
        out.println("</body>");
        out.println("</table>");
        out.println("</html>");
    }
}
