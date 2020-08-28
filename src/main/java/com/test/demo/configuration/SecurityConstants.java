package com.test.demo.configuration;
/**
 * Created on 31.07.2020
 * @author Mykola Horkov
 * mykola.horkov@gmail.com
 */
public interface SecurityConstants {

    String AUTH_LOGIN_URL = "/login";
    String JWT_SECRET = "asd7a89s7d8a7sd987a98sd7a8s7d987as98d7a98s7d89a7sd8asdjhabshdbasjhdbjahsbdhjabsdhjabsdh";

    String TOKEN_HEADER = "Authorization";
    String TOKEN_PREFIX = "Bearer ";
    String TOKEN_TYPE = "JWT";
    String TOKEN_ISSUER = "localhost";
    String TOKEN_AUDIENCE = "localhost";
    int EXPIRATION_TIME = 4 * 60* 60 * 1000; // 4 hours
 }
