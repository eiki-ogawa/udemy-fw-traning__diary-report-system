package com.example.demo.app.reports;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class ReportsForm {
	
	private String employee_id;
	
	@NotNull (message = "日付を入力してください")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime report_date;
	
	@NotNull (message = "タイトルを入力してください")
	private String title;
	
	@NotNull (message = "内容を入力してください")
	private String content;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime created_at;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime updated_at;
	
	private Boolean New;
	
	private String name;

	public ReportsForm(String employee_id,
			LocalDateTime report_date,
			String title,
			String content,
			LocalDateTime created_at,
			LocalDateTime updated_at,
			Boolean New,
			String name) {
		this.employee_id = employee_id;
		this.report_date = report_date;
		this.title = title;
		this.content = content;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.name = name;
	}
	
	public String getEmployee_id() {
		return employee_id;
	}
	
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public LocalDateTime getReport_date() {
		return report_date;
	}

	public void setReport_date(LocalDateTime report_date) {
		this.report_date = report_date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	public Boolean getNew() {
		return New;
	}

	public void setNew(Boolean new1) {
		New = new1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
