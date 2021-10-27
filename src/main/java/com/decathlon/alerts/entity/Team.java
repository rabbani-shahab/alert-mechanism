package com.decathlon.alerts.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private long teamId;

    @Column(name = "team_name",unique = true)
    private String teamName;

    @JsonIgnore
    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL)
    private List<Developer> developers;

    @Override
    public String toString() {
        return "";
    }

    public Team(String teamName) {
        this.teamName = teamName;
    }
}
