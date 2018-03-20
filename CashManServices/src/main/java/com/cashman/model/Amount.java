package com.cashman.model;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "amount")
public class Amount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private long currentAmount;

	private ZonedDateTime lastUpdateTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@JsonIgnore
	public ZonedDateTime getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(ZonedDateTime lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public long getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(long currentAmount) {
		this.currentAmount = currentAmount;
	}

}
