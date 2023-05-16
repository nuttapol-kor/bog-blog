package project.box.controller;

import project.box.dto.BlogRequest;
import project.box.service.BlogService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blog")
    public String getBlogPage(Model model) {
        model.addAttribute("blogs", blogService.getBlogs());
        return "blog";
    }

    @GetMapping("/blog/add")
    public String getAddPage(BlogRequest blogRequest) {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String addBlog(@Valid BlogRequest blog, BindingResult result, Model model) {
        if (result.hasErrors()) return "blog-add";
        blogService.create(blog);
        return "redirect:/blog";
    }
}
