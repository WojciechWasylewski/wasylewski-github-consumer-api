package com.example.wasylewskigithubconsumerapi.github.infrastructure.service;

import com.example.wasylewskigithubconsumerapi.github.client.proxy.*;
import feign.Response;

import java.util.ArrayList;
import java.util.List;

public class InMemoryGithubProxy implements GithubProxy {
    private final List<GitHubResponse> userRepositories;

    public InMemoryGithubProxy(List<GitHubResponse> userRepositories) {
        this.userRepositories = userRepositories;
    }


    @Override
    public List<GitHubResponse> getAllUserRepos(String username, String acceptHeader) {
        List<GitHubResponse> repositories = new ArrayList<>();
        repositories.add(new GitHubResponse("repo1", new Owner("user1")));
        repositories.add(new GitHubResponse("repo2", new Owner("user1")));
        return repositories;
    }

    @Override
    public List<GitHubBranchesResponse> getRepoInfo(String owner, String repo) {
        List<GitHubBranchesResponse> branches = new ArrayList<>();
        branches.add(new GitHubBranchesResponse("main", new Commit("sha12345")));
        branches.add(new GitHubBranchesResponse("develop", new Commit("sha67891")));
        branches.add(new GitHubBranchesResponse("feature", new Commit("sha54321")));

        return branches;
    }

    @Override
    public Response checkUserExists(String username) {
        return Response.builder()
                .status(200)
                .build();
    }
}
