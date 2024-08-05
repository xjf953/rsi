package com.shawn.votesystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.function.client.ClientResponse;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = -3698018261030448979L;
    private ClientResponse clientResponse;
    public BaseException(String param) {
        super(param);
    }

    public BaseException(ClientResponse clientResponse) {
        super(clientResponse.statusCode().toString());
        this.clientResponse = clientResponse;
    }

    public ClientResponse getClientResponse() {
        return clientResponse;
    }


}
