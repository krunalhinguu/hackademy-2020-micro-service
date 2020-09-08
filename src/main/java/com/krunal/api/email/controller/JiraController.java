package com.krunal.api.email.controller;

import com.krunal.api.email.dto.JiraRequest;
import com.krunal.api.email.dto.JiraResponse;
import com.krunal.api.email.service.JiraService;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "v1")
public class JiraController {

    @Autowired
    private JiraService jiraService;

    @PostMapping("/createJira")
    public JiraResponse createJira(@RequestBody JiraRequest jiraRequest) {
        return jiraService.createJira(jiraRequest);
    }

    @GetMapping("/getUserJira")
    public String getJiraByUserId(@RequestBody JiraRequest jiraRequest) throws JSONException {
        JSONObject responseJSON = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        List<String> jiraKeys = new ArrayList<>();

        Response response = null;

        if (!jiraRequest.getProjectKey().isEmpty() && !jiraRequest.getUserId().isEmpty()) {
            try {
                response = jiraService.searchJira(jiraRequest.getProjectKey(), jiraRequest.getUserId());
                if (response != null) {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    JSONArray issues = jsonObject.getJSONArray("issues");

                    for (int i = 0; i < issues.length(); i++) {
                        JSONObject obj = issues.getJSONObject(i);
                        String key = obj.getString("key");
                        jiraKeys.add(key);
                    }

                    for (String key : jiraKeys) {
                        JSONObject formDetailsJson = new JSONObject();
                        formDetailsJson.put("Jira Id", key);
                        jsonArray.put(formDetailsJson);
                    }
                    responseJSON.put("success", true);
                    responseJSON.put("Jiras", jsonArray);//Here you can see the data in json format
                }
            } catch (IOException e) {
                responseJSON.put("message", "please enter valid parameters");
                responseJSON.put("success", false);
            } catch (JSONException e) {
                responseJSON.put("message", "please enter valid parameters");
                responseJSON.put("success", false);
            }
        } else {
            responseJSON.put("message", "values can't be  null");
            responseJSON.put("success", false);
        }
        return responseJSON.toString();
    }

    @GetMapping("/getJiraStatus")
    public String getJiraStatus(@RequestParam String jira) throws JSONException {
        JSONObject responseJSON = new JSONObject();
        Response response = null;

        if (!jira.isEmpty()) {
            try {
                response = jiraService.getStatus(jira);

                if (response != null) {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    JSONObject issues = jsonObject.getJSONObject("fields");
                    JSONObject status = issues.getJSONObject("status");

                    responseJSON.put("success", true);
                    responseJSON.put("status", status.get("name"));

                }
            } catch (IOException e) {
                responseJSON.put("message", e.getMessage());
                responseJSON.put("success", false);
            } catch (JSONException e) {
                responseJSON.put("message", "invalid jira key");
                responseJSON.put("success", false);
            }
        } else {
            responseJSON.put("message", "please enter valid parameters");
            responseJSON.put("success", false);
        }
        return responseJSON.toString();
    }
}

