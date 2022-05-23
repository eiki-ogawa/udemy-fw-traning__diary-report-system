package com.example.demo.app.employee;

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

import com.example.demo.entity.Employee;
import com.example.demo.entity.Login;
import com.example.demo.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	Login login;
	
	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	/**
	 * è]ã∆àıàÍóóÇÃï\é¶
	 * @param 
	 */
	@GetMapping
	public String Employee(EmployeeForm employeeForm, Model model) {
		
		if(login.getName() == null) {
			return "redirect:/login";
		}
		
		employeeForm.setNew(true);
		employeeForm.setAdmin_flag(false);
		
		List<Employee> list = employeeService.findAll();
		
		model.addAttribute(login);
		model.addAttribute("list", list);
		model.addAttribute("count", "(ëS " + list.size() + " åè)");
		model.addAttribute("title", "è]ã∆àıä«óù");
		
		return "employee/index";
	}
	
	@GetMapping("/form")
	public String form(
			EmployeeForm employeeForm,
			Model model) {
		
		employeeForm.setNew(true);
		employeeForm.setAdmin_flag(false);
		
		model.addAttribute(login);
		model.addAttribute("employeeForm", employeeForm);
		model.addAttribute("title", "è]ã∆àıèÓïÒÅ@êVãKìoò^");
		
		return "employee/form";
	}
	
	@PostMapping("/insert")
	public String insert(
			@Valid @ModelAttribute EmployeeForm employeeForm,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {

		Employee employee = makeEmployee(employeeForm, 0);
		
		if (!result.hasErrors()) {
			employeeService.insert(employee);
			redirectAttributes.addFlashAttribute("complete", "êVãKÉAÉJÉEÉìÉgÇçÏê¨ÇµÇ‹ÇµÇΩ");
			return "redirect:/employee";
			
		} else {
			employeeForm.setNew(true);
			model.addAttribute(login);
			model.addAttribute("employeeForm", employeeForm);
			model.addAttribute("title","êVãKìoò^ validation");
			return "employee/form";
		}
	}
	
	@GetMapping("/{id}")
	public String show(
			EmployeeForm employeeForm,
			@PathVariable int id,
			Model model) {
		
		Optional<Employee> employeeOpt = employeeService.getEmployee(id);
		
		Optional<EmployeeForm> employeeFormOpt = employeeOpt.map(t -> showEmployeeForm(t));
		
		if(employeeFormOpt.isPresent()) {
			employeeForm = employeeFormOpt.get();
		}
		
		model.addAttribute(login);
		model.addAttribute("employeeForm", employeeForm);
		model.addAttribute("employeeId", id);
		model.addAttribute("title", "id["+ id +"]  ÇÃè]ã∆àıèÓïÒÅ@è⁄ç◊");
		
		return "employee/show";
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdate(
			EmployeeForm employeeForm,
			@PathVariable int id,
			Model model) {
		
		Optional<Employee> employeeOpt = employeeService.getEmployee(id);
		
		Optional<EmployeeForm> employeeFormOpt = employeeOpt.map(t -> makeEmployeeForm(t));
		
		if(employeeFormOpt.isPresent()) {
			employeeForm = employeeFormOpt.get();
		}
		
		model.addAttribute("employeeForm", employeeForm);
		model.addAttribute(login);
		model.addAttribute("employeeId", id);
		model.addAttribute("title", "id["+ id +"]  ÇÃè]ã∆àıèÓïÒÅ@ï“èW");
		
		return "employee/form";
	}
	
	@PostMapping("/update")
	public String update(
		@Valid @ModelAttribute EmployeeForm employeeForm,
		BindingResult result,
		@RequestParam("employeeId") int employeeId,
		RedirectAttributes redirectAttributes,
		Model model) {
		
		Employee employee = makeEmployee(employeeForm, employeeId);
		
		if (!result.hasErrors()) {
			employeeService.update(employee);
			model.addAttribute(login);
			redirectAttributes.addFlashAttribute("complete", "çXêVÇ™äÆóπÇµÇ‹ÇµÇΩ");
			return "redirect:/employee/";
			
		} else {
			model.addAttribute(login);
			model.addAttribute("employeeForm", employeeForm);
			redirectAttributes.addFlashAttribute("warning", "çXêVÇ…é∏îsÇµÇ‹ÇµÇΩ");
			return "redirect:/employee/edit/" + employeeId;
		}
	}
	
	@GetMapping("/delete/{id}")
	public String delete(
		@PathVariable int id,
		Model model,
		RedirectAttributes redirectAttributes) {
		
		employeeService.deleteById(id);
		redirectAttributes.addFlashAttribute("complete", "çÌèúÇ™äÆóπÇµÇ‹ÇµÇΩ");
		
		return "redirect:/employee";
	}
	
	private Employee makeEmployee(EmployeeForm employeeForm, int employeeId) {
		Employee employee = new Employee();
		if(employeeId != 0) {
			employee.setId(employeeId);
		}
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
		
		EmployeeForm employeeForm = new EmployeeForm(null, null, null, false, null, null, false, true);
		
		employeeForm.setCode(employee.getCode());
		employeeForm.setName(employee.getName());
		employeeForm.setPassword(employee.getPassword());
		employeeForm.setAdmin_flag(employee.getAdmin_flag());
		employeeForm.setCreated_at(LocalDateTime.now());
		employeeForm.setUpdated_at(LocalDateTime.now());
		employeeForm.setDelete_flag(false);
		employeeForm.setNew(false);
		
		return employeeForm;
	}
	
	private EmployeeForm showEmployeeForm(Employee employee) {
		
		EmployeeForm employeeForm = new EmployeeForm(null, null, null, false, null, null, false, true);
		
		employeeForm.setCode(employee.getCode());
		employeeForm.setName(employee.getName());
		employeeForm.setPassword(employee.getPassword());
		employeeForm.setAdmin_flag(employee.getAdmin_flag());
		employeeForm.setCreated_at(employee.getCreated_at());
		employeeForm.setUpdated_at(employee.getUpdated_at());
		employeeForm.setDelete_flag(false);
		employeeForm.setNew(false);
		
		return employeeForm;
	}
}
