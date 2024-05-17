package com.petmatchmaking.Controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.petmatchmaking.Dtos.UserDto;
import com.petmatchmaking.Models.UserModel;
import com.petmatchmaking.Services.UserService;

import jakarta.annotation.Resource;

/**
 * Controller class that models the mapping of the auser services and
 * repository
 */
public class UserController extends BaseController{
    
    @Resource
    private final UserService userService;

    /**
     * Parametarized constructor
     * 
     * @param userService
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Method to get user by id 
     * 
     * @param id user id
     * 
     * @param model user model
     * 
     * @return user detail
     */
    @GetMapping("{id}")
    public String getUser(@PathVariable Long id, Model model){
        UserModel user = userService.findById(id);
        model.addAttribute("user detail", user);
        return "detail";
    }

    /**
     * Method to create user
     * 
     * @param model user model
     * 
     * @return (insert correct path here once known)
     */
    @GetMapping("create")
    public String createUser (Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("add new user", userDto);
        return "login";
    }

    /**
     * Method to save user
     * 
     * @param user
     * 
     * @return redirects to home page
     */
    @PostMapping()
    public String saveUser(@ModelAttribute("user") UserDto user){
        userService.saveUser(user.convertToModel());
        return "redirect:/";
    }



   

    
}
