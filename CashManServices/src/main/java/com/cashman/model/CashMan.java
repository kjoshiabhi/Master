package com.cashman.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cashMan")
public class CashMan {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public enum Note {
		TWENTY(20), FIFTY(50);

		private final int value;

		Note(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	private Note note;
	private long previousNotes;
	private long currentNotes;

	public CashMan() {
	}

	public CashMan(Note note, long prevoiusNotes) {
		setNote(note);
		setPreviousNotes(prevoiusNotes);
	}

	public Note getNote() {
		return note;
	}

	public CashMan setNote(Note note) {
		this.note = note;
		return this;
	}

	public long getPreviousNotes() {
		return previousNotes;
	}

	public CashMan setPreviousNotes(long number) {
		this.previousNotes = number;
		return this;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getCurrentNotes() {
		return currentNotes;
	}

	public CashMan setCurrentNotes(long currentNotes) {
		this.currentNotes = currentNotes;
		return this;
	}

}
