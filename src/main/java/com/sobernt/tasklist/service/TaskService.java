package com.sobernt.tasklist.service;

import com.sobernt.tasklist.exception.ServiceException;
import com.sobernt.tasklist.model.dbo.Task;
import com.sobernt.tasklist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getTasks(Date datefrom, Date dateTo) throws ServiceException {
        List<Task> tasks = new ArrayList<>();

        try{
            tasks = taskRepository.findAllByDates(datefrom, dateTo);
            return tasks;
        }catch (DataAccessException e){
            throw new ServiceException(500,"Database error");
        }catch (Exception e){
            throw new ServiceException(500,"Undefined error");
        }
    }

    public Task getTask(Long id) throws ServiceException {
        try{
            return taskRepository.getFirstById(id);
        }catch (DataAccessException e){
            throw new ServiceException(500,"Database error");
        }catch (Exception e){
            throw new ServiceException(500,"Undefined error");
        }
    }

    public boolean addTask(Task task) throws ServiceException {
        if(!this.testTaskTimeForOverlapping(task)){
            throw new ServiceException(400,"task overlapping");
        }
        try {
            taskRepository.save(task);
            return true;
        } catch (DataAccessException e){
            throw new ServiceException(500,"Database error");
        }catch (Exception e){
            throw new ServiceException(500,"Undefined error");
        }
    }
    public boolean updateTask(Task task) throws ServiceException {
        if(!this.testTaskTimeForOverlapping(task)){
            throw new ServiceException(401,"task overlapping");
        }
        try {

            Task oldTask = taskRepository.getFirstById(task.id);
            oldTask.dateStart = task.dateStart;
            oldTask.dateEnd = task.dateEnd;
            oldTask.title = task.title;
            oldTask.description = task.description;
            taskRepository.save(task);
            return true;
        } catch (DataAccessException e){
            throw new ServiceException(500,"Database error");
        }catch (Exception e){
            throw new ServiceException(500,"Undefined error");
        }
    }

    public boolean deleteTask(Long id) throws ServiceException {
        try {
            taskRepository.deleteById(id);
            return true;
        }
        catch (EmptyResultDataAccessException e){
            throw new ServiceException(404,"Task not found");
        }
        catch (DataAccessException e){
            e.printStackTrace();
            throw new ServiceException(500,"Database error");
        }
        catch (Exception e){
            throw new ServiceException(500,"Undefined error");
        }
    }

    private boolean testTaskTimeForOverlapping(Task task) throws ServiceException {
        try {
            return task.id == null ?
                    taskRepository.getTasksOverlappingCount(task.dateStart, task.dateEnd) == 0 :
                    taskRepository.getTasksOverlappingCount(task.dateStart, task.dateEnd, task.id) == 0;
        } catch (Exception E){
            throw new ServiceException(500,"Undefined error");
        }
    }
}
