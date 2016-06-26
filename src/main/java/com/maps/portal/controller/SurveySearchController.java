package com.maps.portal.controller;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.maps.portal.nonjpa.entity.Survey;
import com.maps.portal.nonjpa.repo.SearchRepository;

@Transactional
@RestController
public class SurveySearchController {
	
	@Autowired private SearchRepository searchRepository;

	@RequestMapping(value="/findsurveys", method=RequestMethod.POST)
	public ResponseEntity<List<Survey>> findSurveys(@RequestBody Survey survey){
		
		return new ResponseEntity<List<Survey>>(searchRepository.searchSurvey(survey), 
				HttpStatus.OK);
	}
}
