package com.ming.springes.controller;

import com.ming.springes.bean.BookBean;
import com.ming.springes.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @Description: 类作用描述
 * @Author: zhangming
 * @CreateDate: 2019/4/4 4:58 PM
 * @Version: 1.0
 */
@RestController
public class ElasticController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/book/{id}")
    @ResponseBody
    public BookBean getBookById(@PathVariable String id){
        Optional<BookBean> opt =bookService.findById(id);
        BookBean book=opt.get();
        System.out.println(book);
        return book;
    }

    @RequestMapping("/save")
    @ResponseBody
    public void Save(){
        BookBean book=new BookBean("1","ES入门教程","程裕强","2018-10-01");
        System.out.println(book);
        bookService.save(book);
    }
}
