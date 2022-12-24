package HW8;

import java.time.Instant;

public class SetableClock implements Clock {
    private Instant now;

    public SetableClock(Instant now) {
        this.now = now;
    }

    public void setNow(Instant now) {
        this.now = now;
    }

    @Override
    public Instant now() {
        return now;
    }
}
