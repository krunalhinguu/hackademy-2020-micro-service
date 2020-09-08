package com.krunal.api.email.dto;

public class JiraRequest {
    private String summary;
    private String projectKey;
    private String issueType;
    private String description;
    private String userId;

    protected JiraRequest() {
    }

    public JiraRequest(String projectKey, String userId) {
        this.projectKey = projectKey;
        this.userId = userId;
    }

    public JiraRequest(String summary, String projectKey, String issueType, String description, String userId) {
        this.summary = summary;
        this.projectKey = projectKey;
        this.issueType = issueType;
        this.description = description;
        this.userId = userId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "JiraRequest{" +
                "summary='" + summary + '\'' +
                ", projectKey='" + projectKey + '\'' +
                ", issueType='" + issueType + '\'' +
                ", description='" + description + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
