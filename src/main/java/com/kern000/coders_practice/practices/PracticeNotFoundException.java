package com.kern000.coders_practice.practices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PracticeNotFoundException extends RuntimeException {

    public PracticeNotFoundException(){
        super("Practice of id not found");
    }
}
