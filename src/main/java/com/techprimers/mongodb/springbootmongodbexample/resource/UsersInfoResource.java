package com.techprimers.mongodb.springbootmongodbexample.resource;

import com.mongodb.BasicDBObject;
import com.techprimers.mongodb.springbootmongodbexample.document.Userinfo;
import com.techprimers.mongodb.springbootmongodbexample.repository.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;

@RestController
@RequestMapping("/rest/usersinfo")
public class UsersInfoResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoTemplate.class);

    private UserInfoRepository userInfoRepository;
    @Autowired
    private MongoTemplate template;

    public UsersInfoResource(UserInfoRepository userRepository) {
        this.userInfoRepository = userRepository;
    }

    @GetMapping("/add")
    public void add()
    {

    }

    @GetMapping("/del")
    public Userinfo del()
    {
        BasicDBObject bson = new BasicDBObject();
        bson.put("$eval","db.user.aggregate([{$group:{_id:'$name',count:{$sum:'$age'}}}])");
        Object object = template.getDb().command(bson);

        return null;
    }


    @GetMapping("/update")
    public void update()
    {

        template.updateFirst(new Query(where("_id").is(11)), new Update().set("data.", "aaaaa"), Userinfo.class);
    }

    @GetMapping("/find")
    public Userinfo find()
    {
        long t1 = System.currentTimeMillis();
        Query query=new Query();
        Criteria first2 =Criteria.where("$userId").is(11);
        Criteria first1 = Criteria.where("_id").is(11).elemMatch(first2);
        query.addCriteria(first1);
        Userinfo info = template.findOne(query,Userinfo.class);
        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1);
        return info;
    }

}
