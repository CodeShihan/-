package com.wcd.handle;

import com.wcd.entity.HtmlJson;

import com.wcd.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/***
 * 统一的，异常对象捕获类
 * @author  wcd
 * @date  2019 7 1
 */
@ControllerAdvice
public class ErrorHandle {

    @Autowired
    HtmlJson errorJson;

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public HtmlJson handle(Exception e){

        if(e instanceof MyException){
            MyException myExecption = (MyException) e;
            errorJson.setErrcode(myExecption.getErrorCode());
            errorJson.setErrorMessaage(myExecption.getMessage());

        }
        else{
            errorJson.setErrcode(-1);
            errorJson.setErrorMessaage("未知错误");
            e.printStackTrace();
        }

        return  errorJson;
    };


}
