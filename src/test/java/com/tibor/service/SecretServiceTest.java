package com.tibor.service;

import com.tibor.model.SecretData;
import com.tibor.model.SecretRequest;
import com.tibor.repository.SecretRepository;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SecretServiceTest {

    @Mock
    private SecretRepository secretRepository;

    @InjectMocks
    private SecretService secretService;

    @Test
    void shouldSaveSecretData() {
        var secretData = createTestData();

        when(secretRepository.save(any())).thenReturn(secretData);

        var result = secretService.saveSecretData(new SecretRequest("secretTest", 10, 5));

        assertEquals(result, secretData);

        verify(secretRepository).save(any());
    }

    @Test
    void shouldRetrieveByHash() {
        var secretData = createTestData();
        var savedData = createTestData();
        savedData.setRemainingReviews(secretData.getRemainingReviews() - 1);
        var hashValue = "hash";

        when(secretRepository.findByHashAndAvailable(hashValue, true)).thenReturn(Optional.of(secretData));
        when(secretRepository.save(any())).thenReturn(savedData);

        var result = secretService.retrieveByHash(hashValue).get();

        assertEquals(49, result.getRemainingReviews());

        verify(secretRepository).findByHashAndAvailable(hashValue, true);
        verify(secretRepository).save(secretData);

    }

    @Test
    public void shouldNotBeAvailableAfter3Reviews() {
        var secretData = createTestData();
        secretData.setRemainingReviews(3);
        var hashValue = "hash";

        when(secretRepository.findByHashAndAvailable(hashValue, true)).thenReturn(Optional.of(secretData));
        when(secretRepository.save(any())).thenReturn(secretData);

        for (int i = 0; i < 3; i++) {
            var result = secretService.retrieveByHash(hashValue);
            assertTrue(result.isPresent());
            lenient().when(secretRepository.save(any())).thenReturn(result.get());
        }
        var endResult = secretService.retrieveByHash(hashValue);
        assertTrue(endResult.isEmpty());

    }

    @Test
    public void shouldNotBeAvailableAfter1Minute() throws InterruptedException {
        var secretData = createTestData();
        secretData.setExpiresAt(new Date());
        var hashValue = "hash";

        when(secretRepository.findByHashAndAvailable(hashValue, true)).thenReturn(Optional.of(secretData));
        when(secretRepository.save(any())).thenReturn(secretData);

        Thread.sleep( 1000);

        var endResult = secretService.retrieveByHash(hashValue).get();
        assertFalse(endResult.isAvailable());
    }

    private SecretData createTestData() {
        return new SecretData(1, "hash", "secretText", new Date(), DateTime.now().plusMinutes(1).toDate(), 50, true);
    }

}