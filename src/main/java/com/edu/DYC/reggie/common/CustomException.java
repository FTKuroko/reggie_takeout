package com.edu.DYC.reggie.common;

/**
 * @author :   Kuroko
 * @date :     2023/2/24
 */

/**
 * 自定义业务异常
 */
public class CustomException extends RuntimeException{

    public CustomException(String message){
        super(message);
    }
}
