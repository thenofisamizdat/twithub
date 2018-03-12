package com.twithub.web.rest.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.twithub.service.GitService;
import com.twithub.service.TwitterService;
import org.springframework.social.twitter.api.Tweet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TwitHubWrapper {

    GitService gitService;
    TwitterService twitterService;

    public TwitHubWrapper(){
        gitService = new GitService();
        twitterService = new TwitterService();
    }

    public List constructResult(String query) throws IOException {
        List<TwitHubResult> result = new ArrayList<>();
        JsonNode gitResult = gitService.gitSearch(query);

        int limit = 10;
        int i = 0;
        for (JsonNode project : gitResult) {
            if (i >= limit) break;
            TwitHubResult wrapper = new TwitHubResult();
            List<Tweet> tweets = twitterService.tweets(project.get("name").asText());
            wrapper.setProject(project);
            wrapper.setTweets(tweets);
            wrapper.setId(i);
            result.add(wrapper);
            i++;
        }

        return result;
    }
}
