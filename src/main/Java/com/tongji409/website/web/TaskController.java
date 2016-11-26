package com.tongji409.website.web;

/**
 * Created by lijiechu on 16/11/15.
 */
import com.tongji409.domain.StaticDefect;
import com.tongji409.domain.Task;
import com.tongji409.util.log.DLogger;
import com.tongji409.website.services.StaticDefectService;
import com.tongji409.website.services.TaskService;
import com.tongji409.website.web.Support.BaseDispatcher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.IOException;

@Controller
@RequestMapping("/task")
public class TaskController extends BaseDispatcher{

    @Resource(name = "taskService")
    private TaskService taskService;

    @Resource(name = "staticDefectService")
    private StaticDefectService staticDefectService;

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

//    @RequestMapping(value = "/task", method = RequestMethod.POST)
//    public @ResponseBody String addTaskPost(@ModelAttribute("task")Task task) {
//        taskService.addTask(task);
//        return taskService.getResultJson();
//    }

    @RequestMapping(value = "/tasks")
    public @ResponseBody String showAllTask() {
        return taskService.getAllTasks();
    }

    //RestfulAPI 参数列表形式请求
    @RequestMapping(value = "/task/{name}/{version}/{path}", method = RequestMethod.POST)
    public @ResponseBody String startTask(@PathVariable(value = "name") String projectName,
                                          @PathVariable(value = "version") String projectVersion,
                                          @PathVariable(value = "path") String projectPath){
        taskService.setFuncname("/startTask");
        //Remember to add , otherwise JavaNullPointerException
        taskService.setLog(log);
        taskService.startTask(projectName,projectVersion,projectPath);
        return taskService.getResultJson();
    }

    //RestfulAPI Body Json形式请求
    @RequestMapping(value = "/task", method = RequestMethod.POST)
    public @ResponseBody String startTask(@RequestBody Task newTask) {
        taskService.setFuncname("/startTask");
        taskService.setLog(log);
        taskService.startTask(newTask);

        return taskService.getResultJson();
    }
}
