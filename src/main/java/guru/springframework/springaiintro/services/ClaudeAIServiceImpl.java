package guru.springframework.springaiintro.services;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClaudeAIServiceImpl implements ClaudeAIService {

	private final ChatModel chatModel;
			
	public ClaudeAIServiceImpl(ChatModel chatModel) {
		super();
		this.chatModel = chatModel;
	}

	public String getAnwer(String question) {
		
		PromptTemplate promptTemplate = new PromptTemplate(question);
		Prompt prompt = promptTemplate.create();
		ChatResponse response = chatModel.call(prompt);
				
		return response.getResult().getOutput().getText();
	}

}
