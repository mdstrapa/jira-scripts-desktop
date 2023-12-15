package com.marcosoft.service;

import com.marcosoft.client.JiraClient;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JiraService {

    JiraClient jiraClient = new JiraClient();

    public void updateIssue(String issueKey){

        String requestbody = null;
        try {
            HttpRequest jiraRequest = jiraClient.buildJiraRequest("PUT", "api/2/issue/" + issueKey, requestbody);
            jiraClient.sendJiraRequest(jiraRequest);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void executeTransition(String issueKey){
        String requestBody = "{\n" +
                "    \"transition\": {\n" +
                "        \"id\": \"31\"\n" +
                "    }\n" +
                "}";

        HttpRequest jiraRequest = jiraClient.buildJiraRequest("POST", "api/2/issue/" + issueKey + "/transitions", requestBody);
        jiraClient.sendJiraRequest(jiraRequest);
    }

    public boolean deleteIssue(String issueKey){
        HttpRequest jiraRequest = jiraClient.buildJiraRequest("DELETE","api/2/issue/" + issueKey,"");
        HttpResponse<String> response = jiraClient.sendJiraRequest(jiraRequest);
        if(response != null){
            return response.statusCode() == 204;
        }
        return false;
    }
}
