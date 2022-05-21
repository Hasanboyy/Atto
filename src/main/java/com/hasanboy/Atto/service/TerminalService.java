package com.hasanboy.Atto.service;


import com.hasanboy.Atto.model.Terminal;
import com.hasanboy.Atto.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class TerminalService {
    public Terminal create(Terminal terminal) {
        /*
        Terminal check = getByNumber(terminal.getTerminal_number());
        if (check != null){
            throw new AttoExeption("Bunday terminal mavjud");
        }*/
        Session session = HibernateUtil.getSessionFactory().openSession();
        terminal.setStatus(true);
        terminal.setBalance(new BigDecimal(0));
        terminal.setDate(LocalDate.now());
        session.beginTransaction();
        session.save(terminal);
        session.close();
        return terminal;
    }

    public Terminal getByNumber(String number){
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "FROM Terminal WHERE terminal_number = :number";
        Query query = session.createQuery(sql);
        query.setParameter("number",number);
        return (Terminal) query.getSingleResult();
    }

    public Terminal getById(Integer id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "FROM Terminal where id = :id";
        Query query = session.createQuery(sql);
        query.setParameter("id",id);
        return (Terminal) query.getSingleResult();
    }


    public Terminal balance(String number, BigDecimal cesh) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Terminal terminal = getByNumber(number);
        terminal.setBalance(terminal.getBalance().add(cesh));
        terminal.setStatus(true);
        session.beginTransaction();
        session.saveOrUpdate(terminal);
        session.flush();
        session.close();
        return terminal;
    }

    public void updateTerminal(Terminal terminal) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(terminal);
        session.flush();
        session.close();
    }
}
