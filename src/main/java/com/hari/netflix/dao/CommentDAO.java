package com.hari.netflix.dao;

import com.hari.netflix.pojo.Comment;
import com.hari.netflix.pojo.User;
import com.hari.netflix.pojo.VideoData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CommentDAO extends DAO {
    private final int pageSize = 10;
    @Autowired
    private VideoDAO videoDAO;

    @Transactional
    public void persist(Comment comment) {
        getManager().persist(comment);
        getManager().flush();
    }

    public ArrayList<Comment> getAllCommentsPaginated(Integer page, Long videoId) {
        EntityManager manager = getManager();
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Comment> criteriaQuery = criteriaBuilder.createQuery(Comment.class);
        Root<Comment> root = criteriaQuery.from(Comment.class);

        Join<Comment, VideoData> joinedRoot = root.join("video", JoinType.RIGHT);

        criteriaQuery.where(criteriaBuilder.equal(joinedRoot.get("id"), videoId));
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("timestamp")));
        return (ArrayList<Comment>) manager.createQuery(criteriaQuery)
                .setFirstResult(pageSize*page)
                .setMaxResults(pageSize)
                .getResultList();
    }
}
