package com.krunal.api.email.service;

import com.krunal.api.email.dto.JiraRequest;
import com.krunal.api.email.dto.JiraResponse;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Logger;

@Service
public class JiraService {
    private static final Logger LOG = (Logger.getLogger(JiraService.class.getName()));
    JiraResponse jiraResponse = new JiraResponse();

    public JiraResponse createJira(JiraRequest jiraRequest) {
        if (!jiraRequest.getSummary().isEmpty() &&
                !jiraRequest.getIssueType().isEmpty() &&
                !jiraRequest.getDescription().isEmpty() &&
                !jiraRequest.getProjectKey().isEmpty() &&
                !jiraRequest.getUserId().isEmpty()) {


            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n    \"fields\": {\n        \"project\": {\n            \"key\": \"" + jiraRequest.getProjectKey() + "\"\n        },\n        \"summary\": \"" + jiraRequest.getSummary() + "\",\n        \"description\": \"" + jiraRequest.getDescription() + "\",\n        \"issuetype\": {\n            \"name\": \"" + jiraRequest.getIssueType() + "\"\n        },\n       \"customfield_10029\":\"" + jiraRequest.getUserId() + "\"\n    }\n}");
            Request request = new Request.Builder()
                    .url("https://hackademy2020.atlassian.net/rest/api/2/issue")
                    .method("POST", body)
                    .addHeader("Authorization", "Basic aGFja2FkZW15MjAyMEBnbWFpbC5jb206UHVnWWpOQ2gyNDdiZ3Eza213ZmYzMDg3")
                    .addHeader("Content-Type", "application/json")
                    .build();
            try {
                Response response = client.newCall(request).execute();

                JSONObject jsonObject = new JSONObject(response.body().string());
                jiraResponse.setStatus(true);
                jiraResponse.setMessage("Jira Created Successfully");
                jiraResponse.setId(jsonObject.get("key").toString());

            } catch (IOException | JSONException e) {
                LOG.info("Error occurred : " + e.getMessage());
                jiraResponse.setStatus(false);
                jiraResponse.setMessage("Error occurred while creating JIRA");
                jiraResponse.setId("null");
            }
        } else {
            jiraResponse.setStatus(false);
            jiraResponse.setMessage("String can't be null. Enter valid values");
            jiraResponse.setId(null);
        }
        return jiraResponse;
    }

    public Response searchJira(String projectKey, String userid) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://hackademy2020.atlassian.net/rest/api/2/search?jql=project%20%3D%20" + projectKey + "%20AND%20%22UserId%5BShort%20text%5D%22%20~%20%22" + userid + "%22%20order%20by%20created%20DESC&fields=key")
                .method("GET", null)
                .addHeader("Authorization", "Basic aGFja2FkZW15MjAyMEBnbWFpbC5jb206UHVnWWpOQ2gyNDdiZ3Eza213ZmYzMDg3")
                .addHeader("Cookie", "atlassian.xsrf.token=b50293ab-6bfb-4957-9840-7e22ad2b3dc3_baff36e03942c2d89c48fa3ad162301e85d6e152_lin")
                .build();
        Response response = client.newCall(request).execute();

        if (response != null)
            return response;

        return null;
    }

    public Response getStatus(String jiraId) throws IOException {
        JSONObject formDetailsJson = new JSONObject();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://hackademy2020.atlassian.net/rest/api/2/issue/" + jiraId + "?fields=status")
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Basic aGFja2FkZW15MjAyMEBnbWFpbC5jb206UHVnWWpOQ2gyNDdiZ3Eza213ZmYzMDg3")
                .addHeader("Cookie", "atlassian.xsrf.token=b50293ab-6bfb-4957-9840-7e22ad2b3dc3_4194c0064ebd2f7350b96fa63b29dc4c80744892_lin")
                .build();
        Response response = client.newCall(request).execute();

        if (response != null)
            return response;

        return null;
    }
}
