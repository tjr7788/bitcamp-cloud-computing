package bitcamp.pms.factory;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.FactoryBean;

public class SqlSessionFactoryBean implements FactoryBean<SqlSessionFactory>{
    @Override
    public SqlSessionFactory getObject() throws Exception {
        System.out.println("getObject호출됨");
        String resource = "bitcamp/pms/config/mybatis-config.xml";
        InputStream inputStream = 
                Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
    
    @Override
    public Class<?> getObjectType() {
        System.out.println("getObjectType호출됨");
        return SqlSessionFactory.class;
    }

}
