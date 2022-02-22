package com.tibor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "secret")
public class SecretData {

    @Id
    @GeneratedValue
    @JsonIgnore
    private int id;

    @NotNull
    private String hash;

    @NotNull
    private String secretText;

    @NotNull
    private Date createdAt;

    @NotNull
    private Date expiresAt;

    @NotNull
    private int remainingReviews;

    @JsonIgnore
    private boolean available = true;

}
