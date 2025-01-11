package dev.prithwish.github_user_activity_cli.commands;

import dev.prithwish.github_user_activity_cli.client.GitHubClient;
import dev.prithwish.github_user_activity_cli.domain.Activity;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.ArrayList;
import java.util.List;

import static ch.qos.logback.core.util.StringUtil.capitalizeFirstLetter;

@ShellComponent
public class Command {
    private final GitHubClient gitHubClient;

    public Command(GitHubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
    }

    @ShellMethod(key = "activity", value = "Fetches the user’s recent activity from GitHub")
    public void activity(@ShellOption String username) {
        try {
            List<Activity> activities = gitHubClient.getUserActivities(username);
            List<String> activityNames = formatActivityMessage(activities);
            for (String activityName : activityNames) {
                System.out.println("- " + activityName);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private List<String> formatActivityMessage(List<Activity> activities) {
        List<String> userActivity = new ArrayList<>();
        for (Activity activity : activities) {
            String type = activity.getType();
            String repoName = activity.getRepo().getName();
            String msg;
            switch (type) {
                case "PushEvent":
                    int commitCount = activity.getPayload().getCommits().size();
                    msg = "Pushed " + commitCount + " commit(s) to " + repoName;
                    break;

                case "PullRequestEvent":
                    String prAction = activity.getPayload().getAction();
                    msg = capitalizeFirstLetter(prAction) + " a pull request in " + repoName;
                    break;

                case "IssuesEvent":
                    String issueAction = activity.getPayload().getAction();
                    msg = capitalizeFirstLetter(issueAction) + " an issue in " + repoName;
                    break;

                case "IssueCommentEvent":
                    msg = "Commented on an issue in " + repoName;
                    break;

                case "ForkEvent":
                    msg = "Forked the repository " + repoName;
                    break;

                case "WatchEvent":
                    msg = "Starred " + repoName;
                    break;

                case "ReleaseEvent":
                    String releaseAction = activity.getPayload().getAction();
                    msg = capitalizeFirstLetter(releaseAction) + " a release in " + repoName;
                    break;

                case "CreateEvent":
                    msg = "Created a new " + activity.getPayload().getRef_type() + " in " + repoName;
                    break;

                case "DeleteEvent":
                    msg = "Deleted a " + activity.getPayload().getRef_type() + " in " + repoName;
                    break;

                case "PullRequestReviewEvent":
                    msg = "Reviewed a pull request in " + repoName;
                    break;

                case "PullRequestReviewCommentEvent":
                    msg = "Commented on a pull request in " + repoName;
                    break;

                case "GollumEvent":
                    msg = "Updated the wiki in " + repoName;
                    break;

                case "PublicEvent":
                    msg = "Made the repository " + repoName + " public";
                    break;

                case "MemberEvent":
                    String memberAction = activity.getPayload().getAction();
                    String member = activity.getPayload().getMember().getLogin();
                    msg = capitalizeFirstLetter(memberAction) + " " + member + " as a collaborator in " + repoName;
                    break;

                case "CommitCommentEvent":
                    msg = "Commented on a commit in " + repoName;
                    break;

                case "DeploymentEvent":
                    msg = "Deployed changes in " + repoName;
                    break;

                case "DeploymentStatusEvent":
                    msg = "Updated deployment status in " + repoName;
                    break;

                case "StatusEvent":
                    String state = activity.getPayload().getState();
                    msg = "Updated status to '" + state + "' for a commit in " + repoName;
                    break;

                default:
                    msg = null;
            }

            if (msg != null) {
                userActivity.add(msg);
            }
        }
        return userActivity;
    }
}
