package com.zipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zipe.model.OauthClientDetails;

public interface IOauthClientDetailsRepository extends JpaRepository<OauthClientDetails, Long> {

}
