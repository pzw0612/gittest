package org.ifly.springboot.quote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class TickerServiceImpl implements  TickerService{
    private  long msByDay=86400000l;

    @Autowired
    private  ValueOperations<String, List<MatchMessageData>> valueOperations;

    @Autowired
    private  ValueOperations<String, CurrentTickerData> valueOperations4Tick;

    private String tickerDataKey="quote:ticker:data:%s";
    private String tickResultKey="quote:ticker:result:%s";
    private Map<String,List<MatchMessageData>> marketMap = new ConcurrentHashMap<>();

    @Override
    public CurrentTickerData calcTicks(MatchMessageData trade) {

        String market = trade.getMarket();
        String rawDatakey = String.format(tickerDataKey,market);
        String resultkey = String.format(tickResultKey,market);

        List<MatchMessageData> list = valueOperations.get(rawDatakey);
        if(list==null){
            list= new ArrayList<>();
        }
        list.removeIf(matchData -> matchData.getExpire() < System.currentTimeMillis());
        trade.setExpire(System.currentTimeMillis()+ msByDay);

        CurrentTickerData  tickerData = new  CurrentTickerData();
        tickerData.setMarket(market);
        tickerData.setDate(trade.getTime());
        tickerData.setBuy(trade.getTicks());
        tickerData.setSell(trade.getTicks());
        tickerData.setLast(trade.getTicks());

        if(list.size()==0){
            tickerData.setHigh(trade.getTicks());
            tickerData.setLow(trade.getTicks());
            tickerData.setLast24(trade.getTicks());
            tickerData.setRate(BigDecimal.ZERO);
            BigDecimal volDecimal = trade.getLots().multiply(trade.getTicks()).setScale(8);
            tickerData.setVol(volDecimal);
        }else{
            MatchMessageData last24Trade= list.get(0);
            TickInfo highLowPrice=getHighLowPrice(list);
            tickerData.setHigh(highLowPrice.getHign());
            tickerData.setLow(highLowPrice.getLow());
            tickerData.setLast24(last24Trade.getTicks());
            BigDecimal rate=BigDecimal.ZERO;
            if(last24Trade.getTicks().compareTo(BigDecimal.ZERO)>0){
                rate= (trade.getTicks().subtract(last24Trade.getLastTicks()))
                        .divide(last24Trade.getLastTicks())
                        .multiply(new BigDecimal(100)).setScale(2,RoundingMode.HALF_UP);
            }
            tickerData.setRate(rate);
            tickerData.setVol(highLowPrice.getVol());
        }
        list.add(trade);
        valueOperations.set(rawDatakey,list);
        valueOperations4Tick.set(resultkey,tickerData);

        return  tickerData;

    }

    private TickInfo getHighLowPrice(List<MatchMessageData> list){
        TickInfo result = null;

        BigDecimal sum=BigDecimal.ZERO;
        for(MatchMessageData data: list){
            if(result==null){
                result= new TickInfo();
                result.setHign(data.getTicks());
                result.setLow(data.getTicks());
            }else{
                if(result.getHign().compareTo(data.getTicks())<0){
                    result.setHign(data.getTicks());
                }
                if(result.getLow().compareTo(data.getTicks())>0){
                    result.setLow(data.getTicks());
                }
            }
            sum = sum.add(data.getTicks().multiply(data.getLots()));
        }
        if(result!=null){
            sum=sum.setScale(8,RoundingMode.HALF_UP);
            result.setVol(sum);
        }

        return  result;
    }

    private static class TickInfo {

        BigDecimal hign;

        BigDecimal low;

        BigDecimal vol;

        public BigDecimal getHign() {
            return hign;
        }

        public void setHign(BigDecimal hign) {
            this.hign = hign;
        }

        public BigDecimal getLow() {
            return low;
        }

        public void setLow(BigDecimal low) {
            this.low = low;
        }

        public BigDecimal getVol() {
            return vol;
        }

        public void setVol(BigDecimal vol) {
            this.vol = vol;
        }
    }
}
