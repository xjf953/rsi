package com.shawn.votesystem.processor;

import com.alibaba.fastjson.JSONArray;
import com.shawn.votesystem.constant.BaseConstant;
import com.shawn.votesystem.dto.ProjectDTO;
import com.shawn.votesystem.dto.ResultMsg;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author Xjf
 * @create 2024/7/27 22:45
 */
@Slf4j
@Component
public class RsiProcessor implements CommandLineRunner{

    private final String url = "https://data-api.binance.vision/api/v3/klines?symbol=BNBUSDT&interval=1h&limit=200";

    private RestTemplate restTemplate = new RestTemplate();

    private HashMap<String,Boolean> flag = new HashMap<>();

    @Value("${spring.mail.username}")
    private String senderMail;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void run(String... args) throws Exception {
        new RsiProcessor.Rsi("rsi").start();
    }

    private void sendMail(BigDecimal rsi,String time,BigDecimal price){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderMail);   //邮件发送人
        message.setTo("az1060088260@qq.com");  //邮件接收人
        message.setSubject(time+" RSI: "+rsi.toString() + " price:" + price);   //邮件主题
        message.setText(time +" "+ rsi);   //邮件内容
        mailSender.send(message);
    }

    class Rsi extends Thread {
        public Rsi(String name) {
            super(name);
        }
        @Override
        public void run(){
            try {
                //启动先休眠10s，降低cpu占用，供其它任务执行
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                log.error("RSI线程启动失败", e);
            }
            log.info("RSI线程启动");
            while(true){
                try {
                    Date nowDate = new Date();
                    ResponseEntity<JSONArray> forEntity = restTemplate.getForEntity(
                            url,
                            JSONArray.class
                    );
                    JSONArray list = forEntity.getBody();
//        log.info("size:{}",list.size());
                    BigDecimal pre = BigDecimal.ZERO;
                    BigDecimal downTotal= BigDecimal.ZERO;
                    BigDecimal upTotal = BigDecimal.ZERO;
                    BigDecimal period = new BigDecimal("6");
                    BigDecimal prePeriod = period.subtract(BigDecimal.ONE);

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    //处理前7个周期，得出平均up/down
                    for (int i = 0; i <= period.toBigInteger().intValue(); i++) {
                        JSONArray obj = list.getJSONArray(i);
                        Long aLong = obj.getLong(0);
                        Date date = new Date(aLong);
//            log.info("时间：{}，收盘价：{}", simpleDateFormat.format(date), obj.getBigDecimal(4).setScale(2));
                        if (i == 0) {
                            pre = obj.getBigDecimal(4).setScale(2);
                            continue;
                        }
                        BigDecimal now = obj.getBigDecimal(4).setScale(2);;
                        BigDecimal val = now.subtract(pre);
                        if (val.compareTo(BigDecimal.ZERO) < 0) {
                            downTotal = downTotal.add(val);
                        } else {
                            upTotal = upTotal.add(val);
                        }
                        pre = now;
                    }
//        log.info("up:{},down:{}",upTotal,downTotal);
                    BigDecimal avgUp = upTotal.divide(period,2, RoundingMode.HALF_UP);
                    BigDecimal avgDown = downTotal.abs().divide(period,2, RoundingMode.HALF_UP);
//        log.info("avgUp:{},avgDown:{}",avgUp,avgDown);
                    BigDecimal rs = avgUp.divide(avgDown,2,RoundingMode.HALF_UP);
//        log.info("preRs:{},",rs);
                    BigDecimal oneRsi = new BigDecimal("100").subtract(new BigDecimal("100").divide(new BigDecimal("1").add(rs),2,RoundingMode.HALF_UP));
//        log.info("preRsi:{}",oneRsi);
                    //剩余93个周期采取均值计算方式
                    for (int i = 7; i < list.size(); i++) {
                        JSONArray preHourArray = list.getJSONArray(i-1);
                        BigDecimal preHour = preHourArray.getBigDecimal(4).setScale(2);;
                        JSONArray nowHourArray = list.getJSONArray(i);
                        BigDecimal nowHour = nowHourArray.getBigDecimal(4).setScale(2);;
                        BigDecimal subtract = nowHour.subtract(preHour);
                        BigDecimal addAvgUp = avgUp.multiply(prePeriod).add(subtract.compareTo(BigDecimal.ZERO) > 0 ? subtract : BigDecimal.ZERO);
                        avgUp = addAvgUp.divide(period,2,RoundingMode.HALF_UP);
                        BigDecimal addAvgDown = avgDown.multiply(prePeriod).add(subtract.compareTo(BigDecimal.ZERO) < 0 ? subtract.abs() : BigDecimal.ZERO);
                        avgDown = addAvgDown.divide(period,2,RoundingMode.HALF_UP);
                        BigDecimal todayRs = avgUp.divide(avgDown,2,RoundingMode.HALF_UP);
                        BigDecimal todayRsi = new BigDecimal("100").subtract(new BigDecimal("100").divide(new BigDecimal("1").add(todayRs),2,RoundingMode.HALF_UP));
                        if(i ==list.size() -1){
                            String time = simpleDateFormat.format(nowDate);
                            log.info("{},nowRsi:{},price:{}",time,todayRsi,nowHour);
                            Long aLong = nowHourArray.getLong(0);
                            String nowTime = simpleDateFormat.format(new Date(aLong));
                            Boolean sendFlag = flag.get(nowTime);
                            if(todayRsi.compareTo(new BigDecimal("30.00")) < 0 && Objects.isNull(sendFlag)){
                                //rsi小于30发送邮件通知
                                sendMail(todayRsi,time,nowHour);
                                flag.put(nowTime,true);
                            }
                        }
                    }
                    //执行成功休息30秒
                    Thread.sleep(30000);
                }catch (Exception e){
                    try {
                        Thread.sleep(30000);
                    } catch (InterruptedException ex) {
                        log.error("RSI执行失败:"+e.getMessage());
                    }
                }
            }
        }

    }
}
