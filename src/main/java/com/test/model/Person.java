package com.test.model;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

/**
 * The persistent class for the person database table.
 * 
 */
@Entity
@Table(name = "person")
@NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "person_id")
	private Long personId;

	@JsonView(DataTablesOutput.View.class)
	private String email;

	@JsonView(DataTablesOutput.View.class)
	private String firstname;

	@JsonView(DataTablesOutput.View.class)
	private String lastname;

	@JsonView(DataTablesOutput.View.class)
	private String password;

	// bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name = "role_id")
	@JsonView(DataTablesOutput.View.class)
	private Role role;

	// bi-directional many-to-one association to Review
	@OneToMany(mappedBy = "person")
	private List<Review> reviews;

	public Person() {
	}

	public Long getPersonId() {
		return this.personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Review addReview(Review review) {
		getReviews().add(review);
		review.setPerson(this);

		return review;
	}

	public Review removeReview(Review review) {
		getReviews().remove(review);
		review.setPerson(null);

		return review;
	}

}