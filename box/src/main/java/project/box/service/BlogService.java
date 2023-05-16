package project.box.service;

import project.box.dto.BlogRequest;
import project.box.dto.BlogResponse;
import project.box.dto.StatusRequest;
import project.box.model.Blog;
import project.box.repository.BlogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BlogService {

    @Autowired
    private BlogRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public BlogResponse getBlogById(UUID blogId) {
        Blog blog = repository.findById(blogId).get();
        return modelMapper.map(blog, BlogResponse.class);
    }

    public List<BlogResponse> getBlogs() {
        List<Blog> blogs = repository.findAll();

        List<BlogResponse> dtos = blogs
                .stream()
                .map(blog -> modelMapper.map(blog, BlogResponse.class))
                .collect(Collectors.toList());
        return dtos;
    }

    public void create(BlogRequest blogDto) {
        Blog blog = modelMapper.map(blogDto, Blog.class);
        blog.setCreatedAt(Instant.now());
        repository.save(blog);
    }
}
