package com.example.wasylewskigithubconsumerapi.github.infrastructure.service;

import com.example.wasylewskigithubconsumerapi.github.client.proxy.GitHubBranchesResponse;
import com.example.wasylewskigithubconsumerapi.github.client.proxy.GitHubResponse;
import com.example.wasylewskigithubconsumerapi.github.client.proxy.GithubProxy;
import com.example.wasylewskigithubconsumerapi.github.infrastructure.controller.dto.response.RepositoryInfoDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GithubRepoFetcher {
    private final GithubProxy githubProxy;


    public GithubRepoFetcher(GithubProxy githubProxy) {
        this.githubProxy = githubProxy;
    }

    public List<RepositoryInfoDTO> getAllUserRepositories(String username, String acceptHeader) {
        List<GitHubResponse> repositories = githubProxy.getAllUserRepos(username, acceptHeader);
        List<RepositoryInfoDTO> repositoryInfoList = new ArrayList<>();

        for (GitHubResponse repository : repositories) {
            List<GitHubBranchesResponse> branches = githubProxy.getRepoInfo(repository.owner().login(), repository.name());

            Map<String, String> branchInfo = new HashMap<>();

            for (GitHubBranchesResponse branch : branches) {
                branchInfo.put(branch.name(), branch.commit().sha());
            }
            RepositoryInfoDTO repositoryInfo = new RepositoryInfoDTO(repository.name(), repository.owner().login(), branchInfo);
            repositoryInfoList.add(repositoryInfo);
        }

        return repositoryInfoList;
    }
}
