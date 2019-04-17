package com.ming.springes.service;

import com.ming.springes.bean.BookBean;
import com.ming.springes.dao.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Description: 类作用描述
 * @Author: zhangming
 * @CreateDate: 2019/4/4 4:55 PM
 * @Version: 1.0
 */
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;


    public Optional<BookBean> findById(String id) {
        //CrudRepository中的方法
        return bookRepository.findById(id);
    }

    public BookBean save(BookBean blog) {
        return bookRepository.save(blog);
    }

    public void delete(BookBean blog) {
        bookRepository.delete(blog);
    }

    public Optional<BookBean> findOne(String id) {
        return bookRepository.findById(id);
    }

    public List<BookBean> findAll() {
        return (List<BookBean>) bookRepository.findAll();
    }


    public Page<BookBean> findByAuthor(String author, PageRequest pageRequest) {
        return bookRepository.findByAuthor(author,pageRequest);
    }


    public Page<BookBean> findByTitle(String title, PageRequest pageRequest) {
        return bookRepository.findByTitle(title,pageRequest);
    }
}
