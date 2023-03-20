package io.getarrays.userservice.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jdk.nashorn.internal.ir.annotations.Reference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Double quantity;
}
