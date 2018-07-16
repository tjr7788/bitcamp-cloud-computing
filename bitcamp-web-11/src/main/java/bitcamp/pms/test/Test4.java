package bitcamp.pms.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test4 {
    
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext iocContainer = new ClassPathXmlApplicationContext("bitcamp/pms/test/application-context4.xml");
        System.out.println(iocContainer.getBeanDefinitionCount());
        System.out.println("===============================");
        String[] names = iocContainer.getBeanDefinitionNames();
        for (String name : names) {
            System.out.printf("%s ==> %s\n", name, iocContainer.getBean(name).toString());
        }
    }
}