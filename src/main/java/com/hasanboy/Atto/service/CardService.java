package com.hasanboy.Atto.service;

import com.Hibrnate.Atto.exeption.AttoExeption;
import com.Hibrnate.Atto.model.Card;
import com.Hibrnate.Atto.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class CardService {

    public Card create(Card card) {
        Card check = getByCardNumber(card.getCard_number());
        if (check != null){
            throw new AttoExeption("Bunday karta mavjud");
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        card.setStatus(true);
        card.setDate(LocalDate.now());
        session.beginTransaction();
        session.save(card);
        session.close();
        return card;
    }

    public Card addBalance(String number, BigDecimal cesh) {
        Card check = getByCardNumber(number);
        if (check == null){
            throw new AttoExeption("Bunday karta raqami mavjud emas");
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Card card = getByCardNumber(number);
        card.setBalance(card.getBalance().add(cesh));
        session.beginTransaction();
        session.saveOrUpdate(card);
        session.flush();
        session.close();
        return card;
    }

    public Card getById(Integer id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "FROM Card where id = :id";
        Query query = session.createQuery(sql);
        query.setParameter("id",id);
        return (Card) query.getSingleResult();
    }

    public Card getByCardNumber(String number){
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "FROM Card WHERE card_number = :number";
        Query query = session.createQuery(sql);
        query.setParameter("number",number);
        return (Card) query.getSingleResult();
    }

    public List<Card> getAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "FROM Card ";
        Query query = session.createQuery(sql);
        return (List<Card>) query.getResultList();
    }



    public void updateBalance(Card card){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(card);
        session.flush();
        session.close();
    }
}
