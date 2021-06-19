package com.example.demo.Service;

import com.example.demo.util.GlobalVariable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    @Value("${env}")
    String env;

    public String getMessageError(String msgError) {

        String messageError = null;
        if (env.equals("DEV")) {
            messageError = msgError;
        } else if (env.equals("PROD")) {
            messageError = GlobalVariable.messageError;
        }

        return messageError;

    }
}
