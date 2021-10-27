package com.decathlon.alerts.controller;

import com.decathlon.alerts.entity.Developer;
import com.decathlon.alerts.entity.Team;
import com.decathlon.alerts.exceptions.DuplicateRecordException;
import com.decathlon.alerts.exceptions.ExternalAPIException;
import com.decathlon.alerts.exceptions.ResourceNotFoundException;
import com.decathlon.alerts.model.CreateTeamRequest;
import com.decathlon.alerts.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
@Validated
public class Controller {

    @Autowired
    private TeamService teamService;

    @PostMapping("/team")
    public ResponseEntity<Team> createTeam(@Validated @RequestBody  final CreateTeamRequest createTeamRequest) throws ResourceNotFoundException, DuplicateRecordException {
        log.info("Request received to create Team");
        Team createdTeam= teamService.createTeam(createTeamRequest);
        log.info("Team created");
        return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);
    }

    @PostMapping("/{teamId}/alert")
    public ResponseEntity<Developer> alertTeam(@Valid @PathVariable Long teamId) throws ResourceNotFoundException, DuplicateRecordException, ExternalAPIException {
        log.info("Request received to alert developer from Team");
        Developer alertedDeveloper= teamService.alertTeam(teamId);
        log.info("Developer from team alerted. Developer Id: {}",alertedDeveloper.getDeveloperId());
        return new ResponseEntity<>(alertedDeveloper, HttpStatus.OK);
    }
}
