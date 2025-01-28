package com.followup.exception;


public class MaterialNotFoundException extends RuntimeException{
    public MaterialNotFoundException(String message){
        super(message);
    }
}
