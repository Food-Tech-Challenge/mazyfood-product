package com.MAZYProduct;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@ActiveProfiles("test")
class LauncherIntegrationTest {

    @Test
    void testApplicationStartsSuccessfully() {
        assertDoesNotThrow(() -> Launcher.main(new String[]{}));
    }
}