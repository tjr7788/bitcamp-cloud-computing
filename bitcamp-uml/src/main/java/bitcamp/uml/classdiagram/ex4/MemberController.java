package bitcamp.uml.classdiagram.ex4;

import bitcamp.uml.classdiagram.ex3.Member;

public class MemberController {
    public String login(String id, String pwd, HttpSession session) {
        
        Member member = new Member();
        
        session.setAttribute("loginUser", member);
        
        return "Atuh";
    }
}
