package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseHandler {
        public static ResponseEntity<Object> generateResponse(HttpStatus status, Object responseObj, String statusMessage) {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            if(statusMessage=="error"){
                map.put("status", "error");
                map.put("errorResponse",responseObj );
            }else{
                map.put("status", "success");
                map.put("data",responseObj );
            }

            return new ResponseEntity<Object>(map,status);
        }
    }


