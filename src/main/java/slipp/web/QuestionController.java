package slipp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import slipp.domain.Question;
import slipp.domain.QuestionRepository;
import slipp.domain.User;
import slipp.domain.UserRepository;

@Controller
public class QuestionController {

	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/questions/save/{uid}")
	public ModelAndView create(@PathVariable("uid") long uid, Question question) {
//		question.setUserPk(uId);
		question.setWriter(userRepository.findOne(uid));
		questionRepository.save(question);
		return new ModelAndView("redirect:/");
	}

	@GetMapping("questions/{id}")
	public ModelAndView show(@PathVariable("id") long id) {
//		Question question = questionRepository.findOne(id);
//		User user = userRepository.findOne(question.getUserPk());
		ModelAndView mav = new ModelAndView("qna/show");
		mav.addObject("question", questionRepository.findOne(id));
//		mav.addObject("user", user);
		return mav;
	}
	
	@GetMapping("/questions/{id}/updateForm")
	public ModelAndView updateForm(@PathVariable long id) {
		ModelAndView mav = new ModelAndView("qna/updateForm");
		mav.addObject("question", questionRepository.findOne(id));
		return mav;
	}

	@PostMapping("/questions/{id}/update")
	public ModelAndView update(@PathVariable long id, Question question) {
		questionRepository.save(question);

		return new ModelAndView("redirect:/");
	}
	
	@PutMapping("/questions/{id}/delete")
	public ModelAndView delete(@PathVariable long id) {
		questionRepository.delete(id);
		return new ModelAndView("redirect:/");
	}
}
