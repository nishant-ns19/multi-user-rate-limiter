package strategy;

import java.util.UUID;

public class RandomIdGenerator implements IIdGenerator{
    @Override
    public String getId() {
        return UUID.randomUUID().toString();
    }
}
