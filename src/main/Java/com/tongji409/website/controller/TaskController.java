package com.tongji409.website.controller;

/**
 * Created by lijiechu on 16/11/15.
 */

import com.alibaba.fastjson.JSONObject;
import com.tongji409.domain.Task;
import com.tongji409.util.config.StaticConstant;
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
@CrossOrigin(origins = "*")
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

    @RequestMapping(value = "/testjoin/{id}", method = RequestMethod.GET)
    public JSONObject test(@PathVariable("id") int id, @RequestParam("pageNum") int pageNum,
                           @RequestParam("pageSize")int pageSize) {
        return taskService.testJoin(id, pageNum, pageSize);
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
    @RequestMapping(value = "/tasks", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @Authorization
    public String getTasks() {
        taskService.setFuncname("/getTasks");
        taskService.setLog(log);
        if ("true".equals(request.getAttribute("401"))) {
            //Spring AOP CGLIB动态代理不支持final类 故包装一层
            taskService.ensureNotLogin();
        } else
//            long userId = (long);
            taskService.getTasks(((Long) request.getAttribute(StaticConstant.CURRENT_USER_ID)).intValue());
        return taskService.getResultJson();
    }

    @RequestMapping(value = "/task/{taskId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @Authorization
    public String getTask(@PathVariable(value = "taskId")int taskId) {
        taskService.setFuncname("/getTasks");
        taskService.setLog(log);
        if ("true".equals(request.getAttribute("401"))) {
            //Spring AOP CGLIB动态代理不支持final类 故包装一层
            taskService.ensureNotLogin();
        } else
        taskService.getTask(taskId);
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
    @Authorization
    public JSONObject startTask(@RequestBody Task newTask) {
        taskService.setFuncname("/enqueueTask");
        taskService.setLog(this.log);
        if ("true".equals(request.getAttribute("401"))) {
            //Spring AOP CGLIB动态代理不支持final类 故包装一层
            taskService.ensureNotLogin();
            return taskService.getResponseJson();
        } else{
            newTask.setUserID(((Long) request.getAttribute(StaticConstant.CURRENT_USER_ID)).intValue());
            return taskService.enqueueTask(newTask);
        }
    }
}
