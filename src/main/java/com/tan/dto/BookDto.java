package com.tan.dto;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * Created by xuhao on 15/7/22.
 */
@XmlRootElement
public class BookDto{
    private int id;
    private String name;
    private String author;

    public BookDto(){}
    public BookDto(int id, String name, String author) {
        super();
        this.id = id;
        this.name = name;
        this.author = author;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
}
