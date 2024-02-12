package com.example.wasylewskigithubconsumerapi.github.infrastructure.controller.dto.response;

import java.util.List;

public class GetAllUserRepoInfoResponseDto {
    private List<RepositoryInfoDto> repositories;

    public GetAllUserRepoInfoResponseDto(List<RepositoryInfoDto> repositories) {
        this.repositories = repositories;
    }

    public List<RepositoryInfoDto> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<RepositoryInfoDto> repositories) {
        this.repositories = repositories;
    }
}
