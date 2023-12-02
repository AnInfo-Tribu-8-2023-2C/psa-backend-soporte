package com.psa.backend.domain.services;

import com.psa.backend.domain.dto.MessageDTO;
import com.psa.backend.domain.repositories.TaskRepository;
import com.psa.backend.domain.services.ITicketTaskService;
import com.psa.backend.domain.handlers.NotFoundException;
import com.psa.backend.domain.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TaskService implements ITaskService{
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    private ITicketTaskService ticketTaskService;

    @Override
    public Collection<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task createTask(Task task) {
        taskRepository.saveAndFlush(task);
        return task;
    }

    @Override
    public Task getTaskById(Long id) {
        Optional<Task> taskOptional =  taskRepository.findById(id);
        if (taskOptional.isEmpty()) {
            throw new NotFoundException(String.format("Task with id: %d not found", id));
        }
        return taskOptional.get();
    }

    @Override
    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public MessageDTO deleteTaskById(Long id) {
        try {
            taskRepository.deleteById(id);
            return new MessageDTO(/*String.format("Delete task with id: %d success", id)*/);
        } catch (Exception e) {
            throw new NotFoundException("Task not found");
        }
    }

    @Override
    public void associateTicket(Long ticketId, Long taskId) {
        ticketTaskService.createTicketTask(ticketId, taskId);
    }
}
