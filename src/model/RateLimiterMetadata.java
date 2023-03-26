package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RateLimiterMetadata {
    private long timeSliceInMillis;
    private long hitLimitPerSlice;

}
