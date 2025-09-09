package ru.microservices.orders.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import ru.microservices.core.entities.User;
import ru.microservices.orders.dto.UserDTO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.rmi.ServerException;
import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceClient {
    private final HttpClient httpClient;
    private final String getUserByIdEndpoint;
    private final ObjectMapper objectMapper;

    public UserServiceClient(@Value("${user.service.getById.endpoint}") String getUserByIdEndpoint,
                             @Value("${connection.timeout.seconds}") String connectionTimeout) {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(Long.parseLong(connectionTimeout))).build();
        this.getUserByIdEndpoint = getUserByIdEndpoint;
        this.objectMapper = new ObjectMapper();
    }

    public Optional<UserDTO> getUserById(UUID id) throws IOException, InterruptedException {
        val url = URI.create(getUserByIdEndpoint.formatted(id));
        val response = httpSend(getHttpRequest(url));
        if(response.statusCode() == HttpStatus.NOT_FOUND.value()) {
            return Optional.empty();
        } else if( response.statusCode() >= HttpStatus.BAD_REQUEST.value()) {
            log.error("Сервис пользователей ответил с ошибкой: " + response.statusCode());
            throw new ServerException("Сервис пользователей ответил с ошибкой");
        }
        return Optional.ofNullable(objectMapper.readValue(response.body(), UserDTO.class));
    }

    private HttpResponse<String> httpSend(HttpRequest httpRequest) throws IOException, InterruptedException {
        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    private HttpRequest getHttpRequest(URI url) {
        return HttpRequest.newBuilder()
                .uri(url)
                .GET()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON.toString())
                .version(HttpClient.Version.HTTP_1_1)
                .build();
    }

}
