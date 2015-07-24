package com.tan.service;

/**
 * Created by xuhao on 15/7/22.
 */
import javax.annotation.Resource;

import com.tan.dto.BookDto;
import org.springframework.stereotype.Component;

import com.tan.dao.BookDao;
import com.tan.model.Book;

import java.util.HashMap;
import java.util.Map;

@Component
public class BookService {

    private BookDao bookDao;

    public BookDao getBookDao() {
        return bookDao;
    }

    public static BookDto getDtoFromBook(Book book){
        BookDto bookDto = new BookDto(book.getId(),book.getName(),book.getAuthor());
        return bookDto;
    }

    public static Book getBookFromDto(BookDto bookDto){
        Book book = new Book(bookDto.getId(),bookDto.getName(),bookDto.getAuthor());
        return book;
    }

    @Resource
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public Map<Integer,BookDto> getBookMapAll() {
        Map<Integer,BookDto> bookDtoMap = new HashMap<Integer, BookDto>();
        Map<Integer,Book> bookMap = bookDao.getBookMapAll();

        for (int key : bookMap.keySet()) {
            bookDtoMap.put(key, getDtoFromBook(bookMap.get(key)));
        }

        return bookDtoMap;
    }

    public void add(BookDto bookDto){

        bookDao.add(getBookFromDto(bookDto));
    }

    public void delete(int id){

        bookDao.delete(id);
    }

    public void update(BookDto bookDto){

        bookDao.update(getBookFromDto(bookDto));
    }

}