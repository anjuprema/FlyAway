package com.anju.admin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name="admin_airline")
public class Airline {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_airline;
	private String airline_name;

	public int getId_airline() {
		return id_airline;
	}
	public void setId_airline(int id_airline) {
		this.id_airline = id_airline;
	}
	public String getAirline_name() {
		return airline_name;
	}
	public void setAirline_name(String airline_name) {
		this.airline_name = airline_name;
	}
	@Override
	public String toString() {
		return "Airline [id_airline=" + id_airline + ", airline_name=" + airline_name + "]";
	}
	
}
