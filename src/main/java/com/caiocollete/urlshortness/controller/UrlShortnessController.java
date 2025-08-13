package com.caiocollete.urlshortness.controller;

import com.caiocollete.urlshortness.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/short")
public class UrlShortnessController {
    @Autowired
    UrlService urlService;

    @GetMapping
    public ResponseEntity<String> getUrl(@RequestParam Long id){
        String urlOriginal = urlService.get(id);
        return urlOriginal.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(urlOriginal);
    }

    @PostMapping
    public ResponseEntity<Long> saveUrl(@RequestParam String url){
        Long response = urlService.save(url);
        return response>0 ? ResponseEntity.ok(response) : ResponseEntity.noContent().build();
    }
}
