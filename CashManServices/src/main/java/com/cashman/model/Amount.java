package com.cashman.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "amount")
public class Amount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private long currentAmount;

	private long previousAmount;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(long currentAmount) {
		this.currentAmount = currentAmount;
	}

	public long getPreviousAmount() {
		return previousAmount;
	}

	public void setPreviousAmount(long previousAmount) {
		this.previousAmount = previousAmount;
	}

}
