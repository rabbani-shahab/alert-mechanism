package com.decathlon.alerts.service;

import com.decathlon.alerts.entity.Developer;
import com.decathlon.alerts.entity.Team;
import com.decathlon.alerts.exceptions.DuplicateRecordException;
import com.decathlon.alerts.exceptions.ExternalAPIException;
import com.decathlon.alerts.exceptions.ResourceNotFoundException;
import com.decathlon.alerts.model.AlertDeveloperRequest;
import com.decathlon.alerts.model.CreateTeamRequest;
import com.decathlon.alerts.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@org.springframework.stereotype.Service
@Slf4j
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Value("${alert.api.url}")
    private String alertapiURL;

    public Team createTeam(CreateTeamRequest createTeamRequest) throws DuplicateRecordException {

        ModelMapper modelMapper=new ModelMapper();
        Team team = modelMapper.map(createTeamRequest, Team.class);

        Team possibleDuplicateTeam= teamRepository.findByTeamName(team.getTeamName());

        if(possibleDuplicateTeam!=null){
            throw new DuplicateRecordException("Team with same teamName already exists.");
        }
        team.getDevelopers().forEach(developer -> {
            developer.setTeam(team);
        });
        try{
            return teamRepository.save(team);
        }catch(DataIntegrityViolationException e){
            throw new DuplicateRecordException("Team with this name/uniqueID already exists");
        }
    }

    public Developer alertTeam(Long teamId) throws ResourceNotFoundException, ExternalAPIException {
        Team team= teamRepository.findById(teamId).orElseThrow(()-> new ResourceNotFoundException("No Team found for teamId- " + teamId));
        List<Developer> developers= team.getDevelopers();
        Random rand = new Random();
        Developer developerToBeAlerted=developers.get(rand.nextInt(developers.size()));
        ResponseEntity<String> response=alertDeveloper(developerToBeAlerted);
        if(response.getStatusCode().is2xxSuccessful()){
            return developerToBeAlerted;
        }else{
            throw new ExternalAPIException("Response from external Alert API not successful");
        }


    }

    public ResponseEntity<String> alertDeveloper(Developer developer){
        RestTemplate restTemplate = new RestTemplate();
        AlertDeveloperRequest alertDeveloperRequest=new AlertDeveloperRequest(developer.getPhoneNumber());

        ResponseEntity<String> response=restTemplate.postForEntity(alertapiURL,alertDeveloperRequest,String.class);
        return response;
    }

}
