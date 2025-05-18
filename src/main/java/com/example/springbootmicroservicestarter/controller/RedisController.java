package com.example.springbootmicroservicestarter.controller;

import com.example.springbootmicroservicestarter.service.RedisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/redis")
@Tag(name = "Redis Cache", description = "Key-value operations using Redis")
public class RedisController {

    private final RedisService redisService;

    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    @Operation(
            summary = "Store a key-value pair in Redis",
            description = "Saves the given key and value in Redis cache"
    )
    @PostMapping("/set")
    public String set(
            @Parameter(description = "Redis key", example = "username") @RequestParam String key,
            @Parameter(description = "Value to store", example = "Tomislav") @RequestParam String value
    ) {
        redisService.save(key, value);
        return "Saved!";
    }

    @Operation(
            summary = "Retrieve a value from Redis",
            description = "Fetches the value associated with the given key from Redis"
    )
    @GetMapping("/get")
    public String get(
            @Parameter(description = "Redis key to fetch", example = "username") @RequestParam String key
    ) {
        return redisService.get(key);
    }

    @Operation(
            summary = "Delete a key from Redis",
            description = "Removes the specified key from the Redis store"
    )
    @DeleteMapping("/delete")
    public String delete(
            @Parameter(description = "Redis key to delete", example = "username") @RequestParam String key
    ) {
        redisService.delete(key);
        return "Deleted!";
    }
}
