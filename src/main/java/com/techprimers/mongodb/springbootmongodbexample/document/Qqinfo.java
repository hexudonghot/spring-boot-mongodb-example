package com.techprimers.mongodb.springbootmongodbexample.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Qqinfo
{
    private int  id;
    private String  tag;
    private String data;
}
