package kingsCorner.blogPostService;


import kingsCorner.blogPostRepository.BlogPostRepository;
import kingsCorner.model.BlogPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BlogPostServiceImpl implements BlogPostService {
    @Autowired
    private BlogPostRepository blogPostRepository;
    @Override
    public List<BlogPost> getAllBlogPost() {
        return blogPostRepository.findAll();
    }

    @Override
    public Optional<BlogPost> getPostById(Long id) {
        return blogPostRepository.findById(id);
    }

    @Override
    public BlogPost createBlogPost(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    @Override
    public void deleteBlogPost(Long id) {
        blogPostRepository.deleteById(id);
    }

    @Override
    public BlogPost updatBlogPost(BlogPost blogPost, Long id) {
        blogPostRepository.findById(id)
                .map(post -> {
                   post.setContent(blogPost.getContent());
                   post.setTitle(blogPost.getTitle());
                   return blogPostRepository.save(post);
                })
                .orElseGet(()->{
                    blogPost.setId(id);
                    return  blogPostRepository.save(blogPost);
                });

        return blogPostRepository.findById(id).get();

    }
}
