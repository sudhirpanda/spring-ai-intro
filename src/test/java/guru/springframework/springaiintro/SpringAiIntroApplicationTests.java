package guru.springframework.springaiintro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import guru.springframework.springaiintro.services.ClaudeAIServiceImpl;
import guru.springframework.springaiintro.services.OpenAIServiceImpl;

@SpringBootTest
class SpringAiIntroApplicationTests {

	
	@Autowired OpenAIServiceImpl openAIService;
	@Autowired ClaudeAIServiceImpl claudeAIService;
	
	/*@Test
	void getOpenAiAnswer() {
		String ques = "What is Gen AI?";
		String ans = openAIService.getAnwer(ques);
		System.out.println(ques);
		System.out.println(ans);
	}*/
	

	@Test
	void getClaudeAiAnswer() {
		String ques = "What is Gen AI?";
		String ans = claudeAIService.getAnwer(ques);
		System.out.println(ques);
		System.out.println(ans);
	}
	
    @Test
    void contextLoads() {
    }

}
