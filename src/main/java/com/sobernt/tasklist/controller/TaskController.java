package com.sobernt.tasklist.controller;

import com.sobernt.tasklist.enums.DateType;
import com.sobernt.tasklist.model.Response;
import com.sobernt.tasklist.model.dbo.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/task")
public class TaskController {
    /**
     * getting list of task
     * @param dateType - period for get list
     * @return default service response,where task.data is ArrayList<Task>.
     */
    @GetMapping("/all/{dateType}")
    public Response<ArrayList<Task>> getTaskList(@PathVariable("dateType") DateType dateType){
        Response<ArrayList<Task>> result = new Response<>();

        return result;
    }

    /**
     * getting task by ID
     * @param id - id of task
     * @return default service response,where task.data is Task model.
     */
    @GetMapping("/{id}")
    public Response<Task> getTask(@PathVariable("id") Integer id){
        Response<Task> result = new Response<>();

        return result;
    }

    /**
     * add new Task
     * @param task - task for add
     * @return default service response,where task.data is true if task added
     */
    @PutMapping("/")
    public Response<Boolean> addTask(@RequestBody() Task task){
        Response<Boolean> result = new Response<>();

        return result;
    }

    /**
     * edit your task
     * @param id - id of task
     * @param task - task Data
     * @return default service response,where task.data is true if task updated
     */
    @PostMapping("/{id}")
    public Response<Boolean> editTask(@PathVariable("id") Integer id, @RequestBody() Task task){
        Response<Boolean> result = new Response<>();

        return result;
    }

    /**
     * delete your task
     * @param id - id of deleted task
     * @return default service response,where task.data is true if task deleted
     */
    @DeleteMapping("/{id}")
    public Response<Boolean> deleteTask(@PathVariable("id") Integer id){
        Response<Boolean> result = new Response<>();

        return result;
    }
}
