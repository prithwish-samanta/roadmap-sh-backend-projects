package dev.prithwish.github_user_activity_cli.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Activity {
    private String id;
    private String type;
    private Actor actor;
    private Repo repo;
    private Payload payload;
    @JsonProperty("public")
    private Boolean isPublic;
    private String created_at;

    public Activity() {
    }

    public Activity(String id, String type, Actor actor, Repo repo, Payload payload, Boolean isPublic, String created_at) {
        this.id = id;
        this.type = type;
        this.actor = actor;
        this.repo = repo;
        this.payload = payload;
        this.isPublic = isPublic;
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
