package com.java.curso.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.curso.model.Cidade;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MainController {
	
	@GetMapping("/administrativo")
	public String acessarHome() {
		return "administrativo/home";
	}

	@GetMapping("/negado")
	public ModelAndView negado(Cidade cidade) {
		ModelAndView mv = new ModelAndView("/negado");
		return mv;
	}
	
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        // Manually log out the user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        ModelAndView mv = new ModelAndView("/login");
        mv.addObject("Deslogado", "Deslogado");
        return mv;
    }

	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("/login");
		mv.addObject("Logado", "Logado");
		return mv;
	}
}
