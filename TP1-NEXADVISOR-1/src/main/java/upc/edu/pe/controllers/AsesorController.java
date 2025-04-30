package upc.edu.pe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import upc.edu.pe.entities.Asesor;
import upc.edu.pe.serviceinterface.AsesorService;

@Controller
@RequestMapping("/asesores")
public class AsesorController {
	
	@Autowired
	private AsesorService asesorService;
	
	@GetMapping("/nuevo")
	public String newAsesor(Model model) { 
		model.addAttribute("a", new Asesor());
		return "asesor/fmrRegistro";
		
	}
	
	@PostMapping("/guardar")
	public String saveAsesor(Asesor ase, BindingResult binRes, Model model) {
	
		if (binRes.hasErrors()) {
			return "asesor/frmRegistro";
		}
		else {
			asesorService.insert(ase);
			model.addAttribute("mensaje", "Se registro Correctamente");
			return "redirect:/asesores/nuevo";
		}
	}
	
	
}
		
		

	


