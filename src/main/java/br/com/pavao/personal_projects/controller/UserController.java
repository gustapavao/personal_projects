package br.com.pavao.personal_projects.controller;

import br.com.pavao.personal_projects.model.Projects;
import br.com.pavao.personal_projects.model.User;
import br.com.pavao.personal_projects.repositories.UserRepository;
import br.com.pavao.personal_projects.service.ServiceUser;
import br.com.pavao.personal_projects.util.Util;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceUser serviceUser;

    @GetMapping("/")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("project", new Projects());
        return modelAndView;
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastro() {
        ModelAndView modelAndView = new ModelAndView("cadastro");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @GetMapping("/save-user")
    public ModelAndView saveUser(User user) {
        ModelAndView modelAndView = new ModelAndView("save-user");
        serviceUser.saveUser(user);
        modelAndView.setViewName("redirect:/");
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid User user, BindingResult br,
                              HttpSession session) throws NoSuchAlgorithmException, ServiceExc {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        if(br.hasErrors()) {
            modelAndView.setViewName("login/login");
        }

        User userLogin = serviceUser.loginUser(user.getUser(), Util.md5(user.getPassword()));
        if(userLogin == null) {
            modelAndView.addObject("msg","Usuario não encontrado. Tente novamente");
        } else {
            session.setAttribute("usuarioLogado", userLogin);
            return index();
        }

        return modelAndView;
    }

    @PostMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return login();
    }
}
