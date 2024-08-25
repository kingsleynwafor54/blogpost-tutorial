package kingsCorner.BlogPostController;


import kingsCorner.blogPostService.BlogPostServiceImpl;
import kingsCorner.model.BlogPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("blog/api/post")
public class BlogPostController {
    @Autowired
    private BlogPostServiceImpl blogPostServiceImpl;
    @GetMapping
    public List<BlogPost> getAllBlogPost(){
        return blogPostServiceImpl.getAllBlogPost();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getPostById(@PathVariable Long id) {
        Optional<BlogPost> post = blogPostServiceImpl.getPostById(id);
        return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
