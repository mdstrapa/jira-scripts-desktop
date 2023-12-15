package com.marcosoft.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JiraClient {

    public String jiraEndPointAddress = "";
    private String jiraAuthToken = "";

    private final HttpClient httpClient = HttpClient.newBuilder().build();

    public HttpRequest buildJiraRequest(String method, String endPointSuffix, String requestBody){
        URI jiraEndPoint = URI.create(jiraEndPointAddress + endPointSuffix);
        HttpRequest jiraRequest  = HttpRequest.newBuilder()
                .uri(jiraEndPoint)
                .method(method, HttpRequest.BodyPublishers.ofString(requestBody))
                .setHeader("Authorization", jiraAuthToken)
                .setHeader("Accept", "application/json")
                .setHeader("Content-Type", "application/json")
                .build();
        return jiraRequest;
    }

    public HttpResponse<String> sendJiraRequest(HttpRequest httpRequest){
        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return httpResponse;
    }

}
