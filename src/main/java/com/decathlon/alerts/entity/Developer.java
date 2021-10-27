package com.decathlon.alerts.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "developer_id")
    private long developerId;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    public Developer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }



    @ManyToOne
    @JoinColumn(name="team_id", nullable=false, referencedColumnName="team_id")
    private Team team;
}
