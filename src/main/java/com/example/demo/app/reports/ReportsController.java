package com.example.demo.app.reports;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Reports;
import com.example.demo.service.ReportsService;

@Controller
@RequestMapping("/reports")
public class ReportsController {
	
	private final ReportsService reportsService;
	
	public ReportsController(ReportsService reportsService) {
		this.reportsService = reportsService;
	}
	
	@GetMapping
	public String Reports(ReportsForm reportsForm, Model model) {
		
		List<Reports> list = reportsService.findAll();
		
		model.addAttribute("list", list);
		model.addAttribute("title", "タスク一覧");
		
		return "reports/index";
	}
	
	@PostMapping("/insert")
	public String insert(
			@Valid @ModelAttribute ReportsForm reportsForm,
			BindingResult result,
			Model model) {
		
		Reports reports = makeReports(reportsForm, 0);
		
		reportsService.insert(reports);
		return "redirect:/reports";
	}
	
	@GetMapping("/{id}")
	public String showUpdate(
			ReportsForm reportsForm,
			@PathVariable int id,
			Model model) {
		
		Optional<Reports> reportsOpt = reportsService.getReports(id);
		
		Optional<ReportsForm> reportsFormOpt = reportsOpt.map(r -> makeReportsForm(r));
		
		reportsForm = reportsFormOpt.get();
		
		model.addAttribute("reportsForm", reportsForm);
		List<Reports> list = reportsService.findAll();
		model.addAttribute("list", list);
		model.addAttribute("ReportsId", id);
		model.addAttribute("title", "タスク更新");
		
		return "reports/index";
	}
	
	@PostMapping("/update")
	public String update(
		@Valid @ModelAttribute ReportsForm reportsForm,
		BindingResult result,
		@RequestParam("reportsId") int reportsId,
		Model model,
		RedirectAttributes redirectAttributes) {
	
		Reports reports = makeReports(reportsForm, reportsId);
		
		reportsService.update(reports);
		redirectAttributes.addFlashAttribute("complete", "変更が完了しました。");
		return "redirect:/reports/" + reportsId;
	}
	
	@PostMapping("/delete")
	public String delete(
		@RequestParam("reportsId") int id,
		Model model) {
		
		reportsService.deleteById(id);
		
		return "redirect:/reports";
	}
	
	public String duplicate(
			ReportsForm reportsForm,
			int id,
			Model model) {
		
		Optional<Reports> reportsOpt = null;
		
		Optional<ReportsForm> reportsFormOpt = reportsOpt.map(r -> makeReportsForm(r));
		
		if(reportsFormOpt.isPresent()) {
			reportsForm = reportsFormOpt.get();
		}
		
		List<Reports> list = null;
		
		model.addAttribute("list", list);
		model.addAttribute("title", "日報一覧");
		
		return "reports/index";
	}
	
	private Reports makeReports(ReportsForm reportsForm, int reportsId) {
		Reports reports = new Reports();
		if(reportsId != 0) {
			reports.setId(reportsId);
		}
		reports.setId(1);
		reports.setEmployee_id(reportsForm.getEmployee_id());
		reports.setReport_date(reportsForm.getReport_date());
		reports.setTitle(reportsForm.getTitle());
		reports.setContent(reportsForm.getContent());
		reports.setCreated_at(LocalDateTime.now());
		reports.setUpdated_at(LocalDateTime.now());
		return reports;
	}
	
	private ReportsForm makeReportsForm(Reports reports) {
		
		ReportsForm reportsForm = new ReportsForm(null, null, null, null);
		
		reportsForm.setReport_date(reports.getReport_date());
		reportsForm.setTitle(reports.getTitle());
		reportsForm.setContent(reports.getContent());
		
		return reportsForm;
	}
}
