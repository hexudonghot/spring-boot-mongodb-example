package com.techprimers.mongodb.springbootmongodbexample.resource;

import com.techprimers.mongodb.springbootmongodbexample.document.Qqinfo;
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
    public Qqinfo del()
    {

//        Update update = new Update();
//        update.pull("template",new BasicDBObject("id",templateId));
//        Query query = Query.query(Criteria.where("id").is(viewTemplateId));
//         db.updateFirst(query,update, ViewTemplate.class);
        return null;
    }


    @GetMapping("/update")
    public void update()
    {

        Update update =new Update();
        update.set("template.$.view", "fuck");
        update.set("template.$.image", "fuck you");
        Query query = Query.query(new Criteria().andOperator(Criteria.where("_id").is("12"),Criteria.where("template").elemMatch(Criteria.where("_id").is("33"))));
        template.updateFirst(query, update, Viewtemplate.class);
    }

    @GetMapping("/find")
    public Viewtemplate find()
    {
//        long t1 = System.currentTimeMillis();
//        Query query=new Query();
//        Criteria first2 =Criteria.where("$userId").is(11);
//        Criteria first1 = Criteria.where("_id").is(11).elemMatch(first2);
//        query.addCriteria(first1);
//        Userinfo info = template.findOne(query,Userinfo.class);
//        long t2 = System.currentTimeMillis();
//        System.out.println(t2-t1);
//        Query query=new Query();
//        Criteria first1 = Criteria.where("_id").is(11);
//        query.addCriteria(first1);
//        Qqinfo info = template.findOne(query, Qqinfo.class);


        Query query = Query.query(new Criteria().andOperator(Criteria.where("_id").is("12"),Criteria.where("template").elemMatch(Criteria.where("_id").is("33"))));



        //Query query2=new Query(Criteria.where("_id").is(11).and("data.userId").is(0));
        Viewtemplate info= template.findOne(query,Viewtemplate.class);
        return info;
    }

}
