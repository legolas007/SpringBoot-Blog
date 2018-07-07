package com.usher.springboot.blog.repository.es;


import com.usher.springboot.blog.Entities.es.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: Usher
 * @Description:
 * blog存储库
 */
public interface EsBlogRepository extends ElasticsearchRepository<EsBlog,String> {

    /**
     * 模糊查询（去重）
     * @param title
     * @param Summary
     * @param content
     * @param tags
     * @param pageable
     * @return
     */
    Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContaining(String title,String Summary,String content,String tags,Pageable pageable);

    EsBlog findByBlogId(Long blogId);
}
