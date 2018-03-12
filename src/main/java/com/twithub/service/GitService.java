package com.twithub.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.net.URLEncoder;

public class GitService {

    public JsonNode gitSearch(String query) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet getRequest = new HttpGet("https://api.github.com/search/repositories?q="+ URLEncoder.encode(query)+"&sort=stars&order=desc");
        HttpResponse response = httpclient.execute(getRequest);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode projects = objectMapper.readTree(EntityUtils.toString(response.getEntity()));

        return projects.get("items");
    }
}
