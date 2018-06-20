package com.localhost.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.localhost.model.OauthClientDetails;

public interface IOauthClientDetailsRepository extends JpaRepository<OauthClientDetails, Long> {

}
