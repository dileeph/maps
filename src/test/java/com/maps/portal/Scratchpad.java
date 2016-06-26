package com.maps.portal;

import java.util.Calendar;

public class Scratchpad {

	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();c.setTimeInMillis(1424977200556l);
		System.out.println(c);
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		cal.set(2015, 5, 26, 14, 0, 0);		
		System.out.println(cal.getTimeInMillis() );
		cal.set(2015, 5, 22, 14, 0, 0);		
		System.out.println(cal.getTimeInMillis() );
		cal.set(2015, 4, 16, 8, 0, 0);		
		System.out.println(cal.getTimeInMillis() );
		cal.set(2016, 1, 12, 9, 0, 0);		
		System.out.println(cal.getTimeInMillis() );
		//for( String id: TimeZone.getAvailableIDs()) System.out.println(id);
	}

}
