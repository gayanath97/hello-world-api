package com.example.helloworld.service;

import com.example.helloworld.exception.InvalidInputException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HelloWorldServiceTest {

    private final HelloWorldService helloWorldService = new HelloWorldService();

    @ParameterizedTest
    @ValueSource(strings = {"alice", "Alice", "ALICE", "bob", "M", "m", " charlie"})
    void buildGreeting_returnsGreetingForFirstHalfOfAlphabet(String name) {
        String greeting = helloWorldService.buildGreeting(name);

        assertEquals("Hello " + expectedFormattedName(name), greeting);
    }

    @ParameterizedTest
    @ValueSource(strings = {"nancy", "Nancy", "Zoe", "n", "N", "z"})
    void buildGreeting_throwsForSecondHalfOfAlphabet(String name) {
        assertThrows(InvalidInputException.class, () -> helloWorldService.buildGreeting(name));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void buildGreeting_throwsForBlankName(String name) {
        assertThrows(InvalidInputException.class, () -> helloWorldService.buildGreeting(name));
    }

    @Test
    void buildGreeting_throwsForNullName() {
        assertThrows(InvalidInputException.class, () -> helloWorldService.buildGreeting(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1alice", "@bob", "_name"})
    void buildGreeting_throwsForNonAlphabeticFirstCharacter(String name) {
        assertThrows(InvalidInputException.class, () -> helloWorldService.buildGreeting(name));
    }

    private String expectedFormattedName(String name) {
        String trimmed = name.trim();
        return trimmed.substring(0, 1).toUpperCase() + trimmed.substring(1).toLowerCase();
    }
}
