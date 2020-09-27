package com.anju.admin;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.anju.client.Destination;

@Entity

@Table(name="admin_flight")
public class Flight {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_flight;
	private String flight_number;	
	private Date departure_time;
	private int number_seats;	
	private Double price_per_seat;
	
	@ManyToOne
	@JoinColumn(name="id_destination")
	private Destination destination;
	
	@ManyToOne
	@JoinColumn(name="id_source")
	private Destination source;
	
	@ManyToOne
	@JoinColumn(name="id_airline")
	public Airline airline;
		

  	public int getId_flight() {
		return id_flight;
	}
	public Airline getAirline() {
		return airline;
	}
	public void setAirline(Airline airline) {
		this.airline = airline;
	}	
	public Destination getSource() {
		return source;
	}
	public void setSource(Destination source) {
		this.source = source;
	}
	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	public void setId_flight(int id_flight) {
		this.id_flight = id_flight;
	}
	public String getFlight_number() {
		return flight_number;
	}
	public void setFlight_number(String flight_number) {
		this.flight_number = flight_number;
	}
	public Date getDeparture_time() {
		return departure_time;
	}
	public void setDeparture_time(Date departure_time) {
		this.departure_time = departure_time;
	}
	public int getNumber_seats() {
		return number_seats;
	}
	public void setNumber_seats(int number_seats) {
		this.number_seats = number_seats;
	}
	public Double getPrice_per_seat() {
		return price_per_seat;
	}
	public void setPrice_per_seat(Double price_per_seat) {
		this.price_per_seat = price_per_seat;
	}
	@Override
	public String toString() {
		return "Flight [id_flight=" + id_flight + ", flight_number=" + flight_number + ", departure_time=" + departure_time + ", number_seats="
				+ number_seats + ", price_per_seat=" + price_per_seat + "]";
	}
	
	
}
