package com.example.BookShopApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;
@Controller
public class LoginController
{
	@Autowired 
	private LoginRepository repo;
	@GetMapping("/login")
	public String loginPage(Model m) 
	{
		m.addAttribute("login", new Login()); 
		return "login"; 
	}
	@PostMapping("/login")
	public String doLogin(Login user, HttpSession session, Model m) 
	{
		Login u = repo.findByUsernameAndPassword( user.getUsername(), user.getPassword());
		if (u == null) 
		{
			m.addAttribute("msg", "Invalid Username or Password!");
			return "login"; 
		}
		if (u != null) 
		{
            session.setAttribute("username", u.getUsername());
            session.setAttribute("role", u.getRole());
            if (u.getRole().equals("admin")) 
            {
            	return "admin";
            } 
            else 
            {
            	return "user";
            }
        }
		m.addAttribute("msg", "Invalid Username or Password!");
		return "login"; 
	}
	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		session.invalidate();
		return "redirect:/login";
	} 
} 