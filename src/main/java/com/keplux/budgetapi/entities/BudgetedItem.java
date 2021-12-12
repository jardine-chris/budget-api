package com.keplux.budgetapi.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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
@Table(name = "budgeted_item")
public class BudgetedItem {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String name;

    private BigDecimal budgetedAmount;
    private BigDecimal availableAmount;
    private BigDecimal spentAmount;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
