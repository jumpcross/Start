package com.charlie.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.charlie.entity.WechatToken;

public interface WechatTokenRepository extends CrudRepository<WechatToken, Long> {

	@Modifying
	@Query("update WechatToken u set u.flag = ? ")
	@Transactional
	int setFlagFor(String flag);

	List<WechatToken> findByFlag(String flag);
}
