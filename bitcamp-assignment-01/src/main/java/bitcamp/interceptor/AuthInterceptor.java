package bitcamp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(
            HttpServletRequest request, 
            HttpServletResponse response, 
            Object handler)
            throws Exception {
        
        HttpSession session = request.getSession();
        
        if (session.getAttribute("loginUser") == null) {
            response.sendRedirect(
                request.getServletContext().getContextPath() + 
                "/json/meber/login");
            return false;
        }
        
        return true;
    }
}