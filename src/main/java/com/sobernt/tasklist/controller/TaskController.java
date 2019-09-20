package com.sobernt.tasklist.controller;

import com.sobernt.tasklist.exception.ServiceException;
import com.sobernt.tasklist.model.dbo.Task;
import com.sobernt.tasklist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;
    /**
     * getting list of task
     * @param dateFrom - begin of period for get list
     * @param dateTo - end of period for get list
     * @return default service response,where task.data is ArrayList<Task>.
     */
    @GetMapping("/all/{dateFrom}/{dateTo}")
    public ResponseEntity<List<Task>> getTaskList(
            @PathVariable("dateFrom") @DateTimeFormat(pattern = "dd-MM-yyyy") Date dateFrom,
            @PathVariable("dateTo") @DateTimeFormat(pattern = "dd-MM-yyyy") Date dateTo){
        ResponseEntity<List<Task>> result;
        try {
            result = new ResponseEntity<>(taskService.getTasks(dateFrom, dateTo), HttpStatus.OK);
        } catch (ServiceException ex){
            result = catchException(ex);
        }
        return result;
    }

    /**
     * getting task by ID
     * @param id - id of task
     * @return default service response,where task.data is Task model.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") Long id){
        ResponseEntity<Task> result;
        try {
            result = new ResponseEntity<>(taskService.getTask(id),HttpStatus.OK);
        } catch (ServiceException ex){
            result = catchException(ex);
        }
        return result;
    }

    /**
     * add new Task
     * @param task - task for add
     * @return default service response,where task.data is true if task added
     */
    @PutMapping("/add")
    public ResponseEntity<Boolean> addTask(@RequestBody() Task task){
        ResponseEntity<Boolean> result;
        try {
            result = new ResponseEntity<>(taskService.addTask(task),HttpStatus.OK);
        } catch (ServiceException ex){
            result = catchException(ex);
        }
        return result;
    }

    /**
     * edit your task
     * @param task - task Data
     * @return default service response,where task.data is true if task updated
     */
    @PostMapping("/update")
    public ResponseEntity<Boolean> editTask(@RequestBody() Task task){
        ResponseEntity<Boolean> result;
        try {
            result = new ResponseEntity<>(taskService.updateTask(task),HttpStatus.OK);
        } catch (ServiceException ex){
            result = catchException(ex);
        }
        return result;
    }

    /**
     * delete your task
     * @param id - id of deleted task
     * @return default service response,where task.data is true if task deleted
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable("id") Long id){
        ResponseEntity<Boolean> result;
        try {
            result = new ResponseEntity<>(taskService.deleteTask(id),HttpStatus.OK);
        } catch (ServiceException ex){
            result = catchException(ex);
        }
        return result;
    }
    private ResponseEntity catchException(ServiceException e){
        return new ResponseEntity(e.getMessage(), HttpStatus.valueOf(e.getCode()));
    }

}
