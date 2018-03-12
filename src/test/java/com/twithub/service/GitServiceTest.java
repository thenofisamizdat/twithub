package com.twithub.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.junit.Assert.*;

public class GitServiceTest {

    @Autowired
    GitService gitService = new GitService();

    @Test
    public void testGit() throws IOException {
        gitService.gitSearch("halt and catch fire");
    }

}
