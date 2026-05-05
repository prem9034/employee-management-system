package com.springcrud1.entity;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "district")
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    private Set<City> cities;

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }


}
