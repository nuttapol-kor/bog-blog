package project.box.controller;

import project.box.dto.SignupDto;
import project.box.model.User;
import project.box.service.ReCaptchaValidationService;
import project.box.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;

@Controller
public class SignupController {
    @Autowired
    private SignupService signupService;

    @Autowired
    private ReCaptchaValidationService validator;

    @GetMapping("/signup")
    public String getSignupPage(SignupDto user) {
        return "signup"; // return signup.html
    }

    @PostMapping("/signup")
    public String signupUser(@Valid SignupDto user, BindingResult result, Model model,@RequestParam(name="g-recaptcha-response") String captcha) {
        if (result.hasErrors())
            return "signup";

        String signupError = null;

        if (!signupService.isUsernameAvailable(user.getUsername())) {
            signupError = "The username already exists.";
        }

        if (signupError == null) {
            int rowsAdded = signupService.createUser(user);
            if (rowsAdded < 0) {
                signupError = "There was an error signing you up. Please try again.";
            }
        }
        if (!validator.validateCaptcha(captcha)){
            signupError = "Please verify captcha";
        }

        if (signupError == null) {
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", signupError);
        }
        model.addAttribute("signupDto", new SignupDto());
        return "signup";
    }


}
