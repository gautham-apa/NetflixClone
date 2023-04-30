package com.hari.netflix.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="comment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private String text;
    private String userEmail;
    private Date timestamp;
    @JsonIgnore
    @ManyToOne
    private VideoData video;

    public Comment(String text, String userEmail, VideoData video) {
        this.text = text;
        this.userEmail = userEmail;
        this.video = video;
        this.timestamp = new Date();
    }
}
