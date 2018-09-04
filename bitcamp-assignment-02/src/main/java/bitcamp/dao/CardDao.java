package bitcamp.dao;

import java.util.List;

import bitcamp.domain.Card;
import bitcamp.domain.Member;

public interface CardDao {

    void insert(Card card);

    List<Card> selectAll(Member member);
}