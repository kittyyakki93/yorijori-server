package today.wtfood.server.security.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@ConditionalOnProperty(name = "channel.enabled", havingValue = "true")
public class ChannelTalkService {

    @Value("${channel.talk.access.secret}")
    private String accessSecret;

    private final RestTemplate restTemplate;

    public ChannelTalkService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendMessage() {
        String url = "https://api.channel.io/v1/your-endpoint";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessSecret);
        headers.set("Content-Type", "application/json");

        HttpEntity<String> request = new HttpEntity<>("{\"message\": \"Hello World\"}", headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        System.out.println(response.getBody());
    }

}
