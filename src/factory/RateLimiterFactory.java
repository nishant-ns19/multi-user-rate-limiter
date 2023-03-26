package factory;

import lombok.AllArgsConstructor;
import model.RateLimiterMetadata;
import repository.InMemoryRateLimiterStorage;
import service.RateLimiter;
import strategy.IIdGenerator;
import strategy.RandomIdGenerator;


@AllArgsConstructor
public class RateLimiterFactory {
    final private long timeSliceInMillis;
    final private long hitLimitPerSlice;

    public RateLimiter getDefaultRateLimiter() {
        IIdGenerator iIdGenerator = new RandomIdGenerator();
        String id = iIdGenerator.getId();
        RateLimiterMetadata rateLimiterMetadata = new RateLimiterMetadata(timeSliceInMillis,hitLimitPerSlice);
        return new RateLimiter(new InMemoryRateLimiterStorage(id),rateLimiterMetadata);
    }
    public RateLimiter getDefaultRateLimiterWithId(String id) {
        RateLimiterMetadata rateLimiterMetadata = new RateLimiterMetadata(timeSliceInMillis,hitLimitPerSlice);
        return new RateLimiter(new InMemoryRateLimiterStorage(id),rateLimiterMetadata);
    }



}
