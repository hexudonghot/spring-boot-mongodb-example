package com.techprimers.mongodb.springbootmongodbexample.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PushChannal implements Serializable {
    private String pushToken;
    private String pushType;
    private String pushChannal;
}
