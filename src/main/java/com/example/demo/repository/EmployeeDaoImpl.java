package com.example.demo.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	public EmployeeDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Employee> findAll() {
		
		String sql = "SELECT id, code, name, password, admin_flag, created_at, updated_at, delete_flag "
				+ "FROM employee";
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
		
		List<Employee> list = new ArrayList<Employee>();
		
		for(Map<String, Object> result : resultList) {
			
			Employee employee = new Employee();
			employee.setId((int)result.get("id"));
			employee.setCode((String)result.get("Code"));
			employee.setName((String)result.get("name"));
			employee.setPassword((String)result.get("password"));
			employee.setAdmin_flag((Boolean)result.get("admin_flag"));
			employee.setCreated_at(((Timestamp) result.get("created_at")).toLocalDateTime());
			employee.setUpdated_at(((Timestamp) result.get("updated_at")).toLocalDateTime());
			employee.setDelete_flag((Boolean)result.get("delete_flag"));
			
			list.add(employee);
		}
		return list;
	}

	@Override
	public Optional<Employee> findById(int id) {
		String sql = "SELECT id, code, name, password, admin_flag, created_at, updated_at, delete_flag"
				+ "FROM employee WHERE id = ?";
		
		Map<String, Object> result = jdbcTemplate.queryForMap(sql, id);
		
		Employee employee = new Employee();
		employee.setId((int)result.get("id"));
		employee.setCode((String)result.get("Code"));
		employee.setName((String)result.get("name"));
		employee.setPassword((String)result.get("password"));
		employee.setAdmin_flag((Boolean)result.get("admin_flag"));
		employee.setCreated_at(((Timestamp) result.get("created_at")).toLocalDateTime());
		employee.setUpdated_at(((Timestamp) result.get("update_at")).toLocalDateTime());
		employee.setDelete_flag((Boolean)result.get("delete_flag"));
		
		Optional<Employee> employeeOpt = Optional.ofNullable(employee);
		
		return employeeOpt;
	}

	@Override
	public void insert(Employee employee) {
		jdbcTemplate.update("INSERT INTO employee(code, name, password, admin_flag, created_at, updated_at, delete_flag) VALUES(?,?,?,?,?,?,?)",
				employee.getCode(), employee.getName(), employee.getPassword(), employee.getAdmin_flag(), employee.getCreated_at(), employee.getUpdated_at(), employee.getDelete_flag() );

	}

	@Override
	public int update(Employee employee) {
		return jdbcTemplate.update("UPDATE employee SET code = ?, name = ?, password = ?, admin_flag = ?, created_at = ?, updated_at = ?, delete_flag = ? WHERE id = ?",
				employee.getCode(), employee.getName(), employee.getPassword(), employee.getAdmin_flag(), employee.getCreated_at(), employee.getUpdated_at(), employee.getDelete_flag(), employee.getId() );
	}

	@Override
	public int deleteById(int id) {
		return jdbcTemplate.update("UPDATE employee SET delete_flag =1 WHERE id = ?",id);
	}

}
