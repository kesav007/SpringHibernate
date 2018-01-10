package com.kesav.contacts.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class State {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	private String state;
	
	public State() {
	}
	
	public State(String state) {
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "State [id=" + id + ", state=" + state + "]";
	}
}
