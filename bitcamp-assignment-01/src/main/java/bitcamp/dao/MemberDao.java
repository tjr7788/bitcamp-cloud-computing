package bitcamp.dao;

import bitcamp.domain.Member;

public interface MemberDao {

    void insert(Member member);

    Member selectUser(Member member);
}
