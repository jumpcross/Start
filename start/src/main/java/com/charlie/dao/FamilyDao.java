package com.charlie.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.charlie.entity.FamilyMember;

public interface FamilyDao extends CrudRepository<FamilyMember, Long> {
	 List<FamilyMember> findByBirthDayBetween(Date one, Date two);
    List<FamilyMember> findByName(String name);
}