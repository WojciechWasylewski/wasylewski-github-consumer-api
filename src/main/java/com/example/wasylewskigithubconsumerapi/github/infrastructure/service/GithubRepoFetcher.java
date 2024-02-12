package com.example.wasylewskigithubconsumerapi.github.infrastructure.service;

import com.example.wasylewskigithubconsumerapi.github.client.proxy.GitHubBranchesResponse;
import com.example.wasylewskigithubconsumerapi.github.client.proxy.GitHubResponse;
import com.example.wasylewskigithubconsumerapi.github.client.proxy.GithubProxy;
import com.example.wasylewskigithubconsumerapi.github.infrastructure.controller.dto.response.RepositoryInfoDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GithubRepoFetcher {
    private final GithubProxy githubProxy;

    public GithubRepoFetcher(GithubProxy githubProxy) {
        this.githubProxy = githubProxy;
    }

    public List<RepositoryInfoDto> getAllUserRepositories(String username, String acceptHeader) {
        List<GitHubResponse> repositories = githubProxy.getAllUserRepos(username, acceptHeader);
        List<RepositoryInfoDto> repositoryInfoList = new ArrayList<>();
        for (GitHubResponse repository : repositories) {
            List<GitHubBranchesResponse> branches = githubProxy.getRepoInfo(repository.owner().login(), repository.name());
            Map<String, String> branchInfo = branches.stream()
                    .collect(Collectors.toMap(
                            GitHubBranchesResponse::name,
                            branch -> branch.commit().sha()
                    ));
            RepositoryInfoDto repositoryInfo = RepositoryInfoDto.builder()
                    .repositoryName(repository.name())
                    .ownerLogin(repository.owner().login())
                    .branchCommits(branchInfo)
                    .build();
            repositoryInfoList.add(repositoryInfo);
        }

        return repositoryInfoList;
    }
}
