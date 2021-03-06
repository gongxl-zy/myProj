package com.gxl.entity;

// Generated 2016-12-19 16:48:32 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PWaitSearch generated by hbm2java
 */
@Entity
@Table(name = "p_wait_search", catalog = "mydb")
public class PWaitSearch implements java.io.Serializable {

	private String wsUrl;
	private int wsDepth;

	public PWaitSearch() {
	}

	public PWaitSearch(String wsUrl, int wsDepth) {
		this.wsUrl = wsUrl;
		this.wsDepth = wsDepth;
	}

	@Id
	@Column(name = "ws_url", unique = true, nullable = false, length = 128)
	public String getWsUrl() {
		return this.wsUrl;
	}

	public void setWsUrl(String wsUrl) {
		this.wsUrl = wsUrl;
	}

	@Column(name = "ws_depth", nullable = false)
	public int getWsDepth() {
		return this.wsDepth;
	}

	public void setWsDepth(int wsDepth) {
		this.wsDepth = wsDepth;
	}

}
