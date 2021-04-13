package com.example.eslearn.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "testcases")
public class GeneralTestCase {
    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String system;

    @Field(type = FieldType.Text)
    private String content;

    @Field(type = FieldType.Date,format = DateFormat.basic_date_time)
    private LocalDateTime addTime;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSystem() {
        return system;
    }
    public void setSystem(String system) {
        this.system = system;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public LocalDateTime getAddTime() {
        return addTime;
    }
    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }
    
    @Override
    public String toString() {
        return "GeneralTestCase [addTime=" + addTime + ", content=" + content + ", id=" + id + ", system=" + system
                + "]";
    }

    
}
