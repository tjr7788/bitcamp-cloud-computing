package bitcamp.pms.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import bitcamp.pms.dao.MemberDao;


@WebListener            //tomcat서버에 listener라고 알려주기
public class ContextLoaderListener implements ServletContextListener {
    @Override       //시작이 준비될때 
    public void contextInitialized(ServletContextEvent sce) {
        MemberDao memberDao = new MemberDao(
                "jdbc:mysql://52.79.234.169:3306/studydb", 
                "study", 
                "1111");
        ServletContext sc = sce.getServletContext();
        sc.setAttribute("memberDao", memberDao);
    }
    
    @Override       //종료될 때
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub
        ServletContextListener.super.contextDestroyed(sce);
    }
}
