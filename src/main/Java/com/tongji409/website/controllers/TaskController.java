package com.tongji409.website.controllers;

/**
 * Created by lijiechu on 16/11/15.
 */
import com.tongji409.domain.Task;
import com.tongji409.util.token.annotation.Authorization;
import com.tongji409.website.services.StaticDefectService;
import com.tongji409.website.services.TaskService;
import com.tongji409.website.controllers.Support.BaseDispatcher;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@Scope("prototype")
@RequestMapping("/api")
public class TaskController extends BaseDispatcher{

    @Resource(name = "taskService")
    private TaskService taskService;

    @Resource(name = "staticDefectService")
    private StaticDefectService staticDefectService;

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
    //返回所有任务列表
    @RequestMapping(value = "/tasks", method = RequestMethod.GET,produces="text/html;charset=UTF-8")
    @Authorization
    public @ResponseBody String getTasks() {
        taskService.setFuncname("/getTasks");
        taskService.setLog(log);
        if("true".equals(request.getAttribute("401"))){
            //Spring AOP CGLIB动态代理不支持final类 故包装一层
            taskService.ensureNotLogin();
        } else
        taskService.getTasks();
        return taskService.getResultJson();
    }

//    @RequestMapping(value = "/task", method = RequestMethod.POST)
//    public @ResponseBody String addTaskPost(@ModelAttribute("task")Task task) {
//        taskService.addTask(task);
//        return taskService.getResultJson();
//    }

//    @RequestMapping(value = "/tasks")
//    public @ResponseBody String showAllTask() {
//        return taskService.getAllTasks();
//    }

    //RestfulAPI 参数列表形式请求
    @RequestMapping(value = "/task/{name}/{version}/{path}", method = RequestMethod.POST)
    public @ResponseBody String startTask(@PathVariable(value = "name") String projectName,
                                          @PathVariable(value = "version") String projectVersion,
                                          @PathVariable(value = "path") String projectPath){
        taskService.setFuncname("/enqueueTask");
        //Remember to add , otherwise JavaNullPointerException
        taskService.setLog(this.log);
        taskService.enqueueTask(projectName,projectVersion,projectPath);
        return taskService.getResultJson();
    }

    //RestfulAPI Body Json形式请求
    @RequestMapping(value = "/task", method = RequestMethod.POST)
    public @ResponseBody String startTask(@RequestBody Task newTask) {
        taskService.setFuncname("/enqueueTask");
        taskService.setLog(this.log);
        taskService.enqueueTask(newTask);

        return taskService.getResultJson();
    }
}
