package com.kamesha.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "country")
@Setter
@Getter
public class Country {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "code", length = 2)
    private String code;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "country")
    @JsonIgnore
    private Set<State> states = new LinkedHashSet<>();


}