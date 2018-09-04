package bitcamp.uml.classdiagram.ex1;

public class MemberService {
    
    MemberDao memberDao;
    
    public MemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
}
