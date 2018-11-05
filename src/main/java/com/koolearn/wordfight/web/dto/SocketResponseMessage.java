package com.koolearn.wordfight.web.dto;

public class SocketResponseMessage {
    private String responseMessage;

    public SocketResponseMessage(String responseMessage){
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}