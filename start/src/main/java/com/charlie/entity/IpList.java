package com.charlie.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class IpList extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String[] ip_list;

	public String[] getIp_list() {
		return ip_list;
	}

	public void setIp_list(String[] ip_list) {
		this.ip_list = ip_list;
	}

}
