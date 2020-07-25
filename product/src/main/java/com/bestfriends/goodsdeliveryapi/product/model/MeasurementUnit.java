package com.bestfriends.goodsdeliveryapi.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "setup_measurement_units")
@Data
public class MeasurementUnit {
    @Id
    @Column(name = "measurement_unit_id")
    private Long id;

    @Column(name = "measurement_unit_name", nullable = false)
    private String name;
}
