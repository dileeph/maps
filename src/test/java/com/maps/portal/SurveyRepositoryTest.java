package com.maps.portal;

import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.maps.portal.MapSearchServiceApplication;
import com.maps.portal.nonjpa.entity.Survey;
import com.maps.portal.nonjpa.repo.SearchRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MapSearchServiceApplication.class)
@WebAppConfiguration
public class SurveyRepositoryTest {
	
	
	@Autowired
	private SearchRepository searchRepo;
	@Test
	public void checkDataFromSurveyTable(){
		
		Survey criteria = new Survey();
		criteria.setDistance(2224d); 
		criteria.setLongitude(-72.5215d);
		criteria.setLatitude(41.7759d);
		List<Survey> results = searchRepo.searchSurvey(criteria);
		
		Assert.assertTrue(results.size() == 6);
	}
	
	@Test
	public void checkWithOnlyRequestNumber(){
		Survey criteria = new Survey();
		criteria.setRequestNumber("T");
		List<Survey> results = searchRepo.searchSurvey(criteria);
		
		Assert.assertTrue(results.size() == 2);
	}
	@Test
	public void checkWithOnlyRequestBy(){
		Survey criteria = new Survey();
		criteria.setRequestedBy("Ta");
		List<Survey> results = searchRepo.searchSurvey(criteria);
		
		Assert.assertTrue(results.size() == 2);
	}
	
	@Test
	public void checkWithRequestNumberRequestByMismatch(){
		Survey criteria = new Survey();
		criteria.setRequestedBy("Ta");
		criteria.setRequestNumber("A");
		List<Survey> results = searchRepo.searchSurvey(criteria);
		
		Assert.assertTrue(results.size() == 0);
	}
	
	@Test
	public void checkWithRequestNumberRequestByMatch(){
		Survey criteria = new Survey();
		criteria.setRequestedBy("Ta");
		criteria.setRequestNumber("T");
		List<Survey> results = searchRepo.searchSurvey(criteria);
		
		Assert.assertTrue(results.size() == 2);
	}


	@Test
	public void checkWithRequestNumberEmptyRequestByPresent(){
		Survey criteria = new Survey();
		criteria.setRequestedBy("Ta");
		criteria.setRequestNumber(" ");
		List<Survey> results = searchRepo.searchSurvey(criteria);
		
		Assert.assertTrue(results.size() == 2);
	}
	
	@Test
	public void checkWithRequestNumberPresentRequestByEmpty(){
		Survey criteria = new Survey();
		criteria.setRequestedBy(" ");
		criteria.setRequestNumber("A");
		List<Survey> results = searchRepo.searchSurvey(criteria);
		
		Assert.assertTrue(results.size() == 5);
	}
	
	@Test
	public void checkWithDateRange(){
		Survey criteria = new Survey();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(1424977200555l);
		criteria.setFromDate(cal);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTimeInMillis(1424977200557l);
		criteria.setToDate(cal2);
		List<Survey> results = searchRepo.searchSurvey(criteria);
		
		Assert.assertTrue(results.size() == 1);
	}
	
	@Test
	public void checkWithDateRangeFromAbsentToPresent(){
		Survey criteria = new Survey();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(1424977200555l);
		criteria.setFromDate(null);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTimeInMillis(1424977200557l);
		criteria.setToDate(cal2);
		List<Survey> results = searchRepo.searchSurvey(criteria);
		
		Assert.assertTrue(results.size() == 8);
	}
	@Test
	public void checkWithDateRangeFromPresentToAbsent(){
		Survey criteria = new Survey();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(1424977200555l);
		criteria.setFromDate(cal);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTimeInMillis(1424977200557l);
		criteria.setToDate(null);
		List<Survey> results = searchRepo.searchSurvey(criteria);
		
		Assert.assertTrue(results.size() == 8);
	}
	@Test
	public void checkWithRequestByAndDate(){
		Survey criteria = new Survey();
		criteria.setRequestedBy("Wa");
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(1424977200555l);
		criteria.setFromDate(cal);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTimeInMillis(1424977200557l);
		criteria.setToDate(cal2);
		List<Survey> results = searchRepo.searchSurvey(criteria);
		
		Assert.assertTrue(results.size() == 1);
	}
	@Test
	public void checkWithRequestNumberAndDate(){
		Survey criteria = new Survey();
		criteria.setRequestNumber("A");
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(1424977200555l);
		criteria.setFromDate(cal);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTimeInMillis(1424977200557l);
		criteria.setToDate(cal2);
		List<Survey> results = searchRepo.searchSurvey(criteria);
		
		Assert.assertTrue(results.size() == 1);
	}
	@Test
	public void checkWithRequestRangeAndNoDistance(){

		Survey criteria = new Survey();
		criteria.setDistance(null); 
		criteria.setLongitude(-72.5215d);
		criteria.setLatitude(41.7759d);
		List<Survey> results = searchRepo.searchSurvey(criteria);
		
		Assert.assertTrue(results.size() == 8);
	}
	
	@Test
	public void checkWithRequestRangeAndDistance(){

		Survey criteria = new Survey();
		criteria.setDistance(2224d); 
		criteria.setLongitude(-72.5215d);
		criteria.setLatitude(41.7759d);
		List<Survey> results = searchRepo.searchSurvey(criteria);
		
		Assert.assertTrue(results.size() == 6);
	}
	

	@Test
	public void checkWithRequestRangeLatMissing(){

		Survey criteria = new Survey();
		 
		criteria.setLongitude(-72.5215d);
		criteria.setLatitude(null);
		List<Survey> results = searchRepo.searchSurvey(criteria);
		
		Assert.assertTrue(results.size() == 8);
	}
	
	@Test
	public void checkWithRequestRangeLonMissing(){

		Survey criteria = new Survey();
		
		criteria.setLongitude(null);
		criteria.setLatitude(41.7759d);
		List<Survey> results = searchRepo.searchSurvey(criteria);
		
		Assert.assertTrue(results.size() == 8);
	}
	
	@Test
	public void checkWithRequestRangeLatMissingAndRangePresent(){

		Survey criteria = new Survey();
		criteria.setDistance(2224d); 
		criteria.setLongitude(-72.5215d);
		criteria.setLatitude(null);
		List<Survey> results = searchRepo.searchSurvey(criteria);
		
		Assert.assertTrue(results.size() == 8);
	}
	
	@Test
	public void checkWithRequestRangeLonMissingAndRangePresent(){

		Survey criteria = new Survey();
		criteria.setDistance(2224d); 
		criteria.setLongitude(null);
		criteria.setLatitude(41.7759d);
		List<Survey> results = searchRepo.searchSurvey(criteria);
		
		Assert.assertTrue(results.size() == 8);
	}
	
	
	

}
