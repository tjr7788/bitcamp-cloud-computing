package bitcamp.mvc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@SuppressWarnings("serial")
@MultipartConfig(
        // 1MB를 넘어가는 파일일 경우 메모리에 저장하지 말고 임시 폴더에 저장
        fileSizeThreshold=1024 * 1024, 
        // 한개 파일의 최대 크기를 제한한다.
        maxFileSize=1024 * 1024 * 2,
        // 요청 데이터 전체 크기를 제한한다.
        maxRequestSize=1024 * 1024 * 2 * 10)
@WebServlet("/fileupload04")
public class FileUploadServlet04 extends HttpServlet {
    
    @Override
    protected void doPost(
            HttpServletRequest req, 
            HttpServletResponse resp) throws ServletException, IOException {
        
        try {
            String name = req.getParameter("name");
            String age = req.getParameter("age");
            Part photo = req.getPart("photo");
            String newfilename = UUID.randomUUID().toString(); 
            String path = this.getServletContext().getRealPath(
                    "/files/" + newfilename);
            photo.write(path);
            
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("<html><head><title>파일업로드</title></head><body>");
            out.printf("name = %s<br>\n", name);
            out.printf("age = %s<br>\n", age);
            out.printf("photo = <a href='files/%s'>%s</a><br>\n", 
                    newfilename,
                    newfilename);
            out.println("<p><img id='img1'></p>");
            out.println("<script>");
            out.println("setTimeout(() => {");
            out.printf("document.getElementById('img1').src = 'files/%s';", newfilename); 
            out.println("}, 5000);");
            out.println("</script>");
            out.println("</body></html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
