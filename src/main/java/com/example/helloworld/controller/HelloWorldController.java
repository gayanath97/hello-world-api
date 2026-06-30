package com.example.helloworld.controller;

import com.example.helloworld.dto.ErrorResponse;
import com.example.helloworld.dto.MessageResponse;
import com.example.helloworld.service.HelloWorldService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Hello World", description = "Greeting endpoint")
@RestController
public class HelloWorldController {

    private final HelloWorldService helloWorldService;

    public HelloWorldController(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @Operation(
            summary = "Get a personalized greeting",
            description = "Returns a greeting when the name's first letter is in the first half of the alphabet (A–M or a–m)."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Greeting generated successfully",
            content = @Content(schema = @Schema(implementation = MessageResponse.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid input (missing, blank, or second-half alphabet name)",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @GetMapping("/hello-world")
    public ResponseEntity<MessageResponse> helloWorld(
            @Parameter(description = "Name to greet; first letter must be A–M or a–m", example = "alice")
            @RequestParam(required = false) String name) {
        String greeting = helloWorldService.buildGreeting(name);
        return ResponseEntity.ok(new MessageResponse(greeting));
    }
}
