package com.example.wasylewskigithubconsumerapi.github.infrastructure.controller.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class RepositoryInfoDTO {
    private String repositoryName;
    private String ownerLogin;
    private Map<String, String> branchCommits;

    public RepositoryInfoDTO(String repositoryName, String ownerLogin, Map<String, String> branchCommits) {
        this.repositoryName = repositoryName;
        this.ownerLogin = ownerLogin;
        this.branchCommits = branchCommits;
    }

}
