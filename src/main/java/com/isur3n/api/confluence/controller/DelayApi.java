package com.isur3n.api.confluence.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/api/delay")
public class DelayApi {

    private static final Long defaultDelayInSeconds = 5L;
    private static final Integer maxRandomNumber = 15;

    @GetMapping("/{seconds}")
    public String delayInSeconds(@PathVariable String seconds) {
        String sleepStatus = null;

        Long delayInSeconds = defaultDelayInSeconds;
        try {
            delayInSeconds = Long.valueOf(seconds);
        }
        catch (NumberFormatException e) {
            System.out.println("Error occurred parsing, default value " + delayInSeconds + " will be used as delay.");
        }

        try {
            System.out.println("Sleeping for " + delayInSeconds + " seconds.");
            Thread.sleep(delayInSeconds * 1000L);
            System.out.println("Thread woke up from sleep.");
            sleepStatus = "Slept for " + delayInSeconds + " seconds.";
        }
        catch (InterruptedException e) {
            System.out.println("Thread interrupted while sleeping.");
            sleepStatus = "Interrupted while sleeping for " + delayInSeconds + " seconds.";
        }

        return sleepStatus;
    }

    @GetMapping("/random")
    public String randomDelay() {
        Random randomNumber = new Random();
        Long delayInSeconds = Long.valueOf(randomNumber.nextInt(maxRandomNumber));

        String sleepStatus = null;
        try {
            System.out.println("Sleeping for " + delayInSeconds + " seconds.");
            Thread.sleep(delayInSeconds * 1000L);
            System.out.println("Thread woke up from sleep.");
            sleepStatus = "Slept for " + delayInSeconds + " seconds.";
        }
        catch (InterruptedException e) {
            System.out.println("Thread interrupted while sleeping.");
            sleepStatus = "Interrupted while sleeping for " + delayInSeconds + " seconds.";
        }

        return sleepStatus;
    }
}
