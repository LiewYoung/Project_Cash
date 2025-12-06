import com.fasterxml.jackson.databind.ObjectMapper;
import top.liewyoung.agentTools.ChatRequest;
import top.liewyoung.agentTools.Message;
import top.liewyoung.agentTools.Role;
import top.liewyoung.config.ConfigLoader;
import top.liewyoung.network.Requests;
import top.liewyoung.network.response.ApiResponse;

import java.io.IOException;
import java.net.http.HttpResponse;

public class ApiResponseTest {
    public static void main(String[] args) {
        String url = "https://api.deepseek.com/chat/completions";
        Requests chat = new Requests(url, ConfigLoader.getValue("apiKey"));
        ChatRequest user1 = new ChatRequest("deepseek-chat",false);

        user1.addMessage(new Message(Role.USER,"Hello"));

        ObjectMapper mapper = new ObjectMapper();

        try{
            HttpResponse<String> response = chat.post(user1);
            ApiResponse apiResponse = mapper.readValue(response.body(),ApiResponse.class);

        }catch(IOException | InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
