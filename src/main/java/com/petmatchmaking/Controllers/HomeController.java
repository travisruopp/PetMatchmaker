package com.petmatchmaking.Controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.petmatchmaking.Dtos.AnswerDto;
import com.petmatchmaking.Dtos.QuestionDto;
import com.petmatchmaking.Dtos.ScoreboardDto;
import com.petmatchmaking.Dtos.UserDto;
import com.petmatchmaking.Models.AnswerModel;
import com.petmatchmaking.Models.RulebookModel;
import com.petmatchmaking.Models.ScoreboardModel;
import com.petmatchmaking.Models.UserModel;
import com.petmatchmaking.Services.AnswerService;
import com.petmatchmaking.Services.QuestionService;
import com.petmatchmaking.Services.RulebookService;
import com.petmatchmaking.Services.ScoreboardCompare;
import com.petmatchmaking.Services.ScoreboardService;
import com.petmatchmaking.Services.UserService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class HomeController extends BaseController {

    @Resource
    private final UserService userService;

    @Resource
    private final ScoreboardService scoreboardService;

    @Resource
    private final QuestionService questionService;

    @Resource
    private final AnswerService answerService;

    @Resource
    private final RulebookService rulebookService;

    public HomeController(UserService userService,
            ScoreboardService scoreboardService,
            QuestionService questionService,
            AnswerService answerService,
            RulebookService rulebookService) {
        this.userService = userService;
        this.scoreboardService = scoreboardService;
        this.questionService = questionService;
        this.answerService = answerService;
        this.rulebookService = rulebookService;
    }

    @GetMapping
    public String getIndex(@ModelAttribute("loginUser") UserDto login, Model model, HttpServletRequest request,
            HttpServletResponse response) {
        model.addAttribute("isLoggedIn", isUserLoggedIn(request));
        if (isUserLoggedIn(request)) {
            model.addAttribute("userId", getUserName(request));
        }
        return "home/index";
    }

    /**
     * Gets the Quiz question one at a time based o a cookie
     */
    @GetMapping("/quiz")
    public String getQuiz(Model model, HttpServletRequest request, HttpServletResponse response) {
        // Checks user for a valid login
        if (!isUserLoggedIn(request)) {
            return "redirect:/createlogin"; // Make sure they login
        }

        // Lets get the order of the next question
        Integer questionOrder = 0;
        String order = getCookieValue("questionOrder", request);
        if (order.length() > 0) {
            // Save the current order if no order present
            questionOrder = Integer.parseInt(order);
        }

        // Incurrment the order
        questionOrder++;
        writeCookieValue("questionOrder", questionOrder.toString(), response);

        // Get all of the current scoreboard
        Iterable<ScoreboardDto> score = scoreboardService.findAllByUserId(getUserId(request));

        // Place the scoreboard in the model object
        model.addAttribute("scoreboard", score);

        // Get all of the questions
        ArrayList<QuestionDto> questions = questionService.findAllDtos();

        // if no more questions then results
        if (questionOrder >= (questions.size() - 1)) {
            // Reset order cookie
            writeCookieValue("questionOrder", null, response);
            // open the result page
            return "redirect:/result";
        }

        // Get the current question based on order
        QuestionDto question = questions.get((questionOrder - 1));

        // Get an array of answer responses
        Boolean[] answers = question.getAnswers();

        // Write question and answer to the model
        model.addAttribute("question", question);
        model.addAttribute("answers", answers);

        // Display the quiz html
        return "home/quiz";
    }

    /**
     * Records the answers to the Scoreboard
     */
    @PostMapping("/question")
    public String postMethodName(@ModelAttribute("question") QuestionDto question, HttpServletRequest request) {

        // Gets the current question oder number
        Integer questionOrder = 0;
        String order = getCookieValue("questionOrder", request);
        if (order.length() > 0) {
            questionOrder = Integer.parseInt(order);
        }

        // Gets the scoreboard
        Iterable<ScoreboardDto> scores = scoreboardService.findAllByUserId(getUserId(request));

        // Gets all the questions
        ArrayList<QuestionDto> questions = questionService.findAllDtos();

        // Checks if complete
        if (questionOrder >= (questions.size() - 1)) {
            return "redirect:/result";
        }

        // Gets the current question
        QuestionDto quest = questions.get((questionOrder - 1));
        ArrayList<RulebookModel> rules = new ArrayList<RulebookModel>();
        int counter = 0;
        // loop throught the anaswer of the current question
        for (AnswerDto dto : quest.getAnswerDto()) {

            boolean selected = question.getSelected(counter++).isSelected();
            System.out.println(dto.getAnswer());
            // Get the current rules for the answer
            if (selected) {
                for (RulebookModel rule : rulebookService.findByAnswerId(dto.getId())) {
                    rules.add(rule);
                }
            }
        }
        int loopCounter = 1;
        // loop through the rules
        for (RulebookModel rule : rules) {
            System.out.println(loopCounter++);
            for (ScoreboardDto score : scores) {
                if (score.getPetId() == rule.getPetId()) {
                    ScoreboardModel model = scoreboardService.findById(score.getId());
                    model.setScore(model.getScore() + rule.getPostiveScore());
                    model.setScore(model.getScore() + rule.getNegativeScore());
                    scoreboardService.saveScoreboard(model);
                    break;
                }
            }
        }
        return "redirect:/quiz";
    }

    @GetMapping("/result")
    public String getResult(Model model, HttpServletRequest request) {
        Long id = getUserId(request);
        Iterable<ScoreboardModel> scores = scoreboardService.findAllModelsByUserId(id);
        ArrayList<ScoreboardModel> list = scoreboardService.iterableToList(scores);
            Collections.sort(list, new ScoreboardCompare());
        model.addAttribute("scoreboard", list);
        return "home/result";
    }

    @GetMapping("/resource")
    public String getResource() {
        return "home/resource";
    }

    @GetMapping("/aboutus")
    public String getAboutUs() {
        return "home/aboutus";
    }

    @GetMapping("/createlogin")
    public String getLogin(Model model) {
        UserDto loginUser = new UserDto();
        model.addAttribute("loginUser", loginUser);
        return "home/createlogin";
    }

    @PostMapping("/createlogin")
    public String loginUser(@ModelAttribute("loginUser") UserDto login, HttpServletResponse response) {
        logout(response);
        UserModel model = login.convertToModel();
        if(model.getUserId().length()<6){
            
            return "redirect:/error/2";
        }
        if(model.getPassword().length()<6 ){
            
            return "redirect:/error/3";
        }
        else{
        userService.saveUser(model);
        // UserModel user = userService.findByUserId(userId);
        Cookie userIdCookie = new Cookie("Id", model.getId().toString());
        Cookie userNameCookie = new Cookie("username", model.getName());
        response.addCookie(userNameCookie);
        response.addCookie(userIdCookie);
        return "redirect:/";
        // }
        // return "redirect:/createlogin";
    }
}

    @GetMapping("autologin")
    public String autoLogin(HttpServletResponse response) {
        logout(response);
        UserModel user = userService.findById(1l);
        Cookie userIdCookie = new Cookie("Id", user.getId().toString());
        Cookie userNameCookie = new Cookie("username", user.getName());
        response.addCookie(userNameCookie);
        response.addCookie(userIdCookie);
        return "redirect:/";
    }

    public String getMethodName(@RequestParam String param) {
        return new String();
    }

    @GetMapping("/logout")
    public String logoutUser(HttpServletResponse response) {
        logout(response);
        return "redirect:/";
    }

    private void logout(HttpServletResponse response) {
        Cookie userIdCookie = new Cookie("Id", null);
        userIdCookie.setMaxAge(0);
        Cookie userNameCookie = new Cookie("username", null);
        userNameCookie.setMaxAge(0);
        Cookie order = new Cookie("questionOrder", null);
        order.setMaxAge(0);
        response.addCookie(userNameCookie);
        response.addCookie(userIdCookie);
        response.addCookie(order);
    }

    @GetMapping("/virtualpet")
    public String getVirtualPet() {
        return "home/virtualpet";
    }

    @PostMapping("login")
    public String login(@ModelAttribute("loginUser") UserDto login, HttpServletResponse response) {
        logout(response);

        if(login.getUserId()==null){
            return "redirect:error/?"+"User Id not found";
        }

        String userName = login.getUserId();
        String userPassword = login.getPassword();
        UserModel userModel = userService.findByUserId(userName);

        if( userModel != null && userModel.getUserId() != null && userModel.getPassword().equals(userPassword)){
            Cookie userIdCookie = new Cookie("Id", userModel.getId().toString());
            Cookie userNameCookie = new Cookie("username", userModel.getName());
            response.addCookie(userNameCookie);
            response.addCookie(userIdCookie);
            return "redirect:/";
        } else {
            return "redirect:/error/1";
        }
 
    }

    @GetMapping("/error/{id}")
    public String displayError(@PathVariable Long id, Model model)
    {
        String message = "error";
        // model.addAttribute("message", message);
        if(id==1l ){
            message="invalid login";
        }
        if(id==2l ){
            message="username must be at least 6 characters ";
        } 
        if(id==3l ){
            message="password must be at least 6 characters";
        }
        model.addAttribute("message", message);
        return "home/error";


    }
}