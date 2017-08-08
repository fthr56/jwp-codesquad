package slipp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import slipp.domain.User;
import slipp.domain.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/users/{id}")
	public ModelAndView show(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("user/profile");
		mav.addObject("user", userRepository.findOne(id));
		return mav;
	}
	
	@GetMapping("/users")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView("user/list");
		mav.addObject("users", userRepository.findAll());
		return mav;
	}
	
	@PostMapping("/users")
	public ModelAndView create(User user) {	
		userRepository.save(user);
		
		return new ModelAndView("redirect:/users");
	}
	
	@GetMapping("users/{id}/form")
	public ModelAndView update(@PathVariable long id) {
		ModelAndView mav = new ModelAndView("user/updateForm");
		mav.addObject("user", userRepository.findOne(id));
		return mav;
	}
	
	@PostMapping("/users/{id}/update")
	public ModelAndView modify(@PathVariable long id,User user) {
		userRepository.save(user);
		return new ModelAndView("redirect:/users");
	}
}
