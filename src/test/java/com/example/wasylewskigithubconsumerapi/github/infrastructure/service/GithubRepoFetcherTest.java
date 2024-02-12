package com.example.wasylewskigithubconsumerapi.github.infrastructure.service;

import com.example.wasylewskigithubconsumerapi.github.client.proxy.GitHubBranchesResponse;
import com.example.wasylewskigithubconsumerapi.github.client.proxy.GitHubResponse;
import com.example.wasylewskigithubconsumerapi.github.client.proxy.Owner;
import com.example.wasylewskigithubconsumerapi.github.infrastructure.controller.dto.response.RepositoryInfoDto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GithubRepoFetcherTest {

    @Test
    void test_getAllUserRepositories_should_return_correct_list_of_users_repos() {
        // given
        String username = "testUser";
        String acceptHeader = "application/json";
        List<GitHubResponse> repositories = Arrays.asList(
                new GitHubResponse("repo1", new Owner("user1")),
                new GitHubResponse("repo2", new Owner("user1"))
        );
        InMemoryGithubProxy inMemoryGithubProxy = new InMemoryGithubProxy(repositories);
        // when
        GithubRepoFetcher repoFetcher = new GithubRepoFetcher(inMemoryGithubProxy);
        List<RepositoryInfoDto> result = repoFetcher.getAllUserRepositories(username, acceptHeader);
        // then
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        assertEquals("repo1", result.get(0).getRepositoryName());
        assertEquals("user1", result.get(0).getOwnerLogin());
        assertEquals("repo2", result.get(1).getRepositoryName());
        assertEquals("user1", result.get(1).getOwnerLogin());
    }

    @Test
    void test_getRepoInfo_should_return_correct_branches_for_given_repo() {
        // given
        String owner = "testOwner";
        String repo = "testRepo";
        InMemoryGithubProxy inMemoryGithubProxy = new InMemoryGithubProxy(null);
        // when
        List<GitHubBranchesResponse> branches = inMemoryGithubProxy.getRepoInfo(owner, repo);
        // then
        assertFalse(branches.isEmpty());
        assertEquals(3, branches.size());
        assertEquals("main", branches.get(0).name());
        assertEquals("sha12345", branches.get(0).commit().sha());
        assertEquals("develop", branches.get(1).name());
        assertEquals("sha67891", branches.get(1).commit().sha());
        assertEquals("feature", branches.get(2).name());
        assertEquals("sha54321", branches.get(2).commit().sha());
    }

}