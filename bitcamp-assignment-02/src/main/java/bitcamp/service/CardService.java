package bitcamp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitcamp.dao.CardDao;
import bitcamp.domain.Card;
import bitcamp.domain.Member;

@Service
public class CardService {
    @Autowired
    CardDao cardDao;
    
    public void add(Card card) {
        cardDao.insert(card);
    }

    public List<Card> list(Member member) {
        return cardDao.selectAll(member);
    }
}
