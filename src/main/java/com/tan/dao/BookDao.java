package com.tan.dao;

/**
 * Created by xuhao on 15/7/22.
 */
import com.tan.dto.BookDto;
import org.springframework.stereotype.Component;

import com.tan.model.Book;

import java.util.HashMap;
import java.util.Map;

@Component
public class BookDao {

    private static Map<Integer,Book> bookMap = new HashMap<Integer, Book>();
    private static int index = 1;

    public BookDao() {
        if(bookMap.size() == 0){
            bookMap.put(index,new Book(index++,"java program","xuhao"));
            bookMap.put(index,new Book(index++,"c++ program","xuhao"));
            bookMap.put(index,new Book(index++,"c program","xuhao"));
        }
    }

    public Map<Integer,Book> getBookMapAll() {

        return bookMap;
    }

    //模拟数据库操作
    public void add(Book book){
        bookMap.put(index++,book);
    }

    //模拟数据库操作
    public void delete(int id){
        bookMap.remove(id);
    }

    public void update(Book book){

        if(!bookMap.containsKey(book.getId()))
            return;
        else
            bookMap.put(book.getId(), book);
    }
}