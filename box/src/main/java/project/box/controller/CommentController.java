package project.box.controller;

import org.springframework.web.bind.annotation.*;
import project.box.dto.CommentRequest;
import project.box.dto.StatusRequest;
import project.box.service.BlogService;
import project.box.service.CommentService;
import project.box.service.JwtAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.UUID;
import java.security.Principal;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/{blogId}")
    public String getCommentPage(@PathVariable UUID blogId, Model model) {
        model.addAttribute("blog", blogService.getBlogById(blogId));
        model.addAttribute("comments", commentService.getBlogComment(blogId));
        return "comment";
    }

    @GetMapping("/add/{blogId}")
    public String getCommentForm(@PathVariable UUID blogId, Model model) {
        model.addAttribute("blogId", blogId);
        return "comment-add";
    }

    @PostMapping("/add")
    public String createComment(@ModelAttribute CommentRequest comment, Model model, Principal principal) {
        String username = principal.getName();
        comment.setUsername(username);
        commentService.createComment(comment);
        return "redirect:/comment/" + comment.getBlogId();
    }

    @PostMapping("/like/{blogId}/{commentId}")
    public String like(@PathVariable UUID commentId, @PathVariable UUID blogId, @ModelAttribute StatusRequest statusRequest, Model model, Principal principal) {
        String username = principal.getName();
        statusRequest.setCommentId(commentId);
        statusRequest.setUsername(username);
        commentService.like(statusRequest);
        return "redirect:/comment/" + blogId;
    }
}
