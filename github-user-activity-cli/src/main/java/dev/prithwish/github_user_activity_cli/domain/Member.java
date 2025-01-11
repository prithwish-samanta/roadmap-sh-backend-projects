package dev.prithwish.github_user_activity_cli.domain;

public class Member {
    private String login;

    public Member() {
    }

    public Member(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
