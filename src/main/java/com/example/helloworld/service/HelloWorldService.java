package com.example.helloworld.service;

import com.example.helloworld.exception.InvalidInputException;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

    public String buildGreeting(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidInputException();
        }

        String trimmed = name.trim();
        char firstLetter = trimmed.charAt(0);
        if (!isFirstHalfOfAlphabet(firstLetter)) {
            throw new InvalidInputException();
        }

        return "Hello " + formatName(trimmed);
    }

    private boolean isFirstHalfOfAlphabet(char letter) {
        return (letter >= 'A' && letter <= 'M') || (letter >= 'a' && letter <= 'm');
    }

    private String formatName(String trimmed) {
        return trimmed.substring(0, 1).toUpperCase() + trimmed.substring(1).toLowerCase();
    }
}
