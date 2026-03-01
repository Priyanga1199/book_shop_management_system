package com.example.BookShopApp;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
@Controller 
public class BookController 
{
	@Autowired 
	BookRepository repo;
	private boolean isLoggedIn(HttpSession session)
	{
		return session.getAttribute("username") != null; 
	}
	@GetMapping
	public String home(HttpSession session)
	{
		if (!isLoggedIn(session)) 
			return "redirect:/login"; 
		else return "index"; 
	}
    @GetMapping("/index") 
	public String index() 
	{
		return "index";
	} 
    @GetMapping("/addbook") 
	public String showForm(Model m, HttpSession session)
	{
		if (!isLoggedIn(session))
			return "redirect:/login"; 
		m.addAttribute("book", new Book());
		return "addbook";
		}
	@PostMapping("/addbook")
	public String submitForm(@ModelAttribute Book book, HttpSession session)
	{
		if (!isLoggedIn(session))
			return "redirect:/login";
		repo.save(book);
		return "redirect:/viewbooks";
	}
	@GetMapping("/viewbooks") 
	public String showList(Model m, HttpSession session)
	{
		if (!isLoggedIn(session))
			return "redirect:/login";
		List<Book> li = repo.findAll(); 
		m.addAttribute("list", li);
		return "viewbooks";
	}
	@GetMapping("/userview") 
	public String userShowhowList(Model m, HttpSession session)
	{
		if (!isLoggedIn(session))
			return "redirect:/login";
		List<Book> li = repo.findAll(); 
		m.addAttribute("list", li);
		return "userview";
	}
	@GetMapping("/managebooks") 
	public String manageList(Model m, HttpSession session)
	{
		if (!isLoggedIn(session))
			return "redirect:/login";
		List<Book> li = repo.findAll(); 
		m.addAttribute("list", li);
		return "managebooks";
	}
	@GetMapping("/userbuy") 
	public String userbuy(Model m, HttpSession session)
	{
		if (!isLoggedIn(session))
			return "redirect:/login";
		List<Book> li = repo.findAll(); 
		m.addAttribute("list", li);
		return "userbuy";
	}
	@GetMapping("/editbook/{id}") 
	public String showEditForm(@PathVariable int id, Model m, HttpSession session)
	{
		if (!isLoggedIn(session)) return "redirect:/login";
		Optional<Book> li = repo.findById(id);
		m.addAttribute("book", li.get());
		return "editbook";
	}
	@PostMapping("/update") 
	public String submitEditForm(@ModelAttribute Book book, HttpSession session)
	{
		if (!isLoggedIn(session)) return "redirect:/login";
		repo.save(book);
		return "redirect:/viewbooks";
	}
	@GetMapping("/delete/{id}")
	public String showDeleteForm(@PathVariable int id, HttpSession session) 
	{
		if (!isLoggedIn(session)) return "redirect:/login";
		repo.deleteById(id); 
		return "redirect:/viewbooks";
	}
	@GetMapping("/home")
	public String homePage(HttpSession session)
	{	
		if (!isLoggedIn(session))
			return "redirect:/login"; 
		return "home";
	}
	@GetMapping("/usermenu")
	public String usermenuPage(HttpSession session)
	{	
		if (!isLoggedIn(session))
			return "redirect:/login"; 
		return "usermenu";
	}
	@GetMapping("/admin")
	public String adminPage(HttpSession session)
	{	
		if (!isLoggedIn(session))
			return "redirect:/login"; 
		return "admin";
	}
	@GetMapping("/user")
	public String userPage(HttpSession session)
	{	
		if (!isLoggedIn(session))
			return "redirect:/login"; 
		return "user";
	}
	@GetMapping("/about")
	public String aboutPage(HttpSession session)
	{	
		if (!isLoggedIn(session))
			return "redirect:/login"; 
		return "about";
	}
	@GetMapping("/userabout")
	public String userAboutPage(HttpSession session)
	{	
		if (!isLoggedIn(session))
			return "redirect:/login"; 
		return "userabout";
	}
	@GetMapping("/contact")
	public String contactPage(HttpSession session)
	{	
		if (!isLoggedIn(session))
			return "redirect:/login"; 
		return "contact";
	}
	@GetMapping("/usercontact")
	public String userContactPage(HttpSession session)
	{	
		if (!isLoggedIn(session))
			return "redirect:/login"; 
		return "usercontact";
	}
	@GetMapping("/buy/{id}")
    public String showBuyPage(@PathVariable int id, Model model)
	{
        Book book = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", book);
        return "buy"; // buy.html
    }
	// Process purchase
    @PostMapping("/buy/{id}")
    public String buyBook(@PathVariable int id, @RequestParam int quantity, Model model) 
    {
        Book book = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        if (book.getQuantity() >= quantity) 
        {
            book.setQuantity(book.getQuantity() - quantity);
            repo.save(book);
            model.addAttribute("message", "Purchase successful! Remaining quantity: " + book.getQuantity());
        } 
        else if(book.getQuantity() == 0)
        {
            model.addAttribute("message", "Not enough stock available. Current quantity: " + book.getQuantity());
        }
        else
        {
            model.addAttribute("message", "Not enough stock available. Current quantity: " + book.getQuantity());
        }
        model.addAttribute("book", book);
        return "buy";
    }
}
