package com.istic.rest;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the learner database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Learner.findAll", query = "SELECT l FROM Learner l"),
		@NamedQuery(name = "Learner.findById", query = "SELECT l FROM Learner l WHERE l.learnerId = :learnerId"),
		@NamedQuery(name = "Learner.create", query = "INSERT INTO Learner (name, email, city) VALUES (:name, :email, :city)"),
		@NamedQuery(name = "Learner.update", query = "UPDATE Learner l SET l.name = :name, l.email = :email, l.city = :city WHERE l.learnerId = :learnerId"),
		@NamedQuery(name = "Learner.delete", query = "DELETE FROM Learner l WHERE l.learnerId = :learnerId") })
public class Learner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int learnerId;

	private String city;

	private String email;

	private String name;

	public Learner() {
	}

	public int getLearnerId() {
		return this.learnerId;
	}

	public void setLearnerId(int learnerId) {
		this.learnerId = learnerId;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}