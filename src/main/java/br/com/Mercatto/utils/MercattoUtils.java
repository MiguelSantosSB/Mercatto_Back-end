package br.com.Mercatto.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MercattoUtils {

    private MercattoUtils(){

    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus){
        return new ResponseEntity<String>("{\"message\":\""+ responseMessage +"\"}", httpStatus);
    }

}
