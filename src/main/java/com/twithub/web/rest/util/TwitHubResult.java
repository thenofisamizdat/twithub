package com.twithub.web.rest.util;


import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.social.twitter.api.Tweet;

import java.util.List;

public class TwitHubResult {

    public TwitHubResult(){}

    private List<Tweet> tweets;
    private JsonNode project;
    private int id;

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public JsonNode getProject() {
        return project;
    }

    public void setProject(JsonNode project) {
        this.project = project;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
