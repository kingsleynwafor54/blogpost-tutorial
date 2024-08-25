package kingsCorner.blogPostService;


import kingsCorner.model.BlogPost;

import java.util.List;
import java.util.Optional;

public interface BlogPostService{
    List<BlogPost> getAllBlogPost();
    Optional<BlogPost> getPostById(Long id);
    BlogPost createBlogPost(BlogPost blogPost);
    void deleteBlogPost(Long id);
    BlogPost updatBlogPost(BlogPost blogPost, Long id);
}

