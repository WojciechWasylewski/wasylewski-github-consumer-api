package com.example.wasylewskigithubconsumerapi.github.infrastructure.controller;

import com.example.wasylewskigithubconsumerapi.github.infrastructure.controller.dto.response.GetAllUserRepoInfoResponseDto;
import com.example.wasylewskigithubconsumerapi.github.infrastructure.controller.dto.response.RepositoryInfoDto;
import com.example.wasylewskigithubconsumerapi.github.infrastructure.controller.error.GithubUserNotFoundException;
import com.example.wasylewskigithubconsumerapi.github.infrastructure.controller.error.InvalidFormatResponseError;
import com.example.wasylewskigithubconsumerapi.github.infrastructure.service.GithubRepoFetcher;
import com.example.wasylewskigithubconsumerapi.github.infrastructure.service.GithubUserChecker;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@AllArgsConstructor
@RequestMapping("/github/agent")
public class GithubRestController {
    private final GithubRepoFetcher githubRepoFetcher;
    private final GithubUserChecker githubUserChecker;

    @GetMapping("/{username}")
    public ResponseEntity<GetAllUserRepoInfoResponseDto> getAllRepositories(@PathVariable String username, @RequestHeader("Accept") String acceptHeader) {
        if (acceptHeader.contains(MediaType.APPLICATION_XML_VALUE)) {
            throw new InvalidFormatResponseError("Requested media type 'application/xml' is not supported");
        }

        if (!githubUserChecker.githubUserExists(username)) {
            throw new GithubUserNotFoundException("User with username: " + username + " not found");
        }

        List<RepositoryInfoDto> repositoryInfoList = githubRepoFetcher.getAllUserRepositories(username, acceptHeader);
        GetAllUserRepoInfoResponseDto responseDto = new GetAllUserRepoInfoResponseDto(repositoryInfoList);

        return ResponseEntity.ok(responseDto);
    }
}
