package com.example.demo.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Reports;

@Repository
public class ReportsDaoImpl implements ReportsDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	public ReportsDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Reports> findAll() {
		
		String sql = "SELECT id, employee_id, report_date, title, content, created_at, updated_at "
				+ "FROM reports";
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
		
		List<Reports> list = new ArrayList<Reports>();
		
		for(Map<String, Object> result : resultList) {
			
			Reports reports = new Reports();
			reports.setId((int)result.get("id"));
			reports.setEmployee_id((String)result.get("employee_id"));
			reports.setReport_date(((Timestamp) result.get("report_date")).toLocalDateTime());
			reports.setTitle((String)result.get("title"));
			reports.setContent((String)result.get("content"));
			reports.setCreated_at(((Timestamp) result.get("created_at")).toLocalDateTime());
			reports.setUpdated_at(((Timestamp) result.get("updated_at")).toLocalDateTime());
			
			list.add(reports);
		}
		return list;
	}

	@Override
	public Optional<Reports> findById(int id) {
		String sql = "SELECT id, employee_id, report_date, title, content, created_at, updated_at"
				+ "FROM reports WHERE id = ?";
		
		Map<String, Object> result = jdbcTemplate.queryForMap(sql, id);
		
		Reports reports = new Reports();
		reports.setId((int)result.get("id"));
		reports.setEmployee_id((String)result.get("employee_id"));
		reports.setReport_date(((Timestamp) result.get("report_date")).toLocalDateTime());
		reports.setTitle((String)result.get("title"));
		reports.setContent((String)result.get("content"));
		reports.setCreated_at(((Timestamp) result.get("created_at")).toLocalDateTime());
		reports.setUpdated_at(((Timestamp) result.get("updated_at")).toLocalDateTime());
		
		Optional<Reports> reportsOpt = Optional.ofNullable(reports);
		
		return reportsOpt;
	}

	@Override
	public void insert(Reports reports) {
		jdbcTemplate.update("INSERT INTO reports(employee_id, report_date, title, content, created_at, updated_at) VALUES(?,?,?,?,?,?)",
				reports.getEmployee_id(), reports.getReport_date(), reports.getTitle(), reports.getContent(), reports.getCreated_at(), reports.getUpdated_at() );

	}

	@Override
	public int update(Reports reports) {
		return jdbcTemplate.update("UPDATE reports SET employee_id = ?, report_date = ?, title = ?, content = ?, created_at = ?, updated_at = ? WHERE id = ?",
				reports.getEmployee_id(), reports.getReport_date(), reports.getTitle(), reports.getContent(), reports.getCreated_at(), reports.getUpdated_at(), reports.getId() );
	}

	@Override
	public int deleteById(int id) {
		return jdbcTemplate.update("DELETE FROM reports WHERE id = ?", id);
	}

}
