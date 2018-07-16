package bitcamp.pms.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MemberListController {
    
    
    @Autowired
    MemberDao memberDao;
    
    @Override
    public String toString() {
        return "MemberListController [memberDao=" + memberDao + "]";
    }

}
