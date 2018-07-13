// 의존 객체 주입 자동화하기 - @Autowired + @Qualifier = @Resource
package bitcamp.java106.step08;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bitcamp.java106.BeanUtils;

public class Exam08 {

    public static void main(String[] args) {
        ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
                "bitcamp/java106/step08/application-context-08.xml");
        
        // Car 객체를 꺼내 Engine 객체가 주입되었는지 확인해보자!
        System.out.println(iocContainer.getBean("c1"));
        System.out.println("---------------------------");
        
        BeanUtils.printBeanNames(iocContainer);
    }

}





