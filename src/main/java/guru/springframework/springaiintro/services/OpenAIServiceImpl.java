package guru.springframework.springaiintro.services;

import java.util.Map;
import java.util.Objects;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import guru.springframework.springaiintro.model.Answer;
import guru.springframework.springaiintro.model.GetCapitalRequest;
import guru.springframework.springaiintro.model.GetCapitalResponse;
import guru.springframework.springaiintro.model.Question;

@Service
public class OpenAIServiceImpl implements OpenAIService {

	private final ChatModel chatModel;
			
	public OpenAIServiceImpl(ChatModel chatModel) {
		super();
		this.chatModel = chatModel;
	}
	
	@Value("classpath:template/get-capital-prompt.st")
	private Resource getCapitalPrompt;
	
	@Value("classpath:template/get-capital-with-info-prompt.st")
	private Resource getCapitalWithInfoPrompt;
	
	@Value("classpath:template/get-capital-with-format-prompt.st")
	private Resource getCapitalWithFormatPrompt;
	
	@Autowired
	ObjectMapper objectMapper;
	
	public Answer getCapitalWithInfo(GetCapitalRequest getCapitalRequest) {
		PromptTemplate promptTemplate = new PromptTemplate(getCapitalWithInfoPrompt);
		Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry()));
		ChatResponse response = chatModel.call(prompt);
		
		
		return new Answer(response.getResult().getOutput().getText());
	}
	
	
	public GetCapitalResponse getCapitalWithFormatResponse(GetCapitalRequest getCapitalRequest) {
		BeanOutputConverter<GetCapitalResponse> converter = new BeanOutputConverter<>(GetCapitalResponse.class);
		String format = converter.getFormat();
		
		PromptTemplate promptTemplate = new PromptTemplate(getCapitalWithFormatPrompt);
		Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry()
				, "format", format));
		ChatResponse response = chatModel.call(prompt);
				
		return converter.convert(Objects.requireNonNull(response.getResult().getOutput().getText()));
	}
	
	public Answer getCapital(GetCapitalRequest getCapitalRequest) {
		PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
		Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry()));
		ChatResponse response = chatModel.call(prompt);
				
		String responseString;
		try {
			JsonNode jsonNode = objectMapper.readTree(response.getResult().getOutput().getText());
			responseString = jsonNode.get("answer").asText();
		}catch(JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		return new Answer(responseString);
	}
	
	public Answer getAnswer(Question question) {
		
		PromptTemplate promptTemplate = new PromptTemplate(question.question());
		Prompt prompt = promptTemplate.create();
		ChatResponse response = chatModel.call(prompt);
				
		return new Answer(response.getResult().getOutput().getText());
	}


	public String getAnwer(String question) {
		
		PromptTemplate promptTemplate = new PromptTemplate(question);
		Prompt prompt = promptTemplate.create();
		ChatResponse response = chatModel.call(prompt);
				
		return response.getResult().getOutput().getText();
	}

}
