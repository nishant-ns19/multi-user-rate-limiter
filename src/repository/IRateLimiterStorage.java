package repository;

import model.HitRequest;

public interface IRateLimiterStorage {

    String getId();
    HitRequest getFirst();
    void removeFirst();
    boolean addLast(HitRequest request);
    long getSize();
}
