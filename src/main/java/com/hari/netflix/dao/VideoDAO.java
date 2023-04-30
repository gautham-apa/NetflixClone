package com.hari.netflix.dao;
import com.hari.netflix.pojo.User;
import com.hari.netflix.pojo.VideoData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VideoDAO extends DAO {
    private final int pageSize = 10;
    @Transactional
    public VideoData getVideoMetaData(Long videoId) {
        EntityManager manager = getManager();
        VideoData record = (VideoData) manager.find(VideoData.class, videoId);
        return record;
    }

    @Transactional
    public void persist(VideoData videoData) {
        getManager().persist(videoData);
        getManager().flush();
    }

    @Transactional
    public void persist(ArrayList<VideoData> videoDataList) {
        EntityManager manager = getManager();
        for(VideoData videoData: videoDataList) {
            manager.persist(videoData);
        }
        manager.flush();
    }

    @Transactional
    public ArrayList<VideoData> getPaginatedVideoMetaData(int page, Integer year, String genre) {
        EntityManager manager = getManager();
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<VideoData> criteria = criteriaBuilder.createQuery(VideoData.class);
        Root<VideoData> root = criteria.from(VideoData.class);

        List<Predicate> predicates = new ArrayList<>();

        if(year != null) predicates.add(criteriaBuilder.equal(root.get("releaseYear"), year));
        if(genre != null) predicates.add(criteriaBuilder.like(root.get("genre"), genre));
        criteria.where(predicates.toArray(new Predicate[]{}));
        return (ArrayList<VideoData>) manager.createQuery(criteria)
                .setFirstResult(pageSize*page)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Transactional
    public ArrayList<VideoData> searchPaginatedVideoMetaData(String searchTerm, int page) {
        String searchQueryParam = "%{searchTerm}%".replace("{searchTerm}", searchTerm);
        EntityManager manager = getManager();
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<VideoData> criteria = criteriaBuilder.createQuery(VideoData.class);
        Root<VideoData> root = criteria.from(VideoData.class);
        criteria.where(criteriaBuilder.like(root.get("title"), searchQueryParam));
        return (ArrayList<VideoData>) manager.createQuery(criteria)
                .setFirstResult(pageSize * page)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void addDummyMovieRecordsToDatabase() {
        ArrayList<VideoData> videoDataList = new ArrayList<>();
        videoDataList.add(new VideoData("iron_man", "sci-fi", "iron_man", "Iron Man", 2008));
        videoDataList.add(new VideoData("avengers_infinity", "sci-fi", "avengers_infinity", "Avengers: Infinity War", 2018));
        videoDataList.add(new VideoData("the_batman", "action", "the_batman", "The Batman", 2022));
        videoDataList.add(new VideoData("inception", "sci-fi", "inception", "Inception", 2010));
        videoDataList.add(new VideoData("interstellar", "sci-fi", "interstellar", "Interstellar", 2014));
        videoDataList.add(new VideoData("wolf_wallstreet", "drama", "wolf_wallstreet", "The Wolf of Wall Street", 2013));
        videoDataList.add(new VideoData("titanic", "romance", "titanic", "Titanic", 1997));
        videoDataList.add(new VideoData("the_intern", "comedy", "the_intern", "The Intern", 2015));
        videoDataList.add(new VideoData("sivaji_the_boss", "drama", "sivaji_the_boss", "Sivaji: The Boss", 2007));
        videoDataList.add(new VideoData("ponniyin_selvan_1", "drama", "ponniyin_selvan_1", "Ponniyin Selvan: I", 2022));
        videoDataList.add(new VideoData("baahubali_2", "adventure", "baahubali_2", "Baahubali 2: The Conclusion", 2017));
        videoDataList.add(new VideoData("cars_2", "comedy", "cars_2", "Cars 2", 2011));
        videoDataList.add(new VideoData("furious_7", "action", "furious_7", "Furious 7", 2015));
        videoDataList.add(new VideoData("indiana_jones", "adventure", "indiana_jones", "Indiana Jones And the Raiders of the Lost Ark", 1981));
        videoDataList.add(new VideoData("mi_fallout", "action", "mi_fallout", "Mission: Impossible - Fallout", 2018));
        videoDataList.add(new VideoData("rrr", "action", "rrr", "RRR", 2022));
        videoDataList.add(new VideoData("top_gun_maverick", "adventure", "top_gun_maverick", "Top Gun: Maverick", 2022));
        videoDataList.add(new VideoData("avatar_2", "sci-fi", "avatar_2", "Avatar: The Way of Water", 2022));
        persist(videoDataList);
    }
}
