package com.jsp.crud2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jsp.crud2.entity.Moviezz;
import com.jsp.crud2.repository.MoviezzRepo;

@Controller
public class MoviesController {

	@Autowired
	MoviezzRepo moviezzRepo;
	
	
	@GetMapping({"/","/main"})
	public String load() {
		return"main.html";
	}
	
	@GetMapping("/addData")
	public String addData() {
		return "add.html";
	}
	
	@PostMapping("/addDetailes")
	public String submit(Moviezz moviezz , RedirectAttributes attributes)  {
	moviezzRepo.save(moviezz);
		attributes.addFlashAttribute("message","DATA DATA SUCCESSFULLY!!");
		return"redirect:/main";
	}
	
	@GetMapping("/manage")
	public String show(ModelMap map) {
	List<Moviezz>list=moviezzRepo.findAll();
	map.put("list", list);
		return"view.html";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam long id ,ModelMap map) {
	Moviezz	moviezz = moviezzRepo.findById(id).orElseThrow();
		map.put("list", moviezz);
		return"edit.html";
	}
	
	@PostMapping("/updateData")
	public String update(Moviezz moviezz, RedirectAttributes attributes) {
		moviezzRepo.save(moviezz);
		attributes.addFlashAttribute("message","UPDATED DATA SUCCESSFULLY!!");
		return "redirect:/manage";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam long id , RedirectAttributes attributes) {
		moviezzRepo.deleteById(id);
		attributes.addFlashAttribute("message","DATA IS DELETED SUCCESSFULLY!!");
		return"redirect:/manage";
	}
	
	
	
	
	
}
