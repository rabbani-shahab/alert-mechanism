/*
package com.decathlon.alerts.service;

import com.decathlon.alerts.entity.Developer;
import com.decathlon.alerts.entity.Team;
import com.decathlon.alerts.exceptions.DuplicateRecordException;
import com.decathlon.alerts.model.CreateTeamRequest;
import com.decathlon.alerts.repository.TeamRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @Autowired
    Mockito mockito;

    @Autowired
    ObjectMapper mapper;


    @Autowired
    @InjectMocks
    private TeamService teamService;

    private Team team1;
    private Team team2;
    List<Team> teamList;

    @BeforeEach
    public void setUp() {
        teamList = new ArrayList<>();
        Developer developer1=new Developer("Developer1","1234567890");
        Developer developer2=new Developer("Developer2","1234567890");

        team1 = new Team(1, "bread", List.of(new Developer[]{developer1, developer2}));
        team2 = new Team(2, "jam",List.of(new Developer[]{developer1, developer2}));
        teamList.add(team1);
        teamList.add(team2);
    }

    @AfterEach
    public void tearDown() {
        team1 = team2 = null;
        teamList = null;
    }

    @Test
    void givenTeamToAddShouldReturnAddedTeam() throws DuplicateRecordException {
        //stubbing
        mockito.when(teamRepository.save(mockito.any())).thenReturn(team1);
        CreateTeamRequest createTeamRequest=mapper.convertValue(team1,CreateTeamRequest.class);
        teamService.createTeam(createTeamRequest);
        mockito.verify(teamRepository, mockito.times(1)).save(mockito.any());

    }
}
*/
