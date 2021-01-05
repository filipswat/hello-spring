package org.launchcode.hellospring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

    //Thymeleaf examples
    @RequestMapping(value="hello", method = {RequestMethod.GET, RequestMethod.POST})
    public String hello(@RequestParam String name, Model model) {
        String greeting = "Hello " + name;
        model.addAttribute("greeting", greeting);
        return "hello";
    }

    @GetMapping("hello/{name}")
    public String helloAgain(@PathVariable String name, Model model) {
        String greeting = "Hello " + name;
        model.addAttribute("greeting", greeting);

        return "hello";
    }

    @GetMapping("form")
    public String helloForm() {
        return "form";
    }

    @GetMapping("hello-names")
    public String helloNames(Model model) {
        List<String> names = new ArrayList<>();
        names.add("name1");
        names.add("name2");
        names.add("name3");

        model.addAttribute("names", names);

        return "hello-list";
    }


    //create message stuff
    public static String createMessage(String name, String language) {
        String message;

        if (language.equals("Polish")) {
            message = "Czesc ";
        } else if (language.equals("French")) {
            message = "Bonjour ";
        } else if (language.equals("Spanish")) {
            message = "Hola ";
        } else if (language.equals("Hawaiian?")) {
            message = "Aloha ";
        } else {
            message = "Hello ";
        }

        return message + name;
    }

    @GetMapping("language-form")
    @ResponseBody
    public String languageForm() {
        String html =
                "<html>" +
                        "<body>" +
                        "<form method = 'post' action = '/language-greeting'>" +
                        "<label for='name-entry'>Enter your name:</label>" +
                        "<input type = 'text' name = 'name' id='name-entry'/>" +
                        "\n" +
                        "<label for='language-select'>Select a language:</label>" +
                        "<select name='language' id='language-select'>" +
                        "<option value='English'>English</option>" +
                        "<option value='Polish'>Polish</option>" +
                        "<option value='French'>French</option>" +
                        "<option value='Spanish'>Spanish</option>" +
                        "<option value='Hawaiian?'>Hawaiian?</option>" +
                        "<input type = 'submit' value = 'Greet Me!' />" +
                        "</form>" +
                        "</body>" +
                        "</html>";
        return html;
    }

    @RequestMapping(value = "language-greeting", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String languageGreeting(@RequestParam String name, @RequestParam String language) {
        return "<b>" + createMessage(name, language) + "</b>";
    }


    //random crap
    @RequestMapping(value="greeting", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String greetingPost(@RequestParam String name) {
        return "Greetings " + name;
    }


    @GetMapping("hello2/{name}")
    public String helloRedirect(@PathVariable String name) {
        return "redirect:/hello?name=" + name;
    }

    @PostMapping("goodbye")
    @ResponseBody
    public String goodbye() {
        return "Goodbye, Spring";
    }

    @RequestMapping(value="hellogoodbye", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String hellogoodbye(@RequestParam String name) {
        return "You say goodbye, and I say hello " + name;
    }
}
