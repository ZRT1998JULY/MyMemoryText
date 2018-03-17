package com.example.mymemorytext;

import org.litepal.crud.DataSupport;

/**
 * Created by Machenike on 2018/3/17.
 */

public class Memory extends DataSupport{
    private int id;
    private String title;
    private String content;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content=content;
    }
}
