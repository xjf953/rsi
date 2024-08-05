package com.shawn.votesystem.utils;

import com.shawn.votesystem.dto.ResultMsg;
import com.shawn.votesystem.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;


import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class WebClientUtil {

    @Autowired
    private WebClient webClient;

    public WebClient getWebClient() {
        return webClient;
    }

    public WebClientUtil() {
        this.webClient = WebClient.builder().build();
    }

    public ResponseEntity<ResultMsg> get(String url) {
        log.info("url :: {}", url);
        ResponseEntity<ResultMsg> response = webClient.get().uri(url)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .onStatus(HttpStatus::isError, res -> {
                    logErrorResponse(url, res);
                    return Mono.error(new BaseException(res));
                }).toEntity(ResultMsg.class).block();
        log.info("url :: {}, status :: {}, res :: {}", url, response != null ? response.getStatusCode() : "",
                response != null ? response.getBody() : "");

        return response;
    }

    public static void logErrorResponse(String url, ClientResponse response) {
        log.error("Request url: {}", url);
        log.error("Response status: {}", response.statusCode());
        log.error("Response headers: {}", response.headers().asHttpHeaders());
        response.bodyToMono(String.class).subscribe(body -> log.error("Response body: {}", body));
    }


}
