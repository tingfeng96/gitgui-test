package com.tan.controller;

/**
 * Created by xuhao on 15/7/22.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.tan.dto.BookDto;
import com.tan.service.BookService;
import com.tan.service.BookServiceTest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;


@Component         //定义此类为spring组件,即bean类.
@Path("/operation")
public class BookRemote {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookServiceTest bookServiceTest;

    private static Logger logger = Logger.getLogger(BookRemote.class);
    private static int index = 1;
    private static Map<Integer,BookDto> bookDtoMap = new HashMap<Integer, BookDto>();

    public BookRemote (){
        //String res = bookServiceTest.getMessage();
        //bookDtoMap = bookService.getBookMapAll();
        if(bookDtoMap.size() == 0){
            bookDtoMap.put(index,new BookDto(index++,"java program","xuhao"));
            bookDtoMap.put(index,new BookDto(index++,"c++ program","xuhao"));
            bookDtoMap.put(index,new BookDto(index++,"c program","xuhao"));
        }
    }

    @GET
    @Path("list")
    @Produces({MediaType.APPLICATION_JSON})
    public List<BookDto> getAllStudents() {
        bookDtoMap = bookService.getBookMapAll();
        List<BookDto> students = new ArrayList<BookDto>();
        students.addAll(bookDtoMap.values());
        return students;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public BookDto getMetadata(@PathParam("id") int id) {
        if(bookDtoMap.containsKey(id))
            return bookDtoMap.get(id);
        else
            return new BookDto(0, "Nil", "Nil");
    }

    @GET
    @Path("msg")
    @Produces("text/plain")
    public String getMsg() {
        String res = bookServiceTest.getMessage();
        if(res == null)
            return "null";
        else
            return res;
    }

    @POST
    @Path("add")
    @Produces("text/plain")
    public String addStudent(@FormParam("name") String name,
                             @FormParam("author") String author) {
        BookDto bookDto = new BookDto(index, name, author);
        bookDtoMap.put(index++, bookDto);

        bookService.add(bookDto);
        return String.valueOf(index-1);
    }

    @DELETE
    @Path("delete/{id}")
    @Produces("text/plain")
    public String removeStudent(@PathParam("id") int id) {
        bookService.delete(id);
        BookDto removed = bookDtoMap.remove(id);
        if(removed==null) return "failed!";
        else   return "true";
    }

    @PUT
    @Path("update")
    @Produces("text/plain")
    public String putStudent(@QueryParam("id") int id,
                             @QueryParam("name") String name,
                             @QueryParam("author") String author
    ) {
        BookDto bookDto = new BookDto(id, name, author);

        bookService.update(bookDto);
        if(!bookDtoMap.containsKey(id))
            return "failed!";
        else
            bookDtoMap.put(id, bookDto);

        return String.valueOf(id);
    }
}
