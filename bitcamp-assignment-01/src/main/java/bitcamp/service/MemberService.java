package bitcamp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitcamp.dao.MemberDao;
import bitcamp.domain.Member;

@Service
public class MemberService {
    @Autowired
    MemberDao memberDao;
    
    public void add(Member member) {
        memberDao.insert(member);
    }

    public Member login(Member member) {
        return memberDao.selectUser(member);
    }
    
}
