package com.hasanboy.Atto.service;

import com.hasanboy.Atto.exeption.AttoExeption;
import com.hasanboy.Atto.model.Card;
import com.hasanboy.Atto.model.Terminal;
import com.hasanboy.Atto.model.Transaction;
import com.hasanboy.Atto.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.math.BigDecimal;

@Service
public class TransuctionService {

    @Autowired
    private CardService cardService;

    @Autowired
    private TerminalService terminalService;

    private BigDecimal fare = new BigDecimal(1400);


    public Transaction create(Transaction transaction) {
        Card card = cardService.getById(transaction.getCardNumber());
        Terminal terminal = terminalService.getByNumber(transaction.getTerminalNumber());
        if (card == null || card.getBalance().compareTo(fare) < 0){
            throw new AttoExeption("Kartada xatolik mavjud");
        }

        if (terminal == null){
            throw  new AttoExeption("Terminal mavjud emas");
        }

        card.setBalance(card.getBalance().subtract(fare));
        terminal.setBalance(terminal.getBalance().add(fare));

        terminalService.updateTerminal(terminal);
        cardService.updateBalance(card);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(transaction);
        session.close();
        return transaction;
    }

    public Transaction getById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "FROM Transaction where id = :id";
        Query query = session.createQuery(sql);
        query.setParameter("id",id);
        return (Transaction) query.getSingleResult();
    }


}
