package com.example.demo;

import java.util.List;

public class ErrorResponse {
        private String code;
        private List<String> messages;

        public String getCode() {
            return code;
        }

        public List<String> getMessages() {
            return messages;
        }

        public ErrorResponse(String code, List<String> messages) {
            this.code = code;
            this.messages = messages;
        }

}
