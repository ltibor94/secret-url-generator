package com.tibor.service;

import com.tibor.model.SecretData;
import com.tibor.model.SecretRequest;
import com.tibor.repository.SecretRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class SecretService {

    @Autowired
    private SecretRepository secretRepository;

    public SecretData saveSecretData(SecretRequest secretRequest) {
        var secretData = new SecretData();
        var createdAt = DateTime.now();

        secretData.setCreatedAt(createdAt.toDate());
        secretData.setExpiresAt(createdAt.plusMinutes(secretRequest.getExpireAfter()).toDate());
        secretData.setHash(DigestUtils.sha256Hex(secretRequest.getSecret()));
        secretData.setSecretText(secretRequest.getSecret());
        secretData.setRemainingReviews(secretRequest.getExpireAfterViews());

        return secretRepository.save(secretData);
    }

    public Optional<SecretData> retrieveByHash(String hash) {
        return secretRepository.findByHashAndAvailable(hash, true)
                .map(secretData1 -> {
                    secretData1.setRemainingReviews(secretData1.getRemainingReviews() - 1);
                    if (secretData1.isAvailable()) {
                        if (secretData1.getRemainingReviews() <= 0 || secretData1.getExpiresAt().before(new Date())) {
                            secretData1.setAvailable(false);
                        }
                        return secretRepository.save(secretData1);
                    } else {
                        return null;
                    }
                });
    }
}
