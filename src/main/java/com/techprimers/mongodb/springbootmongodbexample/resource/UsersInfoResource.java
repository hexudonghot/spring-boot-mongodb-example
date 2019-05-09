package com.techprimers.mongodb.springbootmongodbexample.resource;

import com.mongodb.BasicDBObject;
import com.techprimers.mongodb.springbootmongodbexample.document.Viewtemplate;
import com.techprimers.mongodb.springbootmongodbexample.repository.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yzl
 */
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


    @GetMapping("/update")
    public void update()
    {

        Update update =new Update();
        update.set("template.$.view", "fuck11");
        update.set("template.$.image", "fuck you2222");
        Query query = Query.query(new Criteria().andOperator(Criteria.where("_id").is("12"),Criteria.where("template").elemMatch(Criteria.where("_id").is("33"))));
        template.updateFirst(query, update, Viewtemplate.class);
    }

    @GetMapping("/find")
    public Viewtemplate find()
    {
        Query query = Query.query(new Criteria().andOperator(Criteria.where("_id").is("12"),Criteria.where("template").elemMatch(Criteria.where("_id").is("33"))));
        Viewtemplate info= template.findOne(query,Viewtemplate.class);
        return info;
    }


    @GetMapping("/add")
    public void add()
    {
        /**
         * db.getCollection('user_info').update(
         * {"_id": ObjectId("5cd0f42a39e94fbcf6940da3")},
         * {$push: { data: { userId: 2,cont:"xxxx",sec:10,userId:98988}}})
         */

        Map mp = new HashMap();
        mp.put("_id","444");
        mp.put("view","5555");
        mp.put("image","66666");
        Update update = new Update();
        update.push("template",new BasicDBObject(mp));
        Query query = Query.query(Criteria.where("_id").is("33"));
        template.updateFirst(query,update,Viewtemplate.class);
    }



    @GetMapping("/del")
    public void del()
    {
        Update update = new Update();
        update.pull("template",new BasicDBObject("_id","333"));
        Query query = Query.query(Criteria.where("_id").is("33"));
        template.updateFirst(query,update,Viewtemplate.class);
    }

}
