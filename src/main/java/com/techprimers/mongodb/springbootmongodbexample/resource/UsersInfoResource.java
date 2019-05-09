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
import org.springframework.web.bind.annotation.PathVariable;
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
    public void update(String fk, String img, String id1,String id2)
    {
        Update update =new Update();
        update.set("template.$.view", fk);
        update.set("template.$.image", img);
        Query query = Query.query(new Criteria().andOperator(Criteria.where("_id").is(id1),Criteria.where("template").elemMatch(Criteria.where("_id").is(id2))));
        template.updateFirst(query, update, Viewtemplate.class);
    }

    @GetMapping("/find")
    public Viewtemplate find(String id)
    {
        Query query = Query.query(new Criteria().andOperator(Criteria.where("_id").is(id)));
        Viewtemplate info= template.findOne(query,Viewtemplate.class);
        return info;
    }


    @GetMapping("/add")
    public void add(String id1,String id2,String view,String image)
    {
        Map mp = new HashMap();
        mp.put("_id",id2);
        mp.put("view",view);
        mp.put("image",image);
        Update update = new Update();
        update.push("template",new BasicDBObject(mp));
        Query query = Query.query(Criteria.where("_id").is(id1));
        template.updateFirst(query,update,Viewtemplate.class);
    }



    @GetMapping("/del")
    public void del(String id1,String id2)
    {
        Update update = new Update();
        update.pull("template",new BasicDBObject("_id",id2));
        Query query = Query.query(Criteria.where("_id").is(id1));
        template.updateFirst(query,update,Viewtemplate.class);
    }

}
