package com.njmarket.webshopnj.repository;

import com.njmarket.webshopnj.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

    public Member findOneUserid(String userid) {
        return em.createQuery("select m from  Member  m where m.userid = :userid", Member.class)
                .setParameter("userid",userid)
                .getSingleResult();
    }

    public Optional<Member> findByUserid(String userid) {
        return findAll().stream()
                .filter(member -> member.getUserid().equals(userid))
                .findFirst();
    }
}
