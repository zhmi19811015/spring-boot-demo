package com.ming.springes.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @Description: 类作用描述
 * @Author: zhangming
 * @CreateDate: 2019/4/4 4:50 PM
 * @Version: 1.0
 */
@Document(indexName = "book", type = "_doc")
@Data
public class BookBean {
    @Id
    private String id;
    private String title;
    private String author;
    private String postDate;

    public BookBean(String id, String title, String author, String postDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.postDate = postDate;
    }
}
