package com.example.demo.app.employee;

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

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	/**
	 * 従業員の一覧を表示
	 * @param 
	 */
	@GetMapping
	public String Employee(EmployeeForm employeeForm, Model model) {
		
		List<Employee> list = employeeService.findAll();
		
		model.addAttribute("list", list);
		model.addAttribute("title", "従業員一覧");
		
		return "employee/index";
	}
	
	@PostMapping("/insert")
	public String insert(
			@Valid @ModelAttribute EmployeeForm employeeForm,
			BindingResult result,
			Model model) {
		
		if (!result.hasErrors()) {
			Employee employee = makeEmployee(employeeForm, 0);
			employeeService.insert(employee);
			return "redirect:/employee";
		} else {
			model.addAttribute("employeeForm", employeeForm);
			List<Employee> list = employeeService.findAll();
			model.addAttribute("list", list);
			model.addAttribute("title","従業員一覧（バリデーション）");
			return "employee/index";
		}
	}
	
	@GetMapping("/{id}")
	public String showUpdate(
			EmployeeForm employeeForm,
			@PathVariable int id,
			Model model) {
		
		Optional<Employee> employeeOpt = employeeService.getEmployee(id);
		
		Optional<EmployeeForm> employeeFormOpt = employeeOpt.map(t -> makeEmployeeForm(t));
		
		employeeForm = employeeFormOpt.get();
		
		model.addAttribute("employeeForm", employeeForm);
		List<Employee> list = employeeService.findAll();
		model.addAttribute("list", list);
		model.addAttribute("employeeId", id);
		model.addAttribute("title", "更新用フォーム");
		
		return "employee/index";
	}
	
	@PostMapping("/update")
	public String update(
		@Valid @ModelAttribute EmployeeForm employeeForm,
		Model model,
		BindingResult result,
		@RequestParam("employeeId") int employeeId,
		RedirectAttributes redirectAttributes) {
		
		if(!result.hasErrors()) {
			Employee employee = makeEmployee(employeeForm, employeeId);
			
			employeeService.update(employee);
			redirectAttributes.addFlashAttribute("complete", "変更が完了しました");
			return "redirect:/employee/" + employeeId;
		} else {
			model.addAttribute("employeeForm", employeeForm);
			model.addAttribute("title", "更新用フォーム");
			model.addAttribute("employeeId", employeeId);
			return "employee/index";
		}
	}
	
	@PostMapping("/delete")
	public String delete(
		@RequestParam("employeeId") int id,
		Model model) {
		
		employeeService.deleteById(id);
		
		return "redirect:/employee";
	}
	
	public String duplicate(
			EmployeeForm employeeForm,
			int id,
			Model model) {
		
		Optional<Employee> employeeOpt = null;
		
		Optional<EmployeeForm> employeeFormOpt = employeeOpt.map(t -> makeEmployeeForm(t));
		
		if(employeeFormOpt.isPresent()) {
			employeeForm = employeeFormOpt.get();
		}
		
		List<Employee> list = null;
		
		model.addAttribute("list", list);
		model.addAttribute("title", "従業員一覧");
		
		return "employee/index";
	}
	
	
	private Employee makeEmployee(EmployeeForm employeeForm, int employeeId) {
		Employee employee = new Employee();
		if(employeeId != 0) {
			employee.setId(employeeId);
		}
		employee.setId(1);
		employee.setCode(employeeForm.getCode());
		employee.setName(employeeForm.getName());
		employee.setPassword(employeeForm.getPassword());
		employee.setAdmin_flag(employeeForm.getAdmin_flag());
		employee.setCreated_at(LocalDateTime.now());
		employee.setUpdated_at(LocalDateTime.now());
		employee.setDelete_flag(false);
		return employee;
	}
	
	private EmployeeForm makeEmployeeForm(Employee employee) {
		
		EmployeeForm employeeForm = new EmployeeForm();
		
		employeeForm.setCode(employee.getCode());
		employeeForm.setName(employee.getName());
		employeeForm.setPassword(employee.getPassword());
		employeeForm.setAdmin_flag(employee.getAdmin_flag());
		employeeForm.setUpdated_at(LocalDateTime.now());
		
		return employeeForm;
	}
}
