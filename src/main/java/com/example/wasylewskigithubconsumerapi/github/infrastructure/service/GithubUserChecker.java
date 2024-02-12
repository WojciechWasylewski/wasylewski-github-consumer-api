package com.example.wasylewskigithubconsumerapi.github.infrastructure.service;

import com.example.wasylewskigithubconsumerapi.github.client.proxy.GithubProxy;
import feign.Response;
import org.springframework.stereotype.Service;

@Service
public class GithubUserChecker {
    private final GithubProxy githubProxy;

    public GithubUserChecker(GithubProxy githubProxy) {
        this.githubProxy = githubProxy;
    }

    public boolean githubUserExists(String username) {
        Response response = githubProxy.checkUserExists(username);
        return response.status() == 200;
    }
}
