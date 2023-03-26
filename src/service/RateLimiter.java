package service;

import model.HitRequest;
import model.RateLimiterMetadata;
import repository.IRateLimiterStorage;

import static java.lang.System.currentTimeMillis;


public class RateLimiter {

    private final IRateLimiterStorage queue;
    private final RateLimiterMetadata rateLimiterMetadata;

    public RateLimiter(final IRateLimiterStorage queue, final RateLimiterMetadata rateLimiterMetadata) {
        this.queue = queue;
        this.rateLimiterMetadata = rateLimiterMetadata;
    }

    public boolean hit() {

        long currentTimeInMillis = currentTimeMillis();
        long currentTimeWindowStartTimeInMillis = currentTimeInMillis - rateLimiterMetadata.getTimeSliceInMillis();
        synchronized (queue) {
            while ((queue.getSize() > 0) && (currentTimeWindowStartTimeInMillis >= (queue.getFirst()).getTimeStampInMillis())) {
                queue.removeFirst();
            }
            System.out.printf("ID: %s, queueSize: %d, currentTime: %d, items per slice: %d \n", getId(), queue.getSize(), currentTimeInMillis, rateLimiterMetadata.getHitLimitPerSlice());
            return (queue.getSize() < rateLimiterMetadata.getHitLimitPerSlice()) && queue.addLast(new HitRequest(currentTimeInMillis));
        }
    }

    public String getId() {
        return queue.getId();
    }


}
