package com.hari.netflix.controller;

import com.hari.netflix.util.AssetLoaderUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssetCDNController {
    @Autowired
    private AssetLoaderUtil assetLoaderUtil;

    @GetMapping("/v1/get_asset")
    public ResponseEntity getContentMetaData(HttpServletRequest request) throws Exception {
        String assetId = request.getParameter("asset_id");
        if(assetId == null || assetId.isEmpty()) return new ResponseEntity("Asset id is empty", HttpStatus.BAD_REQUEST);
        byte[] asset = assetLoaderUtil.getAssetFile(assetId);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "image/jpeg");
        return new ResponseEntity(asset, headers, HttpStatus.OK);
    }
}
