package com.techprimers.mongodb.springbootmongodbexample.resource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 主播粉丝关注关系
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AnchorFansRel implements Serializable
{
    private long uid;
    private int anchorId;
    private int fansId;
    private Long updateTime;
}
