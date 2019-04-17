package com.ming.springes.dao;

import com.ming.springes.bean.BookBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Description: 类作用描述
 * @Author: zhangming
 * @CreateDate: 2019/4/4 4:53 PM
 * @Version: 1.0
 */
public interface BookRepository extends ElasticsearchRepository<BookBean, String> {
    Page<BookBean> findByAuthor(String author, Pageable pageable);

    Page<BookBean> findByTitle(String title, Pageable pageable);

}
