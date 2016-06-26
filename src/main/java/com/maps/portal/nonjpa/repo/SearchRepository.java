package com.maps.portal.nonjpa.repo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.maps.portal.nonjpa.entity.Survey;

@Repository
public class SearchRepository {
	private final JdbcTemplate jdbcTemplate;

	private String surveyQuery = " SELECT sur.request_number, sur.requested_by, sur.requested_date_utc, sur.requested_date_tz, sur.latitude, sur.longitude ";
	private String nearestSurveyQuerySelect=" ,ST_Distance(geog, poi)*0.000621371 AS distance_mile ";
 					
	
	private String nearestSurveyQueryFrom1 = ", ( ";
 							
	private String nearestSurveyQueryFrom2 = ") as poi ";  
	private String nearestSurveyQueryWhere = "   ST_DWithin(sur.geog, poi, ?)  ";
	private String nearestSurveyQueryOrderBy = " ST_Distance(sur.geog, poi) ";
	
	private String requestedByQueryWhere = " LOWER(sur.requested_by) like ? ";
	
	private String requestNumberQueryWhere = " LOWER(sur.request_number) like ? ";
	
	@Autowired
	public SearchRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Survey> searchSurvey(final Survey criteria){
		
		List<Object> params = new ArrayList<>();
		
		StringBuilder spatialStmtBuilder = new StringBuilder(surveyQuery);
		
	
		boolean rangeQueryPresent = false;
		if(criteria.getLatitude() != null && criteria.getLongitude() != null){
		
			
			spatialStmtBuilder.append(nearestSurveyQuerySelect).append(" FROM survey sur ").append(nearestSurveyQueryFrom1).
				append("select ST_GeomFromText('POINT(").append(criteria.getLongitude()).
				append(" ").append(criteria.getLatitude()).append(")', 4326) as poi").append(nearestSurveyQueryFrom2).append(" WHERE " ).append(nearestSurveyQueryWhere);
			rangeQueryPresent = true;
			
			Double distance = 3000d;
			if(criteria.getDistance() != null){
				distance = criteria.getDistance();
			}
			params.add(distance*1609.34);
		}

		if(!rangeQueryPresent){
			spatialStmtBuilder.append(" ,0 AS distance_mile FROM survey sur WHERE ");
		}else{
			spatialStmtBuilder.append(" AND ");
		}
		
		
		if(org.apache.commons.lang3.StringUtils.isNotBlank(criteria.getRequestedBy())){
			spatialStmtBuilder.append(requestedByQueryWhere).append(" AND ");	
			params.add(criteria.getRequestedBy().toLowerCase() + "%");
			
		}
		if(org.apache.commons.lang3.StringUtils.isNotBlank(criteria.getRequestNumber())){
			spatialStmtBuilder.append(requestNumberQueryWhere).append(" AND ");			
			params.add(criteria.getRequestNumber().toLowerCase() + "%");
		}
		
		if(criteria.getFromDate() != null && criteria.getToDate() != null){
			spatialStmtBuilder.append(" sur.requested_date_utc between ? and ?").append(" AND ");
			params.add(criteria.getFromDate().getTimeInMillis());
			params.add(criteria.getToDate().getTimeInMillis());
		}
		
		spatialStmtBuilder.append(" 1=1 ");
		
		RowMapper<Survey> rowMapper = new RowMapper<Survey>(){

			@Override
			public Survey mapRow(ResultSet rs, int rownum) throws java.sql.SQLException {
				Survey survey = new Survey();
				survey.setDistance(rs.getDouble("distance_mile"));
				survey.setLatitude(rs.getDouble("latitude"));
				survey.setLongitude(rs.getDouble("longitude"));
				survey.setRequestNumber(rs.getString("request_number"));
				survey.setRequestedBy(rs.getString("requested_by"));
				survey.setRequestedDateTz(rs.getString("requested_date_tz"));
				survey.setRequestedDateUtc(rs.getLong("requested_date_utc"));
				
				return survey;
			}
		};
		
	
		
		if(rangeQueryPresent){
			spatialStmtBuilder.append(" ORDER BY ").append(nearestSurveyQueryOrderBy);
		}
		spatialStmtBuilder.append(" LIMIT  10 ");
		
		return jdbcTemplate.query(spatialStmtBuilder.toString(), params.toArray(), rowMapper);
				
	}
}
