package com.hari.netflix.controller;

import com.hari.netflix.dto.VideoDataRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hari.netflix.dao.VideoDAO;

@RestController
public class VideoContentController {
    @Autowired
    private VideoDAO videoDAO;
    @GetMapping("/v1/get_content")
    public ResponseEntity getContentMetaData(VideoDataRequestDto videoDataRequestDto) {
        Integer page = videoDataRequestDto.getPage();
        if(page == null || page < 0) page = 0;
        return new ResponseEntity(videoDAO.getPaginatedVideoMetaData(
                page,
                videoDataRequestDto.getYear(),
                videoDataRequestDto.getGenre()),
                HttpStatus.OK);
    }

    @GetMapping("/v1/search")
    public ResponseEntity searchContentMetaData(HttpServletRequest request) {
        String pageParam = request.getParameter("page");
        String searchTerm = request.getParameter("search_term");
        int page = 0;
        if(pageParam != null && !pageParam.isEmpty()) page = Integer.valueOf(pageParam);
        if(searchTerm == null || searchTerm.isEmpty()) {
            return new ResponseEntity("Missing search term", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(videoDAO.searchPaginatedVideoMetaData(searchTerm, page), HttpStatus.OK);
    }
}
