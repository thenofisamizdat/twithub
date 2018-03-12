package com.twithub.service;

import org.junit.Test;

import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

import java.util.List;

import static org.junit.Assert.*;


public class TwitterServiceTest {
    TwitterService twitterService = new TwitterService();

    @Test
    public void twitterTest(){
        List<Tweet> s = twitterService.tweets("al jorgenson");
        String x = s.toString();
    }

}
