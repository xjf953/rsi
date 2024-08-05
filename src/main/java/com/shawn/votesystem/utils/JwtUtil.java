package com.shawn.votesystem.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private String sign="ccity2022";

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public JwtUtil(String sign){
        this.sign=sign;
    }
    public JwtUtil(){

    }
    public String getToken(String sign, Map<String,String> payload, int minutes){
        //生成令牌token
        Calendar instance=Calendar.getInstance();//日历类，获取日历实例
        instance.add(Calendar.MINUTE,minutes);//设置时间令牌超时为30分钟
        JWTCreator.Builder builder= JWT.create();
        payload.forEach(builder::withClaim);
        String token= builder
                //.withHeader(map)//添加头部
                .withExpiresAt(instance.getTime())//设置过期时间
                .sign(Algorithm.HMAC256(sign));//设置签名，密钥
        return token;
    }
    public String getToken( Map<String,String> payload, int minutes){
        //生成令牌token
        Calendar instance=Calendar.getInstance();//日历类，获取日历实例
        instance.add(Calendar.MINUTE,minutes);//设置时间令牌超时为30分钟
        JWTCreator.Builder builder=JWT.create();
        if (payload!=null){
            payload.forEach(builder::withClaim);}
        String token= builder
                //.withHeader(map)//添加头部
                .withExpiresAt(instance.getTime())//设置过期时间
                .sign(Algorithm.HMAC256(sign));//设置签名，密钥
        return token;
    }
    public Map<String,String> getPayLoad(String token){
        Map<String ,String> map=new HashMap<>();
        if (verifyToken(token,sign)){
            JWTVerifier v=JWT.require(Algorithm.HMAC256(sign)).build();//根据密钥，签名创建校验者
            try {
                DecodedJWT f=v.verify(token);//校验者进行校验,没有异常及时校验成功
                Map<String, Claim> mc = f.getClaims();
                for (String k:mc.keySet()){
                    map.put(k,k.equalsIgnoreCase("exp")?String.format("%tF %<tT",mc.get(k).asDate()):mc.get(k).asString());
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return map;
    }
    public  boolean verifyToken(String token, String sign){
        boolean flag=false;
        //校验令牌
        JWTVerifier v=JWT.require(Algorithm.HMAC256(sign)).build();//根据密钥，签名创建校验者
        try{
            DecodedJWT f=v.verify(token);//校验者进行校验,没有异常及时校验成功
            flag =true;
            System.out.printf("%tF %<tT%n",f.getClaim("exp").asDate());
            System.out.println(f.getPayload());
            System.out.println("-------------------------");
            f.getClaims().forEach((key,value)->{
                System.out.println(key+"===="+value);
            });
        }
        catch (TokenExpiredException tee){
            System.out.println("令牌过期");
        }
        catch (SignatureVerificationException sve){
            System.out.println("令牌异常，被篡改");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  flag;
    }

    public  boolean verifyToken(String token){
        boolean flag=false;
        //校验令牌
        JWTVerifier v=JWT.require(Algorithm.HMAC256(sign)).build();//根据密钥，签名创建校验者
        try{
            DecodedJWT f=v.verify(token);//校验者进行校验,没有异常及时校验成功
            flag =true;
            System.out.printf("%tF %<tT%n",f.getClaim("exp").asDate());
            System.out.println(f.getPayload());
            System.out.println("-------------------------");
            f.getClaims().forEach((key,value)->{
                System.out.println(key+"===="+value);
            });
        }
        catch (TokenExpiredException tee){
            System.out.println("令牌过期");
        }
        catch (SignatureVerificationException sve){
            System.out.println("令牌异常，被篡改");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  flag;
    }
}
