package com.capgemini.model;

import javax.persistence.*;

@Entity
public class RequestStatus 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private boolean status;
	
	@OneToOne
	@JoinColumn(name="np_id")
	private NeedyPeople np;

	public RequestStatus() {}

	public RequestStatus(Long id, boolean status, NeedyPeople np) {
		this.id = id;
		this.status = status;
		this.np = np;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public NeedyPeople getNp() {
		return np;
	}

	public void setNp(NeedyPeople np) {
		this.np = np;
	}
	
	
}
