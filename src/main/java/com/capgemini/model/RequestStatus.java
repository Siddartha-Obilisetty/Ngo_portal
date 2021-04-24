package com.capgemini.model;

import javax.persistence.*;

@Entity
public class RequestStatus 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
	private int id;
	
	
	@OneToOne
	@JoinColumn(name="np_id")
	private NeedyPeople np;

	public RequestStatus() {}

	public RequestStatus(NeedyPeople np) {
		this.np = np;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public NeedyPeople getNp() {
		return np;
	}

	public void setNp(NeedyPeople np) {
		this.np = np;
	}
	
	
}
