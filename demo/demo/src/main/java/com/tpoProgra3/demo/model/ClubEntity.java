package com.tpoProgra3.demo.model;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.INCOMING;

@Node("Club")
public class ClubEntity {
    @Id
    private final String name;
    @Property("tagline")
    private final int fundationYear;

    @Relationship(type = "PLAYS_IN", direction = INCOMING)
    private Set<PersonEntity> players = new HashSet<>();

    @Relationship(type = "DIRECTS", direction = INCOMING)
    private Set<PersonEntity> coachingStaff = new HashSet<>();
    
    public ClubEntity(String name, int fundationYear) {
        this.name = name;
        this.fundationYear = fundationYear;
    }

    public String getName() {
        return this.name;
    }

    public int getFundationYear() {
        return this.fundationYear;
    }

    public Set<PersonEntity> getPlayers() {
        return this.players;
    }

    public void setPlayers(Set<PersonEntity> players) {
        this.players = players;
    }

    public Set<PersonEntity> getCoachingStaff() {
        return this.coachingStaff;
    }

    public void setDirectors(Set<PersonEntity> coachingStaff) {
        this.coachingStaff = coachingStaff;
    }
}