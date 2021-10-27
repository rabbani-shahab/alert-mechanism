package com.decathlon.alerts.model;

import com.decathlon.alerts.entity.Developer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTeamRequest {
    @NotNull(message="Team Name cannot be null")
    private String teamName;
    @NotEmpty(message = "Developer list cannot be empty")
    private List<Developer> developers;
}
