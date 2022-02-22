package com.tibor.controller;

import com.tibor.model.SecretData;
import com.tibor.model.SecretRequest;
import com.tibor.service.SecretService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/secret")
@Validated
@Slf4j
public class SecretController {



    private final SecretService secretService;

    @PostMapping(
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseBody
    public SecretData saveSecret(@Valid SecretRequest request) {
        try {
            return secretService.saveSecretData(request);
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            throw exception;
        }
    }

    @GetMapping(path = "/{hash}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseBody
    public ResponseEntity<?> getSecret(@PathVariable String hash) {
        return secretService.retrieveByHash(hash)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.of(Optional.empty()));
    }

}
