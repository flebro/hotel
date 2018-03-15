package com.iia.webservices.groupa.hotel.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public LocalDate parse(String date) {
		return LocalDate.parse(date, formatter);
	}
	
	public String format(LocalDate date) {
		return date.format(formatter);
	}
	
}
