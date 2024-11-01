package com.tpoProgra3.demo.repository;

import com.tpoProgra3.demo.model.ClubEntity;

import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import reactor.core.publisher.Mono;

public interface ClubRepository extends ReactiveNeo4jRepository<ClubEntity, String> {
    Mono<ClubEntity> findOneByName(String name);
}
