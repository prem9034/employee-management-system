package com.springcrud1.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "country_")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private Set<State> states;

    public Set<State> getStates() {
        return states;
    }

    public void setStates(Set<State> states) {
        this.states = states;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
