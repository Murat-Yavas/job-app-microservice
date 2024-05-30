package com.devtech.Jobms.job.exceptions;

public class JobNotFoundException extends RuntimeException{

    public JobNotFoundException(String message) {
        super(message);
    }
}
