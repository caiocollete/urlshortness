package com.caiocollete.urlshortness.repository;

import com.caiocollete.urlshortness.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUrlRepository extends JpaRepository<Url, Long> {
}
