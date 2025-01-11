package dev.prithwish.github_user_activity_cli.domain;

import java.util.List;

public class Payload {
    private String ref;
    private String ref_type;
    private String master_branch;
    private String description;
    private String pusher_type;
    private List<Commit> commits;
    private String action;
    private Member member;
    private String state;

    public Payload() {
    }

    public Payload(String ref, String ref_type, String master_branch, String description, String pusher_type, List<Commit> commits, String action, Member member, String state) {
        this.ref = ref;
        this.ref_type = ref_type;
        this.master_branch = master_branch;
        this.description = description;
        this.pusher_type = pusher_type;
        this.commits = commits;
        this.action = action;
        this.member = member;
        this.state = state;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getRef_type() {
        return ref_type;
    }

    public void setRef_type(String ref_type) {
        this.ref_type = ref_type;
    }

    public String getMaster_branch() {
        return master_branch;
    }

    public void setMaster_branch(String master_branch) {
        this.master_branch = master_branch;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPusher_type() {
        return pusher_type;
    }

    public void setPusher_type(String pusher_type) {
        this.pusher_type = pusher_type;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
