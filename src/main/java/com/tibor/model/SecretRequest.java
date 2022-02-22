package com.tibor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class SecretRequest {

    @NotNull
    private String secret;

    @NotNull
    private Integer expireAfterViews;

    @NotNull
    private Integer expireAfter;

}
