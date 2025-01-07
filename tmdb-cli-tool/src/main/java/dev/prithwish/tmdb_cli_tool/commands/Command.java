package dev.prithwish.tmdb_cli_tool.commands;

import dev.prithwish.tmdb_cli_tool.client.TMDBClient;
import dev.prithwish.tmdb_cli_tool.domain.Movie;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.*;

import java.util.LinkedHashMap;
import java.util.List;

@ShellComponent
public class Command {
    private final TMDBClient tmdbClient;

    public Command(TMDBClient tmdbClient) {
        this.tmdbClient = tmdbClient;
    }

    @ShellMethod(key = "tmdb-app", value = "Show the popular, top-rated, upcoming and now playing movies from the TMDB API. Valid options are ('playing','popular','top' & 'upcoming')")
    public Object help(@ShellOption String type) {
        try {
            if (type.equalsIgnoreCase("playing")) {
                List<Movie> movies = tmdbClient.nowPlaying();
                return buildTable(movies);
            } else if (type.equalsIgnoreCase("popular")) {
                List<Movie> movies = tmdbClient.popular();
                return buildTable(movies);
            } else if (type.equalsIgnoreCase("top")) {
                List<Movie> movies = tmdbClient.topRated();
                return buildTable(movies);
            } else if (type.equalsIgnoreCase("upcoming")) {
                List<Movie> movies = tmdbClient.upcoming();
                return buildTable(movies);
            } else {
                return "Invalid type value. Allowed values: 'playing', 'popular', 'top' & 'upcoming'";
            }
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }

    private Table buildTable(List<Movie> movies) {
        TableModel tableModel = new BeanListTableModel<>(
                movies,
                new LinkedHashMap<>() {{
                    put("original_title", "Title");
                    put("original_language", "Language");
                    put("vote_average", "Rating");
                    put("release_date", "Released Date");
                }}
        );
        TableBuilder tableBuilder = new TableBuilder(tableModel);
        tableBuilder.addFullBorder(BorderStyle.oldschool);
        tableBuilder.addHeaderBorder(BorderStyle.oldschool);

        return tableBuilder.build();
    }
}
