package com.techprimers.mongodb.springbootmongodbexample.document;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "viewtemplate")
public class Viewtemplate
{
    private String id;
    private String name;//模本名称
    private String desc;//描述
    private String type;//模本类型
    List<Template> template;
    public static class Template{
        private String id;
        private String view;//视图名称
        private String image;//图片路径
        private Long updateTime;


        public Long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Long updateTime) {
            this.updateTime = updateTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getView() {
            return view;
        }

        public void setView(String view) {
            this.view = view;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Template> getTemplate() {
        return template;
    }

    public void setTemplate(List<Template> template) {
        this.template = template;
    }
}
