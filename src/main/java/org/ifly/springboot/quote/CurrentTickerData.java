package org.ifly.springboot.quote;

import java.math.BigDecimal;

/**
 * @author: Alex
 * @Date: 2018/6/27 14:13
 * @Description:
 */
public class CurrentTickerData {

    private String market;
    private Long date=System.currentTimeMillis();
    private BigDecimal sell=new BigDecimal(0);
    private BigDecimal buy=new BigDecimal(0);
    private BigDecimal high=new BigDecimal(0);
    private BigDecimal low=new BigDecimal(0);
    private BigDecimal last=new BigDecimal(0);
    private BigDecimal last24=new BigDecimal(0);
    private BigDecimal rate=new BigDecimal(0);
    private BigDecimal vol=new BigDecimal(0);


    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public BigDecimal getSell() {
        return sell;
    }

    public void setSell(BigDecimal sell) {
        this.sell = sell;
    }

    public BigDecimal getBuy() {
        return buy;
    }

    public void setBuy(BigDecimal buy) {
        this.buy = buy;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getLast() {
        return last;
    }

    public void setLast(BigDecimal last) {
        this.last = last;
    }

    public BigDecimal getLast24() {
        return last24;
    }

    public void setLast24(BigDecimal last24) {
        this.last24 = last24;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getVol() {
        return vol;
    }

    public void setVol(BigDecimal vol) {
        this.vol = vol;
    }
}
