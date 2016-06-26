package com.maps.portal.nonjpa.entity;

import java.util.Calendar;

public class Survey {
	
	private String requestNumber;
	private String requestedBy;
	private Double latitude;
	private Double longitude;
	private Long requestedDateUtc;
	private String requestedDateTz;
	private String geom;
	
	private Double distance;
	
	private Calendar fromDate;
	
	private Calendar toDate;

	public String getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

	public String getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Long getRequestedDateUtc() {
		return requestedDateUtc;
	}

	public void setRequestedDateUtc(Long requestedDateUtc) {
		this.requestedDateUtc = requestedDateUtc;
	}

	public String getRequestedDateTz() {
		return requestedDateTz;
	}

	public void setRequestedDateTz(String requestedDateTz) {
		this.requestedDateTz = requestedDateTz;
	}

	public String getGeom() {
		return geom;
	}

	public void setGeom(String geom) {
		this.geom = geom;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Calendar getFromDate() {
		return fromDate;
	}

	public void setFromDate(Calendar fromDate) {
		this.fromDate = fromDate;
	}

	public Calendar getToDate() {
		return toDate;
	}

	public void setToDate(Calendar toDate) {
		this.toDate = toDate;
	}
	
	
	
	
	
}
