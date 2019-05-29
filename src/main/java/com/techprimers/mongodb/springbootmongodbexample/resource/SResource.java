package com.techprimers.mongodb.springbootmongodbexample.resource;

import com.alibaba.fastjson.JSON;
import com.mongodb.BasicDBObject;
import com.techprimers.mongodb.springbootmongodbexample.document.UserInfoDetail;
import com.techprimers.mongodb.springbootmongodbexample.document.Viewtemplate;
import com.techprimers.mongodb.springbootmongodbexample.repository.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yzl
 */
@RestController
public class SResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoTemplate.class);
    private UserInfoRepository userInfoRepository;
    @Autowired
    private MongoTemplate template;
    public SResource(UserInfoRepository userRepository) {
        this.userInfoRepository = userRepository;
    }
    @RequestMapping("/updates")
    @ResponseBody
    public void updates()
    {
        Update update =new Update();
        update.set("setting.sexModify", 22);
        Query query = Query.query(new Criteria().
        andOperator(Criteria.where("uid").is(2247151)));
        template.updateFirst(query, update, UserInfoDetail.class);
    }

    @RequestMapping("/ad")
    @ResponseBody
    public void ad()
    {
        Update update =new Update();
        update.set("setting.ffffff", 22);
        Query query = Query.query(new Criteria().
                andOperator(Criteria.where("uid").is(2247151)));
        template.updateFirst(query, update, UserInfoDetail.class);
    }

    @RequestMapping("/dl")
    @ResponseBody
    public void dl()
    {
        Update update =new Update();
        update.unset("setting.ffffff");
        Query query = Query.query(new Criteria().
                andOperator(Criteria.where("uid").is(2247151)));
        template.updateFirst(query, update, UserInfoDetail.class);
    }



    @RequestMapping("/upArray")
    @ResponseBody
    public void upArray(@RequestHeader("pushToken") String pushToken,@RequestHeader("pushType")Integer pushType
    ,@RequestHeader("channel") Integer channel,@RequestHeader("uid")Integer uid)
    {
        Update update =new Update();
        update.set("pushChannals.$.pushToken", "aaaaaa");
        update.set("pushChannals.$.pushType", pushType);
        update.set("pushChannals.$.channel", channel);
        Query query = Query.query(new Criteria().
        andOperator(Criteria.where("uid").is(uid),
        Criteria.where("pushChannals")
        .elemMatch(Criteria.where("pushToken").is(pushToken))
        .elemMatch(Criteria.where("pushType").is(pushType))
        .elemMatch(Criteria.where("channel").is(channel))));
        template.upsert(query, update, UserInfoDetail.class);
    }


    @RequestMapping("/pro")
    @ResponseBody
    public String pro() {

        ProjectionOperation project = Aggregation.project("_id")
              //  .and("userInfo").as("setting")
                .and("userInfo").as("userInfo");
        MatchOperation match = Aggregation.match(new Criteria("_id").is("22")
               // .and("pushChannals.pushType").is(channal.getPushType())
               // .and("pushChannals.pushChannal").is(channal.getPushChannal())
        );
        UnwindOperation unwind = Aggregation.unwind("userInfo");
        Aggregation aggregation = Aggregation.newAggregation(match, project, unwind);
        AggregationResults<Object> results = template.aggregate(aggregation, "Aggregation", Object.class);
        return JSON.toJSONString(results.getMappedResults());
    }
}
