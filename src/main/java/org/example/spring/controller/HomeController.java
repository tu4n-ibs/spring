package org.example.spring.controller;

import org.example.spring.model.Employee;
import org.example.spring.repository.IOfficeRepository;
import org.example.spring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private IOfficeRepository iOfficeRepository;

    @GetMapping("/home")
    public ModelAndView showHome(@PageableDefault(value = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("homeF");
        Page<Employee> employees = employeeService.findAll(pageable);
        modelAndView.addObject("employee", employees);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("createF");
        modelAndView.addObject("newEmployee", new Employee());
        modelAndView.addObject("listOffice", iOfficeRepository.findAll());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView saveEmployee(@Valid Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("createF");
            modelAndView.addObject("newEmployee", new Employee());
            modelAndView.addObject("ListErr", bindingResult.getAllErrors());
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        employeeService.save(employee);
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deleteEmployee(Employee employee) {
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        employeeService.delete(employee);
        return modelAndView;
    }

    @GetMapping("/{id}/update")
    public ModelAndView updateForm(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("updateF");
        modelAndView.addObject("employee", employeeService.findById(id).get());
        modelAndView.addObject("listOffice", iOfficeRepository.findAll());
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView editEmployee(@Valid Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("updateF");
            modelAndView.addObject("employee", new Employee());
            modelAndView.addObject("ListErr", bindingResult.getAllErrors());
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        employeeService.update(employee);
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView searchEmployee(@RequestParam("keyword") String keyword, @PageableDefault(value = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("homeF");
        modelAndView.addObject("employee", employeeService.findAllByName(pageable, keyword));
        return modelAndView;
    }

    @GetMapping("/asc")
    public ModelAndView ascEmployee(@PageableDefault(value = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("homeF");
        modelAndView.addObject("employee", employeeService.salaryAsc(pageable));
        return modelAndView;
    }

    @GetMapping("/desc")
    public ModelAndView descEmployee(@PageableDefault(value = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("homeF");
        modelAndView.addObject("employee", employeeService.salaryDesc(pageable));
        return modelAndView;
    }

    @GetMapping("/searchSalary")
    public ModelAndView searchSalary(@RequestParam("salaryOne") int salaryOne, @RequestParam("salaryTwo") int salaryTwo) {
        ModelAndView modelAndView = new ModelAndView("homeF");
        List<Employee> employeesSalary = employeeService.findAllSalaryBetween(salaryOne, salaryTwo);
        modelAndView.addObject("employee", employeesSalary);
        return modelAndView;
    }

    @GetMapping("/{id}/view")
    public ModelAndView viewEmployee(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("viewF");
        modelAndView.addObject("viewEmployee", employeeService.findById(id).get());
        return modelAndView;
    }

}
