package org.ifly.springboot.quote;

public interface TickerService {

    public CurrentTickerData calcTicks(MatchMessageData trade);

}
