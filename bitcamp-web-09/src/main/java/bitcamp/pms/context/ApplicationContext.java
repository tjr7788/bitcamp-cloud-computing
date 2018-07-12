package bitcamp.pms.context;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;

import org.apache.ibatis.io.Resources;

import bitcamp.pms.annotation.Autowired;
import bitcamp.pms.annotation.Component;
import bitcamp.pms.annotation.Controller;
import bitcamp.pms.annotation.Repository;

public class ApplicationContext {
    
    public ApplicationContext(String packageName) throws Exception {
        
        String filePath = packageName.replace(".", "/");
        
        File dir = Resources.getResourceAsFile(filePath);
        System.out.println(dir.getAbsolutePath());
        
        /*ClassLoader defaultClassLoader = ClassLoader.getSystemClassLoader();
        
        URL url = defaultClassLoader.getResource(filePath);
        
        File dir = new File(url.toURI());*/
        
        findClass(dir, packageName);
        
        injectDependency();
    }
    
    private void injectDependency() throws Exception {
        Collection<Object> objList = objPool.values();
        
        for (Object obj : objList) {
            Class<?> clazz = obj.getClass();
            
            Method[] methods = clazz.getMethods();
            
            for (Method m : methods) {
                if (!m.getName().startsWith("set"))
                    continue;
                if (m.getAnnotation(Autowired.class) == null)
                    continue;
                if (m.getParameterTypes().length > 1)
                    continue;
                Class<?> paramType = m.getParameterTypes()[0];
                try {
                    Object dependency = getBean(paramType);
                    m.invoke(obj, dependency);
                } catch (Exception e) {
                    System.out.println("error:" + e.getMessage());
                }
            }
        }
        
    }
    
    public void refresh() throws Exception {
        injectDependency();
    }
    
    public Object getBean(Class<?> type) throws RuntimeException {
        Collection<Object> objlist = objPool.values();
        for (Object obj : objlist) {
            if (type.isInstance(obj))
                return obj;
        }
        throw new RuntimeException(type.getName() + "객체가 존재하지않습니다.");
    }

    public Object getBean(String name) throws RuntimeException {
        Object obj = objPool.get(name);
        if (obj == null) 
            throw new RuntimeException(name + "객체가 존재하지않습니다.");
        return obj;
    }
    
    public void addBean(String name, Object obj) {
        objPool.put(name, obj);
    }
    
    HashMap<String, Object> objPool = new HashMap<>();
    
    private void findClass(File path, String packageName) {
        
        File[] subFiles = path.listFiles((File pathname) -> {
            if (pathname.isDirectory())
                return true;
            if (pathname.isFile() && 
                    pathname.getName().endsWith(".class")) 
                return true;
            return false;
        });
        
        for (File subFile : subFiles) {
            if (subFile.isFile()) {
                String className = packageName + "." + 
                        subFile.getName().replace(".class", "");
                createObject(className);
            } else {
                findClass(subFile, 
                        packageName + "." + subFile.getName());
            }
        }
    }

    private void createObject(String className) {
        try {
            // 클래스 이름(패키지명 + 클래스명)으로 .class 파일을 찾아 로딩한다.
            Class<?> clazz = Class.forName(className);
            if (clazz.getAnnotation(Component.class) == null && clazz.getAnnotation(Controller.class) == null && clazz.getAnnotation(Repository.class) == null) {
                return;
            }
            String objName = getObjectName(clazz);
            
            // 클래스 정보를 보고 기본 생성자를 알아낸다.
            Constructor<?> constructor = clazz.getConstructor();
            
            // 기본 생성자를 호출하여 객체를 생성한 후 객체 보관소에 저장한다.
            objPool.put(objName, constructor.newInstance());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private String getObjectName(Class<?> clazz) throws Exception {
        
        String objName = null;
        Component compAnno = clazz.getAnnotation(Component.class);
        if (compAnno != null) objName = compAnno.value();
        Controller ctrlAnno = clazz.getAnnotation(Controller.class);
        if (ctrlAnno != null) objName = ctrlAnno.value();
        Repository repoAnno = clazz.getAnnotation(Repository.class);
        if (repoAnno != null) objName = repoAnno.value();
        
        if (objName.length() == 0) {
            return clazz.getCanonicalName();
        } else {
            return objName;
        }
    }
}
