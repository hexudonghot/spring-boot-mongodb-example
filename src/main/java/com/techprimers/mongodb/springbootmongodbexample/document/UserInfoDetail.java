package com.techprimers.mongodb.springbootmongodbexample.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "ss")
public class UserInfoDetail implements Serializable
{
    private Long uid;
    private Long lastTime;
    private String pushChannals;
    private String gender;
    private String language;
    private Long updateTime;
    private String avatar;
    private String userName;
    private String upliveCode;
    private String qualityAuthor;
    private String setting;
    private String mobilePhone;
    private String feature;
    private Long createTime;
    private String countryCode;
    private String grade;
    private String location;
}
