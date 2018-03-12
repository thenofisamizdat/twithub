package com.twithub.service;

import io.github.jhipster.config.JHipsterProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "spring.social.twitter")
public class TwitterService {

    public TwitterService(){}

//    @Value("${spring.social.twitter.client-key}")
    private String consumerKey;

//    @Value("${spring.social.twitter.client-secret}")
    private String consumerSecret;

    public List<Tweet> tweets (String query){
        consumerKey = "cmtfVAG1cnfTIm6FS5Cg56lH9"; // The application's consumer key
        consumerSecret = "ipbcSxVsZDblFuniQBYzLz9gTgfA18CctrEyquIwndlWCs2snG"; // The application's consumer secret
        Twitter twitter = new TwitterTemplate(consumerKey, consumerSecret);
        return twitter.searchOperations().search(query).getTweets();
    }

}
