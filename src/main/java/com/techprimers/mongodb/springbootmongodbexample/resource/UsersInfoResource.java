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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yzl
 */
@RestController
public class UsersInfoResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoTemplate.class);
    private UserInfoRepository userInfoRepository;
    @Autowired
    private MongoTemplate template;
    public UsersInfoResource(UserInfoRepository userRepository) {
        this.userInfoRepository = userRepository;
    }
    @RequestMapping("/update")
    @ResponseBody
    public void update(@RequestParam("view") String view, @RequestParam("image") String image, @RequestParam("id") String id,@RequestParam("userid") String userid)
    {
        Update update =new Update();
        update.set("template.$.view", view);
        update.set("template.$.image", image);
        update.set("template.$.updateTime", 22);
        Query query = Query.query(new Criteria().
        andOperator(Criteria.where("_id").is(id),Criteria.where("desc").is("test push"),
        Criteria.where("name").is("push"),
                        Criteria.where("template")
                        .elemMatch(Criteria.where("userid").is(userid))
                        .elemMatch(Criteria.where("updateTime").lt(11))));
        template.updateFirst(query, update, Viewtemplate.class);
    }



    @ResponseBody
    @RequestMapping(value = "/add")
    public void add(String userid,String view,String image,String id)
    {
        Map mp = new HashMap();
        mp.put("userid",userid);
        mp.put("view",view);
        mp.put("image",image);
        Update update = new Update();
        update.push("template",new BasicDBObject(mp));
        Query query = Query.query(Criteria.where("_id").is(id));
        template.updateFirst(query,update,Viewtemplate.class);
    }


    @RequestMapping(value = "/del",method = RequestMethod.POST)
    public void del(@RequestHeader("id") String id,@RequestHeader("userid")String userid)
    {
        Update update = new Update();
        update.pull("template",new BasicDBObject("userid",userid));
//        Query query = Query.query(Criteria.where("_id").is(id));

        Query query = Query.query(new Criteria().
        andOperator(Criteria.where("_id").is(id),Criteria.where("desc").is("test push"),
        Criteria.where("name").is("push"),
        Criteria.where("template")
        .elemMatch(Criteria.where("userid").is(userid))
        .elemMatch(Criteria.where("updateTime").lt(66))));


        template.updateFirst(query,update,Viewtemplate.class);
    }



    @RequestMapping(value = "/find")
    public void find()
    {
//        Update update = new Update();
//        update.pull("template",new BasicDBObject("userid",userid));
//        Query query = Query.query(Criteria.where("_id").is(id));
//        template.updateFirst(query,update,Viewtemplate.class);


        Query query = new Query(Criteria.where("template.userid").is("4412").and("id").is("11"));
        System.out.println(template.findOne(query, Viewtemplate.class));



    }


    /**
     * http://localhost:8095/rest/usersinfo/find?id=5cda33f180a857acbb7619ab
     * http://localhost:8095/rest/usersinfo/update?fk=aaaa&img=bbbb&id1=5cda33f180a857acbb7619ab&id2=33
     * http://localhost:8095/rest/usersinfo/add?id1=5cda33f180a857acbb7619ab&id2=44&view=aaa&image=444
     * http://localhost:8095/rest/usersinfo/del?id1=5cda33f180a857acbb7619ab&id2=44
     *
     *
     * http://localhost:8095/rest/usersinfo/update?fk=aaaa&img=bbbb&id1=5cda33f180a857acbb7619ab2&id2=33
     */
}
