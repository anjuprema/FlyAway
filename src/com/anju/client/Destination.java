package com.anju.client;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin_destination")
public class Destination {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_destination;
	private String destination;
	public int getId_destination() {
		return id_destination;
	}
	public void setId_destination(int id_destination) {
		this.id_destination = id_destination;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	@Override
	public String toString() {
		return "Destination [id_destination=" + id_destination + ", destination=" + destination + "]";
	}		
}
