package com.doan.tma_spring_boot_test.service;

import com.doan.tma_spring_boot_test.entity.Team;
import com.doan.tma_spring_boot_test.repository.TeamRepositoryCustom;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class TeamRepositoryImpl implements TeamRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Team> teamByParams(String name, String city, String mascot) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Team> criteriaQuery = cb.createQuery(Team.class);
        Root<Team> teamRoot = criteriaQuery.from(Team.class);

        Predicate namePredicate = cb.equal(teamRoot.get("name"), name);
        Predicate cityPredicate = cb.equal(teamRoot.get("city"), city);
        Predicate mascotPredicate = cb.equal(teamRoot.get("mascot"), mascot);

        Predicate finalPredicate = cb.or(namePredicate, cityPredicate, mascotPredicate);
        criteriaQuery.where(finalPredicate);
        return em.createQuery(criteriaQuery).getResultList();
    }
}
