package com.example.demo.app.reports;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.entity.Login;
import com.example.demo.entity.Reports;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.ReportsService;

@Controller
@RequestMapping("/reports")
public class ReportsController {
	
	@Autowired
	Login login;
	
	private final ReportsService reportsService;

	private final EmployeeService employeeService;
	
	public ReportsController(
			ReportsService reportsService,
			EmployeeService employeeService
			) {
		this.reportsService = reportsService;
		this.employeeService = employeeService;
	}
	
	
	@GetMapping
	public String Reports(ReportsForm reportsForm,
			Model model) {
		
		if(login.getName() == null) {
			return "redirect:/login";
		}
		
		reportsForm.setNew(true);
		
		List<Reports> list = reportsService.findAll();
		
		model.addAttribute("list", list);
		model.addAttribute("count", "(全 " + list.size() + " 件)");
		model.addAttribute("title", "日報管理");
		model.addAttribute(login);
		
		return "reports/index";
	}
	
	@GetMapping("/form")
	public String form(
			ReportsForm reportsForm,
			Model model) {
		
		reportsForm.setNew(true);
		
		model.addAttribute(login);
		model.addAttribute("reportsForm", reportsForm);
		model.addAttribute("title", "日報　新規登録");
		
		return "reports/form";
	}
	
	@PostMapping("/insert")
	public String insert(
			@Valid @ModelAttribute ReportsForm reportsForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {

		Reports reports = makeReports(reportsForm, 0);
		
		if (!result.hasErrors()) {
			reportsService.insert(reports);
			redirectAttributes.addFlashAttribute("complete", "日報を新規作成しました");
			return "redirect:/reports";
		} else {
			reportsForm.setNew(true);
			model.addAttribute(login);
			model.addAttribute("reportsForm", reportsForm);
			List<Reports> list = reportsService.findAll();
			model.addAttribute("list", list);
			model.addAttribute("title", "新規登録 validation");
			return "reports/form";
		}
		
	}
	
	@GetMapping("/{id}")
	public String show(
			ReportsForm reportsForm,
			@PathVariable int id,
			Model model) {
		
		Optional<Reports> reportsOpt = reportsService.getReports(id);
		
		Optional<ReportsForm> reportsFormOpt = reportsOpt.map(r -> showReportsForm(r));
		
		if(reportsFormOpt.isPresent()) {
			reportsForm = reportsFormOpt.get();
		}
			
		model.addAttribute(login);
		model.addAttribute("reportsForm", reportsForm);
		model.addAttribute("reportsId", id);
		model.addAttribute("title", "id["+ id +"]  の日報　詳細");
		
		return "reports/show";
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdate(
			ReportsForm reportsForm,
			@PathVariable int id,
			Model model) {
		
		Optional<Reports> reportsOpt = reportsService.getReports(id);
		
		Optional<ReportsForm> reportsFormOpt = reportsOpt.map(r -> makeReportsForm(r));
		
		if(reportsFormOpt.isPresent()) {
			reportsForm = reportsFormOpt.get();
		}
		
		model.addAttribute(login);
		model.addAttribute("reportsForm", reportsForm);
		model.addAttribute("reportsId", id);
		model.addAttribute("title","id["+ id +"]  の日報　編集");
		
		return "reports/form";
	}
	
	@PostMapping("/update")
	public String update(
		@Valid @ModelAttribute ReportsForm reportsForm,
		BindingResult result,
		@RequestParam("reportsId") int reportsId,
		Model model,
		RedirectAttributes redirectAttributes) {
	
		Reports reports = makeReports(reportsForm, reportsId);
		
		if (!result.hasErrors()) {
			reportsService.update(reports);
			model.addAttribute(login);
			redirectAttributes.addFlashAttribute("complete", "更新が完了しました");
			return "redirect:/reports/";
		} else {
			model.addAttribute(login);
			model.addAttribute("reportsForm", reportsForm);
			redirectAttributes.addFlashAttribute("warning", "更新に失敗しました");
			return "redirect:/reports/edit/" + reportsId;
		}
		
	}
	
	@GetMapping("/delete/{id}")
	public String delete(
		@PathVariable int id,
		Model model,
		RedirectAttributes redirectAttributes) {
		
		reportsService.deleteById(id);
		redirectAttributes.addFlashAttribute("complete", "蜑企勁縺悟ｮ御ｺ�縺励∪縺励◆縲�");
		
		return "redirect:/reports";
	}

	
	private Reports makeReports(ReportsForm reportsForm, int reportsId) {
		Reports reports = new Reports();
		if(reportsId != 0) {
			reports.setId(reportsId);
		}
		reports.setEmployee_id(login.getCode());
		reports.setReport_date(reportsForm.getReport_date());
		reports.setTitle(reportsForm.getTitle());
		reports.setContent(reportsForm.getContent());
		reports.setCreated_at(LocalDateTime.now());
		reports.setUpdated_at(LocalDateTime.now());
		return reports;
	}
	
	private ReportsForm makeReportsForm(Reports reports) {
		
		ReportsForm reportsForm = new ReportsForm(null, null, null, null, null, null, false, null);
		
		reportsForm.setReport_date(reports.getReport_date());
		reportsForm.setTitle(reports.getTitle());
		reportsForm.setContent(reports.getContent());
		reportsForm.setCreated_at(LocalDateTime.now());
		reportsForm.setUpdated_at(LocalDateTime.now());
		reportsForm.setNew(false);
		
		return reportsForm;
	}
	
private ReportsForm showReportsForm(Reports reports) {
		
		ReportsForm reportsForm = new ReportsForm(null, null, null, null, null, null, false, null);
		
		reportsForm.setName(employeeService.getName(reports.getEmployee_id()));
		reportsForm.setEmployee_id(reports.getEmployee_id());
		reportsForm.setReport_date(reports.getReport_date());
		reportsForm.setTitle(reports.getTitle());
		reportsForm.setContent(reports.getContent());
		reportsForm.setCreated_at(reports.getCreated_at());
		reportsForm.setUpdated_at(reports.getUpdated_at());
		reportsForm.setNew(false);
		
		return reportsForm;
	}
}
