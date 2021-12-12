package com.keplux.budgetapi.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.math.BigDecimal;
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
@Table(name = "balance")
public class Balance extends RepresentationModel<Balance> {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private BigDecimal clearedBalance = new BigDecimal(0);
    private BigDecimal unclearedBalance = new BigDecimal(0);
    private BigDecimal workingBalance = new BigDecimal(0);

    @OneToOne(mappedBy = "balance")
    private Account account;
}
