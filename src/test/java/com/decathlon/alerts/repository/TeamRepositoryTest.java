/*
package com.decathlon.alerts.repository;

import com.decathlon.alerts.entity.Developer;
import com.decathlon.alerts.entity.Team;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;
    private Team team;
    private List<Developer> developers;

    @BeforeEach
    public void setUp() {
        team = new Team("Team1");
        Developer developer1=new Developer("developer1","1234567890");
        developer1.setTeam(team);
        Developer developer2=new Developer("developer2","1234567890");
        developer2.setTeam(team);
        developers.add(developer1);
        developers.add(developer2);
        team.setDevelopers(developers);

    }
    @AfterEach
    public void tearDown() {
        teamRepository.deleteAll();
        team = null;
        developers=null;
    }


    @Test
    public void givenTeamToAddShouldReturnAddedTeam(){
        teamRepository.save(team);
        Team fetchedTeam = teamRepository.findByTeamName(team.getTeamName());
        assertEquals("Team1", fetchedTeam.getTeamName());
    }

}
*/
