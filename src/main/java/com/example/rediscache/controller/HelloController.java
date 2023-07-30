package com.example.rediscache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {
    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @GetMapping
    public ResponseEntity<String> sayHello() {
        try (RedisConnection con = jedisConnectionFactory.getConnection()) {
            byte[] bs = con.get("name".getBytes());
            if (bs != null) {
                return ResponseEntity.ok(new String(bs));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during the Redis operation
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving data from Redis.");
        }
     }

}
