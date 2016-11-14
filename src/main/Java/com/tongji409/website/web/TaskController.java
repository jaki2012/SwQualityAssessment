package com.tongji409.website.web;

/**
 * Created by lijiechu on 16/11/15.
 */
import com.tongji409.website.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Resource(name = "taskService")
    private TaskService service;

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public ModelAndView hello2() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "HelloMVC");
        mv.setViewName("task");
        return mv;
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ModelAndView count() {

        int c = service.taskCount();

        ModelAndView mv = new ModelAndView();
        mv.addObject("message", c);
        mv.setViewName("task");
        return mv;
    }
}
