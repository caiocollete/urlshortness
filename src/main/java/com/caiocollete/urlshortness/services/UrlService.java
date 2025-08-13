package com.caiocollete.urlshortness.services;

import com.caiocollete.urlshortness.model.Url;
import com.caiocollete.urlshortness.repository.IUrlRepository;
import org.springframework.stereotype.Service;

@Service
public class UrlService {
    IUrlRepository urlRepository;

    public String get(Long id){
        return urlRepository.findById(id).get().getOriginalUrl();
    }

    public Long save(String url){
        var response = urlRepository.save(new Url(url));
        return response.getId();
    }
}
