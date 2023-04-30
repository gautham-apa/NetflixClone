package com.hari.netflix.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "video_data")
@Getter
@Setter
@NoArgsConstructor
public class VideoData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String genre;
    private String thumbnailId;
    private String title;
    private int releaseYear;
    @JsonIgnore
    @OneToMany(mappedBy = "video")
    private List<Comment> comments = new ArrayList<>();

    public VideoData(String fileName, String genre, String thumbnailId, String title, int releaseYear) {
        this.fileName = fileName;
        this.genre = genre;
        this.thumbnailId = thumbnailId;
        this.title = title;
        this.releaseYear = releaseYear;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setVideo(this);
    }
}
