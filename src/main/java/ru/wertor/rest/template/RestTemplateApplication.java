package ru.wertor.rest.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import ru.wertor.rest.template.model.User;

import java.util.Arrays;

@SpringBootApplication
public class RestTemplateApplication {


    public static void main(String[] args) {
        String code = "";
        User addUser = new User(3l, "James", "Brown", (byte)24);
        User editUser = new User(3l, "Thomas", "Shelby", (byte)24);

        SpringApplication.run(RestTemplateApplication.class, args);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> response = restTemplate.exchange("http://94.198.50.185:7081/api/users", HttpMethod.GET, entity, String.class);
        String cookie = response.getHeaders().getFirst(headers.SET_COOKIE);
        System.out.println("Response: " + response + "\n");
        System.out.println("Set-Cookie: " + cookie + "\n");
        System.out.println("********* FINISH *******");

        headers.add("cookie", cookie);
        HttpEntity<User> userEntity = new HttpEntity<>(addUser, headers);
        response = restTemplate.exchange("http://94.198.50.185:7081/api/users", HttpMethod.POST, userEntity, String.class);
        code += response.getBody();
        System.out.println("Response: " + response.getBody() + "\n");
        System.out.println("Code: " + code + "\n");
        System.out.println("********* FINISH *******");

        userEntity = new HttpEntity<>(editUser, headers);
        response = restTemplate.exchange("http://94.198.50.185:7081/api/users", HttpMethod.PUT, userEntity, String.class);
        code += response.getBody();
        System.out.println("Response: " + response.getBody() + "\n");
        System.out.println("Code: " + code + "\n");
        System.out.println("********* FINISH *******");

        userEntity = new HttpEntity<>(editUser, headers);
        response = restTemplate.exchange("http://94.198.50.185:7081/api/users/3", HttpMethod.DELETE, userEntity, String.class);
        code += response.getBody();
        System.out.println("Response: " + response.getBody() + "\n");
        System.out.println("Code: " + code + "\n");
        System.out.println("********* FINISH *******");
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}