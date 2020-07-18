package com.bestfriends.goodsdeliveryapi.product.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "setup_application_messages")
@Data
public class ApplicationMessage {
    @Id
    @Column(name = "application_message_id")
    private Long id;

    @Column(name = "application_message", nullable = false, unique = true)
    private String message;
}
