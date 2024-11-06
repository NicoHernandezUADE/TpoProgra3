package com.tpoProgra3.demo.repository;

import com.tpoProgra3.demo.model.LeagueEntity;

import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import reactor.core.publisher.Mono;

public interface LeagueRepository extends ReactiveNeo4jRepository<LeagueEntity, String> {
    Mono<LeagueEntity> findOneByName(String name);
}