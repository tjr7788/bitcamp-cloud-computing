package bitcamp.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import bitcamp.domain.Card;
import bitcamp.domain.Member;
import bitcamp.service.CardService;

@RestController
@SessionAttributes("loginUser")
@RequestMapping("/card")
public class CardController {
    
    @Autowired
    CardService cardSerivce;
    
    @RequestMapping("add")
    public Object add(Card card) {
        HashMap<String, Object> result = new HashMap<>();
        cardSerivce.add(card);
        result.put("status", "success");
        
        return result;
    }
    
    @RequestMapping("list")
    public Object list(@ModelAttribute(value="loginUser") Member loginUser) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("session", loginUser);
        //result.put("data", cardSerivce.list(loginUser));
        
        return result;
    }
    
    
    
}
