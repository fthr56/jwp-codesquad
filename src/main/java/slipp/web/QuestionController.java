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

@Controller
public class QuestionController {

	@Autowired
	QuestionRepository questionRepository;

	@PostMapping("/questions")
	public ModelAndView create(Question question) {

		questionRepository.save(question);

		return new ModelAndView("redirect:/");
	}

	@GetMapping("/questions/{id}")
	public ModelAndView show(@PathVariable long id) {
		ModelAndView mav = new ModelAndView("qna/show");
		mav.addObject("question", questionRepository.findOne(id));
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
		System.out.println("here is delete");
		questionRepository.delete(id);
		return new ModelAndView("redirect:/");
	}
}
