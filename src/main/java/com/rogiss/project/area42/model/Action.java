package com.rogiss.project.area42.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "action")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private List<Reaction> compatibleReaction = new ArrayList<>();

    public Action() {
    }

    public Action(String description) {
        this.description = description;
    }

    public Action(String description, List<Reaction> compatibleReaction) {
        this.description = description;
        this.compatibleReaction = compatibleReaction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Reaction> getCompatibleReaction() {
        return compatibleReaction;
    }

    public void setCompatibleReaction(List<Reaction> compatibleReaction) {
        this.compatibleReaction = compatibleReaction;
    }
}
