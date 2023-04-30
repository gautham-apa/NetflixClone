package com.hari.netflix.controller;

import com.hari.netflix.dao.CommentDAO;
import com.hari.netflix.dao.VideoDAO;
import com.hari.netflix.dto.CommentDto;
import com.hari.netflix.pojo.Comment;
import com.hari.netflix.pojo.VideoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CommentController {
    @Autowired
    private CommentDAO commentDAO;
    @Autowired
    private VideoDAO videoDAO;

    @PostMapping("/v1/add_comment")
    public ResponseEntity addComment(@RequestBody CommentDto request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long videoId = request.getVideoId();
        if(auth == null || auth.getName() != null);
        if(videoId == null) return new ResponseEntity("Video id is required", HttpStatus.BAD_REQUEST);
        VideoData videoData = videoDAO.getVideoMetaData(videoId);
        Comment comment = new Comment(request.getText(), auth.getName(),videoData);
        videoData.addComment(comment);
        commentDAO.persist(comment);
        return new ResponseEntity("Comment inserted successfully", HttpStatus.CREATED);
    }

    @GetMapping("/v1/get_comments")
    public ResponseEntity getComments(@RequestParam("video_id") Long videoId,
                                      @RequestParam(value = "page", required=false) Integer page) {
        if(page == null || page < 0) page = 0;
        ArrayList<Comment> comments = commentDAO.getAllCommentsPaginated(page, videoId);
        return new ResponseEntity(comments, HttpStatus.OK);
    }
}
