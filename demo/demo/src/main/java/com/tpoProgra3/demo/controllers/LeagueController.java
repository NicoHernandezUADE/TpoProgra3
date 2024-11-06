package com.tpoProgra3.demo.controllers;

import com.tpoProgra3.demo.model.LeagueEntity;
import com.tpoProgra3.demo.model.PersonEntity;
import com.tpoProgra3.demo.model.ClubEntity;
import com.tpoProgra3.demo.repository.LeagueRepository;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/leagues")
public class LeagueController {
    private final LeagueRepository leagueRepository;

    public LeagueController(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    @PutMapping
    Mono<LeagueEntity> createOrUpdateLeague(@RequestBody LeagueEntity newLeague) {
        return leagueRepository.save(newLeague);
    }

    @GetMapping(value = { "", "/" }, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<LeagueEntity> getLeagues() {
        return leagueRepository.findAll();
    }

    @GetMapping("/backtracking")
    Mono<String> backtrackingTraversal() {
        return leagueRepository.findAll()
                .collectList()
                .map(leagues -> {
                    Set<LeagueEntity> visited = new HashSet<>();
                    StringBuilder result = new StringBuilder();
                    for (LeagueEntity league : leagues) {
                        if (!visited.contains(league)) {
                            backtrack(league, visited, result);
                        }
                    }
                    return result.toString();
                });
    }

    private void backtrack(LeagueEntity league, Set<LeagueEntity> visited, StringBuilder result) {
        visited.add(league);
        result.append("League: ").append(league.getName()).append("\n");
        for (ClubEntity club : league.getClubs()) {
            result.append("  Club: ").append(club.getName()).append("\n");
            for (PersonEntity player : club.getPlayers()) {
                result.append("    Player: ").append(player.getName()).append("\n");
            }
            for (PersonEntity manager : club.getCoachingStaff()) {
                result.append("    Manager: ").append(manager.getName()).append("\n");
            }
        }
    }

    @GetMapping("/DFS")
    Mono<String> dfsTraversal() {
        return leagueRepository.findAll()
                .collectList()
                .map(leagues -> {
                    Set<ClubEntity> visited = new HashSet<>();
                    StringBuilder result = new StringBuilder();
                    for (LeagueEntity league : leagues) {
                        for (ClubEntity club : league.getClubs()) {
                            if (!visited.contains(club)) {
                                dfs(club, visited, result);
                            }
                        }
                    }
                    return result.toString();
                });
    }

    private void dfs(ClubEntity club, Set<ClubEntity> visited, StringBuilder result) {
        Stack<ClubEntity> stack = new Stack<>();
        stack.push(club);

        while (!stack.isEmpty()) {
            ClubEntity currentClub = stack.pop();
            if (!visited.contains(currentClub)) {
                visited.add(currentClub);
                result.append("  Club: ").append(currentClub.getName()).append("\n");
                for (PersonEntity player : currentClub.getPlayers()) {
                    result.append("    Player: ").append(player.getName()).append("\n");
                }
                for (PersonEntity manager : currentClub.getCoachingStaff()) {
                    result.append("    Manager: ").append(manager.getName()).append("\n");
                }
            }
        }
    }

    @GetMapping("/BFS")
    Mono<String> bfsTraversal() {
        return leagueRepository.findAll()
                .collectList()
                .map(leagues -> {
                    Set<ClubEntity> visited = new HashSet<>();
                    StringBuilder result = new StringBuilder();
                    Queue<ClubEntity> queue = new LinkedList<>();
                    for (LeagueEntity league : leagues) {
                        for (ClubEntity club : league.getClubs()) {
                            if (!visited.contains(club)) {
                                queue.add(club);
                            }
                        }
                    }
                    while (!queue.isEmpty()) {
                        ClubEntity currentClub = queue.poll();
                        if (!visited.contains(currentClub)) {
                            visited.add(currentClub);
                            result.append("  Club: ").append(currentClub.getName()).append("\n");
                        }
                    }

                    for (LeagueEntity league : leagues) {
                        for (ClubEntity club : league.getClubs()) {
                            if (visited.contains(club)) {
                                for (PersonEntity player : club.getPlayers()) {
                                    result.append("    Player: ").append(player.getName()).append("\n");
                                }
                                for (PersonEntity manager : club.getCoachingStaff()) {
                                    result.append("    Manager: ").append(manager.getName()).append("\n");
                                }
                            }
                        }
                    }

                    return result.toString();
                });
    }
}