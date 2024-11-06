package com.tpoProgra3.demo.model;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.INCOMING;

import java.util.HashSet;
import java.util.Set;

@Node
public class LeagueEntity {

    @Id
    private final String name;

    @Relationship(type = "HAS_CLUB", direction = INCOMING)
    private Set<ClubEntity> clubs = new HashSet<>();

    public LeagueEntity(String name, Set<ClubEntity> clubs) {
        this.name = name;
        this.clubs = clubs;
    }

    public String getName() {
        return name;
    }

    public Set<ClubEntity> getClubs() {
        return clubs;
    }

    public void setClubs(Set<ClubEntity> clubs) {
        this.clubs = clubs;
    }
}