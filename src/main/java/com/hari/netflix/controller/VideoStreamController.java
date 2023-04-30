package com.hari.netflix.controller;

import com.hari.netflix.dao.VideoDAO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hari.netflix.util.AssetLoaderUtil;
import reactor.core.publisher.Mono;

@RestController
public class VideoStreamController {
    @Autowired
    private AssetLoaderUtil assetLoaderUtil;
    @Autowired
    private VideoDAO videoDAO;

    @GetMapping("/v1/watch/{videoId}")
    ResponseEntity<byte[]> getVideoStream(@PathVariable("videoId") String videoId, HttpServletRequest request) throws Exception {
        String range = request.getHeader("range");
        Long start = 0l, end = null;
        if(videoId == null || videoId.isEmpty()) return new ResponseEntity("Video id is required", HttpStatus.BAD_REQUEST);

        if(range != null && !range.isEmpty()) {
            String[] str = range.split("-");
            start = Long.valueOf(str[0].replaceAll("[^0-9]", ""));
            if(str.length == 2 && str[1] != "") end = Long.valueOf(str[1]);
        }
        return assetLoaderUtil.streamVideo(videoId, start, end);
    }
}
