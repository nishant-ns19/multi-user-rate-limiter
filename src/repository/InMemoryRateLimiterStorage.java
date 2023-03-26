package repository;

import java.util.concurrent.ConcurrentLinkedQueue;
import model.HitRequest;

public class InMemoryRateLimiterStorage implements IRateLimiterStorage {
    private final String id;
    private final ConcurrentLinkedQueue<HitRequest> concurrentLinkedQueue;

    public InMemoryRateLimiterStorage(final String id) {
        this.id=id;
        this.concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public HitRequest getFirst() {
        if(getSize()==0) {
            throw new RuntimeException("No element exists");
        }
        return concurrentLinkedQueue.peek();
    }

    @Override
    public void removeFirst() {
        if(getSize()==0) {
            throw new RuntimeException("No element exists");
        }
        concurrentLinkedQueue.remove();
    }

    @Override
    public boolean addLast(HitRequest request) {
        return concurrentLinkedQueue.add(request);
    }

    @Override
    public long getSize() {
        return concurrentLinkedQueue.size();
    }

}
