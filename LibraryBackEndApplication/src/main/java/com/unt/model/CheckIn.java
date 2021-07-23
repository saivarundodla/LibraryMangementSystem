package com.unt.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity(name="checkin")
public @Data class CheckIn {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="checkInId")
	private Integer checkInId;
	@ManyToOne
    @JoinColumn(name="bookId", nullable=false)
	private Book book;
	@ManyToOne
    @JoinColumn(name="userId", nullable=false)
	private User user;
	private Date checkInDate;
	private Date checkOutDate;
	private Date returnedDate;
	private Boolean requestApproval;
	private Float penalty;
}
