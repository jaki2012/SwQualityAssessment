package com.tongji409.website.web;

/**
 * Created by lijiechu on 16/11/15.
 */
import com.alibaba.fastjson.JSONObject;
import com.tongji409.util.log.DLogger;
import com.tongji409.website.services.TaskService;
import com.tongji409.website.services.support.ServiceInterface;
import com.tongji409.website.web.Support.BaseDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/task")
public class TaskController extends BaseDispatcher{

    @Resource(name = "taskService")
    private TaskService taskService;

    DLogger log = DLogger.getDLogger(this.getClass());

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public ModelAndView hello2() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "HelloMVC");
        mv.setViewName("task");
        return mv;
    }

//    @RequestMapping(value = "/count", method = RequestMethod.GET)
//    public ModelAndView count() {
//
//        int c = service.taskCount();
//
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("message", c);
//        mv.setViewName("task");
//        return mv;
//    }

    //@PathVariable(value="id") Integer id
    @RequestMapping(value = "/task/{id}", method = RequestMethod.POST)
    public @ResponseBody String addTask(@PathVariable(value="id") Integer id) {
        //TaskService service= new TaskService(log, "/task", this.requestjson);
        taskService.setFuncname("/task");
        taskService.setLog(log);
        taskService.addTask(id);
        return taskService.getResultJson();
    }
}
