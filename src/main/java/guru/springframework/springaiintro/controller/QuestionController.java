package guru.springframework.springaiintro.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.springaiintro.model.Answer;
import guru.springframework.springaiintro.model.GetCapitalRequest;
import guru.springframework.springaiintro.model.GetCapitalResponse;
import guru.springframework.springaiintro.model.Question;
import guru.springframework.springaiintro.services.OpenAIService;

@RestController
public class QuestionController {

	private final OpenAIService openAIService;
	
	public QuestionController(OpenAIService openAIService) {
		this.openAIService = openAIService;
	}
	
	/**
	 * ex JSON- {"question" : "what is llm" }
	 * 			{"answer" : "llm is large language model consist of embeddings" }
	 * @param question
	 * @return
	 */
	@PostMapping("/ask")
	public Answer askQuestion(@RequestBody Question question) {
		return openAIService.getAnswer(question);
	}
	
	/**
	 * ex JSON- {"stateOrCountry" : "what is llm" }
	 * 			{"answer" : "llm is large language model consist of embeddings" }
	 * @param question
	 * @return
	 */
	@PostMapping("/capital")
	public Answer getCapital(@RequestBody GetCapitalRequest getCapitalRequest) {
		return openAIService.getCapital(getCapitalRequest);
	}
	
	/**
	 * ex JSON- {"stateOrCountry" : "what is llm" }
	 * 			{"answer" : "llm is large language model consist of embeddings" }
	 * @param question
	 * @return
	 */
	@PostMapping("/capitalWithFormat")
	public GetCapitalResponse getCapitalWithFormat(@RequestBody GetCapitalRequest getCapitalRequest) {
		return openAIService.getCapital(getCapitalRequest);
	}
	
	/**
	 * ex JSON- {"stateOrCountry" : "what is llm" }
	 * 			{"answer" : "llm is large language model consist of embeddings" }
	 * @param question
	 * @return
	 */
	@PostMapping("/capitalWithInfo")
	public Answer getCapitalWithInfo(@RequestBody GetCapitalRequest getCapitalRequest) {
		return openAIService.getCapitalWithInfo(getCapitalRequest);
	}
}
