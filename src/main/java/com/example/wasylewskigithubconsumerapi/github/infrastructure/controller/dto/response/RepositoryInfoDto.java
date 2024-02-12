package com.example.wasylewskigithubconsumerapi.github.infrastructure.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Builder
@Getter
@Setter
public class RepositoryInfoDto {
    private String repositoryName;
    private String ownerLogin;
    private Map<String, String> branchCommits;

    public RepositoryInfoDto(String repositoryName, String ownerLogin, Map<String, String> branchCommits) {
        this.repositoryName = repositoryName;
        this.ownerLogin = ownerLogin;
        this.branchCommits = branchCommits;
    }

}
