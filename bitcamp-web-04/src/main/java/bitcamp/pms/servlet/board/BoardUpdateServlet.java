package bitcamp.pms.servlet.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.domain.Board;


@SuppressWarnings("serial")
@WebServlet("/board/update")
public class BoardUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {   
            Board board = new Board();
            board.setTitle(request.getParameter("title"));
            board.setContents(request.getParameter("content"));
            board.setNo(Integer.parseInt(request.getParameter("no")));
            
            if (((BoardDao)getServletContext().getAttribute("boardDao")).update(board) == 0) {
                RequestDispatcher rd = request.getRequestDispatcher("/board/updatefail.jsp");
                rd.forward(request, response);
            } else {
                response.sendRedirect("list");
            }
        } catch (Exception e) {
            request.setAttribute("error", e);
            RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
        }
    }
}
