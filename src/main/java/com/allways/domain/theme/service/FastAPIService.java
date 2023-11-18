package com.allways.domain.theme.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class FastAPIService {

    private final WebClient webClient;

    public FastAPIService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://127.0.0.1:8000").build();
    }

    public String sendKeywordsToFastAPI(String positivePrompt, String negativePrompt) {
        // FastAPI 엔드포인트 URL
        String url = "/generate_image/";

        // FastAPI에 HTTP POST 요청 보내기
        return webClient.post()
                .uri(url)
                .body(BodyInserters.fromFormData("positivePrompt", positivePrompt)
                        .with("negativePrompt", negativePrompt))
                .retrieve()
                .bodyToMono(String.class)
                .block();  // 동기적으로 호출 (비동기로 사용하려면 subscribe 등을 사용)
    }
}
