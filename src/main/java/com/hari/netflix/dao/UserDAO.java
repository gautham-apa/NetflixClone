package com.hari.netflix.dao;
import com.hari.netflix.exception.ResourceNotFoundException;
import com.hari.netflix.pojo.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserDAO extends DAO {
    private final int pageSize = 10;
    @Transactional
    public void persist(User user) {
        getManager().persist(user);
        getManager().flush();
    }

    @Transactional
    public void deleteUser(String email) throws Exception {
        User user = findUser(email);
        if(user == null) throw new ResourceNotFoundException("User record does not exist");
        getManager().remove(user);
        getManager().flush();
    }

    public User findUser(String email) {
        EntityManager manager = getManager();
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.where(criteriaBuilder.equal(root.get("email"), email));
        return manager.createQuery(criteria).getResultList().stream().findFirst().orElse(null);
    }

    public ArrayList<User> getAllUsersPaginated(Integer page) {
        EntityManager manager = getManager();
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        return (ArrayList<User>) manager.createQuery(criteria)
                .setFirstResult(pageSize * page)
                .setMaxResults(pageSize)
                .getResultList();
    }
}
