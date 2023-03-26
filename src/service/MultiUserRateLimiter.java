package service;

import factory.RateLimiterFactory;

import java.util.concurrent.ConcurrentHashMap;

public class MultiUserRateLimiter {
    private final RateLimiterFactory rateLimiterFactory;
    private final ConcurrentHashMap<String, RateLimiter> rateLimiterStore;

    public MultiUserRateLimiter(RateLimiterFactory rateLimiterFactory) {
        this.rateLimiterFactory = rateLimiterFactory;
        rateLimiterStore = new ConcurrentHashMap<>();
    }

    public MultiUserRateLimiter(RateLimiterFactory rateLimiterFactory, ConcurrentHashMap<String, RateLimiter> rateLimiterStore) {
        this.rateLimiterFactory = rateLimiterFactory;
        this.rateLimiterStore = rateLimiterStore;
    }

    public boolean hit(String userId) {
        rateLimiterStore.putIfAbsent(userId, rateLimiterFactory.getDefaultRateLimiterWithId(userId));
        RateLimiter rateLimiter = rateLimiterStore.get(userId);
        return rateLimiter.hit();
    }

}
