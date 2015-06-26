package demo.controller;

import demo.database.DbManager;
import demo.database.userManager;
import demo.model.user;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;

/**
 * Created by DanielY on 6/23/2015.
 */
@Controller
@RequestMapping(value="/")
public class loginController {
    /*// Login form
    @RequestMapping("/login.html")
    public String login() {
        return "login.html";
    }

    // Login form with error
    @RequestMapping("/login-error.html")
    public String loginError(Model model){
        model.addAttribute("loginError", true);
        return "login.html";
    }*/
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String showDynamicIndex(Model model) {
        model.addAttribute("message", "Welcome");
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            model.addAttribute("currentUser", userManager.getUsersName(userDetails.getUsername()));
        } catch (Exception e) {
            //Error getting user's first and last names
        }
        return "welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showForm(Model model) {
        user u = new user();
        model.addAttribute("user", u);
        return "login";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String processForm(@ModelAttribute(value="user") user u, Model model) {
        try {
            if (userManager.userIsValid(u.getUsername(), u.getPassword()) == true) {
                model.addAttribute("message", "Logged In!");
            }
            else {
                model.addAttribute("message", "Invalid Credentials!");
            }
                //return "dynamicIndex";
        } catch (SQLException e) {
            System.out.println("Error validating user's identity. SQLException");
        }
        return "welcome";

        /*System.out.println("Got here");
        System.out.println(u.getUsername());
        System.out.println(u.getPassword());*/
    }
}
