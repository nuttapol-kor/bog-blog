package project.box.service;

import project.box.dto.CommentRequest;
import project.box.dto.CommentResponse;
import project.box.dto.StatusRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtAccessTokenService tokenService;

    public CommentRequest createComment(CommentRequest comment) {
        String token = tokenService.requestAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", "Bearer " + token);
        headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
        HttpEntity entity = new HttpEntity(comment,headers);

        String url = "http://localhost:8091/api/comment";

        ResponseEntity<CommentRequest> response = restTemplate.exchange(url, HttpMethod.POST, entity, CommentRequest.class);

        return response.getBody();
    }

    public List<CommentResponse> getBlogComment(UUID blogId) {
        String token = tokenService.requestAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", "Bearer " + token);
        HttpEntity entity = new HttpEntity(headers);

        String url = "http://localhost:8091/api/comment/" + blogId;

        ResponseEntity<CommentResponse[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, CommentResponse[].class);
        CommentResponse[] comments = response.getBody();
        return Arrays.asList(comments);
    }

    public StatusRequest like(StatusRequest statusRequest) {
        String token = tokenService.requestAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", "Bearer " + token);
        headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
        HttpEntity entity = new HttpEntity(statusRequest,headers);

        String url = "http://localhost:8091/api/comment/like";

        ResponseEntity<StatusRequest> response = restTemplate.exchange(url, HttpMethod.POST, entity, StatusRequest.class);

        return response.getBody();
    }

}
