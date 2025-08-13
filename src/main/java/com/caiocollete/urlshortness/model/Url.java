package com.caiocollete.urlshortness.model;

import jakarta.persistence.*;

@Entity
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "original_url")
    private String originalUrl;

    public Url(){}
    public Url(String originalUrl) {
        this.originalUrl = originalUrl;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
