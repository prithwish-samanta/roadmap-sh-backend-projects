package dev.prithwish.github_user_activity_cli.domain;

public class Commit {
    private String sha;
    private String message;
    private boolean distinct;
    private String url;

    public Commit() {
    }

    public Commit(String sha, String message, boolean distinct, String url) {
        this.sha = sha;
        this.message = message;
        this.distinct = distinct;
        this.url = url;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
