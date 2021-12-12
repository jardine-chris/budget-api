package com.keplux.budgetapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * {Description}
 *
 * @author Chris Jardine
 * @version 0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "budget")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Budget extends RepresentationModel<Budget> {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Account> accounts;

    @OneToMany(mappedBy = "budget", fetch = FetchType.LAZY, cascade =
            CascadeType.ALL)
    @JsonIgnore
    private List<Category> categories;
}
