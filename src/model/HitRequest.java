package model;


import java.util.Objects;

public class HitRequest {
    private final long timestampInMillis;

    public HitRequest(long timestampInMillis) {
        this.timestampInMillis = timestampInMillis;
    }

    public long getTimeStampInMillis() {
        return timestampInMillis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HitRequest that)) return false;
        return timestampInMillis == that.timestampInMillis;
    }

    @Override
    public String toString() {
        return "HitRequest{" +
                "timestampInMillis=" + timestampInMillis +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestampInMillis);
    }
}
