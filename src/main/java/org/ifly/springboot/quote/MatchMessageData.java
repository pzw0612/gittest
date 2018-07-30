package org.ifly.springboot.quote;

import lombok.Data;



import java.math.BigDecimal;

@Data
public class MatchMessageData {
	
    private String market;//市场

    private String matchId;//撮合id
	
    private String askOrderId;//卖方orderid
	
    private String bidOrderId;//买方orderId
	
    private BigDecimal lots;//数量

    private BigDecimal ticks;//价格

    private String side;// taker买卖方向（1;//bid,2;//ask)

	private String status;//订单状态
	
    private long time; //撮合时间时间戳

    private BigDecimal lastTicks;//上24小时价格



    private long expire;//到期毫秒数



}
