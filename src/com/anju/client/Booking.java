package com.anju.client;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.anju.admin.Flight;

@Entity
@Table(name="admin_booking")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_booking")
	private int BookingId;
	
	@Column(name="id_flight")	
	private int FlightId;
	
	@Column(name="booked_by")
	private String BookedBy;
	
	@Column(name="contact_number")
	private String ContactNumber;
	
	@Column(name="contact_email")
	private String ContactEmail;
	
	@Column(name="ticket_count")
	private int TicketCount;
	
	@Column(name="contact_address")
	private String ContactAddress;
	
	@Column(name="total_fare")
	private Double TotalFare;
	
	private boolean payment_done = false;
	
	private String pnr_number;

//	@ManyToOne
//	@JoinColumn(name="id_flight")	
//	private Flight flight;
	
	public Booking() {
		
	}	

	public Booking(int bookingId, int flightId,  String bookedBy, String contactNumber, String contactEmail,
			int ticketCount, String contactAddress, Double totalFare, Boolean setPaymentDone, String pnrNumber) {
		BookingId = bookingId;
		FlightId = flightId;
		BookedBy = bookedBy;
		ContactNumber = contactNumber;
		ContactEmail = contactEmail;
		TicketCount = ticketCount;
		ContactAddress = contactAddress;
		TotalFare = totalFare;
		payment_done = setPaymentDone;
		pnr_number = pnrNumber;
	}



	public int getBookingId() {
		return BookingId;
	}

	public void setBookingId(int bookingId) {
		BookingId = bookingId;
	}

	public int getFlightId() {
		return FlightId;
	}

	public void setFlightId(int flightId) {
		FlightId = flightId;
	}

	public String getBookedBy() {
		return BookedBy;
	}

	public void setBookedBy(String bookedBy) {
		BookedBy = bookedBy;
	}

	public String getContactNumber() {
		return ContactNumber;
	}

	public void setContactNumber(String contactNumber) {
		ContactNumber = contactNumber;
	}

	public String getContactEmail() {
		return ContactEmail;
	}

	public void setContactEmail(String contactEmail) {
		ContactEmail = contactEmail;
	}

	public int getTicketCount() {
		return TicketCount;
	}

	public void setTicketCount(int ticketCount) {
		TicketCount = ticketCount;
	}

	public String getContactAddress() {
		return ContactAddress;
	}

	public void setContactAddress(String contactAddress) {
		ContactAddress = contactAddress;
	}

	public Double getTotalFare() {
		return TotalFare;
	}

	public void setTotalFare(Double totalFare) {
		TotalFare = totalFare;
	}

	public boolean isPayment_done() {
		return payment_done;
	}

	public void setPayment_done(boolean payment_done) {
		this.payment_done = payment_done;
	}

	public String getPnr_number() {
		return pnr_number;
	}

	public void setPnr_number(String pnr_number) {
		this.pnr_number = pnr_number;
	}

//	public Flight getFlight() {
//		return flight;
//	}
//
//	public void setFlight(Flight flight) {
//		this.flight = flight;
//	}

	@Override
	public String toString() {
		return "Booking [BookingId=" + BookingId + ", BookedBy=" + BookedBy
				+ ", ContactNumber=" + ContactNumber + ", ContactEmail=" + ContactEmail + ", TicketCount=" + TicketCount
				+ ", ContactAddress=" + ContactAddress + ", TotalFare=" + TotalFare+ "]";
	}
	

}
