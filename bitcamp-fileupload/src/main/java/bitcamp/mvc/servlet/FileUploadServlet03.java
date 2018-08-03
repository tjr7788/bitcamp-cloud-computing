package bitcamp.mvc.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@SuppressWarnings("serial")
@WebServlet("/fileupload03")
public class FileUploadServlet03 extends HttpServlet {
    @Override
    protected void doPost(
            HttpServletRequest req, 
            HttpServletResponse resp) throws ServletException, IOException {
        
        // 일반 폼으로 전송된 한글 데이터가 유니코드로 바뀔 때 깨지지 않게 하려면
        // getParameter()를 호출하기 전에 다음 명령을 먼저 실행해야 한다.
        // 그러나 멀티파트 전송인 경우는 소용없다!
        //req.setCharacterEncoding("UTF-8");
        
        // 업로드 파일을 외장 하드에 저장하는 역할을 수행
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // 멀티파트 데이터를 파싱한다.
        // 업로드된 파일은 위에서 설정한 factory를 이용하여 다룬다.
        ServletFileUpload upload = new ServletFileUpload(factory);
        
        InputStream fileContent = null;
        OutputStream fileOut = null;
    
        // 클라이언트가 보낸 데이터를 분석한다.
        try {
            Map<String, List<FileItem>> paramMap = upload.parseParameterMap(req);
            String name = paramMap.get("name").get(0).getString("UTF-8");
            String age= paramMap.get("age").get(0).getString("UTF-8");
            FileItem photoItem = paramMap.get("photos").get(0);
            String newfilename = UUID.randomUUID().toString(); 
            String path = this.getServletContext().getRealPath(
                    "/files/" + newfilename);
            fileContent = photoItem.getInputStream();
            fileOut = new FileOutputStream(path);
            
            byte[] buf = new byte[1024];
            int len = 0;
            
            while ((len = fileContent.read(buf)) != -1) {
                fileOut.write(buf, 0, len);
            }
            fileOut.flush();
            
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
        } finally {
            try {fileContent.close();} catch (Exception e) {}
            try {fileOut.close();} catch (Exception e) {}
        }
    }
}
