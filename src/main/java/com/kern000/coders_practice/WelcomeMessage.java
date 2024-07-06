package com.kern000.coders_practice;

import org.springframework.stereotype.Component;

@Component // annotate that this is a component
public class WelcomeMessage {
    
    public String getWelcomeMessage(){
        return "Welcome to Coder's practice";
    }
}
