package slipp.web;

import javax.servlet.http.HttpSession;

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
	public ModelAndView updateForm(@PathVariable("id") long id, HttpSession session) {
		Object tempUser = session.getAttribute("loginedUser");
		if (tempUser == null) {
			return new ModelAndView("redirect:/");
		}
		
		User loginedUser = (User)tempUser;
		if(!loginedUser.matchId(id)) {
			return new ModelAndView("redirect:/");
		}
		
		ModelAndView mav = new ModelAndView("user/updateForm");
		mav.addObject("user", userRepository.findOne(id));
		return mav;
	}

	@PostMapping("/users/{id}")
	public String update(@PathVariable long id, User user, HttpSession session) {
		
		Object tempUser = session.getAttribute("loginedUser");
		if (tempUser == null) {
			return "redirect:/";
		}
		
		User loginedUser = (User)tempUser;
		if(!loginedUser.matchId(id)) {
			return "redirect:/";
		}
		
		User dbUser = userRepository.findOne(id);
		if (dbUser.update(user)) {
			userRepository.save(dbUser);
		}
		session.setAttribute("loginedUser", dbUser);

		return "redirect:/users";
	}
	
	@GetMapping("/users/login")
	public String loginForm() {
		return "/user/login";
	}
	
	@PostMapping("/users/login2")
	public String login(String userId, String password, HttpSession session){
		
		User dbUser = userRepository.findByUserId(userId);
		if(dbUser == null) {
			return "user/login_failed";
		}
		if(!dbUser.matchPassword(password)) {
			return "user/login_failed";
		}
		session.setAttribute("loginedUser", dbUser);
		
		return "redirect:/";
	}
	
	@GetMapping("users/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginedUser");;
		return "redirect:/";
	}


}
