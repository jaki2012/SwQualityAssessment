package com.tongji409.website.controller;

/**
 * Created by lijiechu on 16/11/15.
 */

import com.alibaba.fastjson.JSONObject;
import com.tongji409.domain.Task;
import com.tongji409.util.token.annotation.Authorization;
import com.tongji409.website.service.StaticDefectService;
import com.tongji409.website.service.TaskService;
import com.tongji409.website.controller.Support.BaseDispatcher;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@RestController
@Scope("prototype")
@RequestMapping("/api")
public class TaskController extends BaseDispatcher {

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
    @RequestMapping(value = "/tasks", method = RequestMethod.GET, produces="text/html;charset=UTF-8")
    @Authorization
    public String getTasks() {
        taskService.setFuncname("/getTasks");
        taskService.setLog(log);
        if ("true".equals(request.getAttribute("401"))) {
            //Spring AOP CGLIB动态代理不支持final类 故包装一层
            taskService.ensureNotLogin();
        } else
            taskService.getTasks();
        return taskService.getResultJson();
    }

    //返回任务数量
    @RequestMapping(value = "/task/nums",  method = RequestMethod.GET)
    public @ResponseBody String showAllTask() {
        taskService.setFuncname("/getTaskNums");
        taskService.setLog(log);
        taskService.countTask();
        return taskService.getResultJson();
    }

    //RestfulAPI 参数列表形式请求
    @RequestMapping(value = "/task/{name}/{version}/{path}", method = RequestMethod.POST)
    public String startTask(@PathVariable(value = "name") String projectName,
                     @PathVariable(value = "version") String projectVersion,
                     @PathVariable(value = "path") String projectPath) {
        taskService.setFuncname("/enqueueTask");
        //Remember to add , otherwise JavaNullPointerException
        taskService.setLog(this.log);
        taskService.enqueueTask(projectName, projectVersion, projectPath);
        return taskService.getResultJson();
    }

    //RestfulAPI Body Json形式请求
    @RequestMapping(value = "/task", method = RequestMethod.POST)
    public JSONObject startTask(@RequestBody Task newTask) {
        taskService.setFuncname("/enqueueTask");
        taskService.setLog(this.log);


        return taskService.enqueueTask(newTask);
    }
}
