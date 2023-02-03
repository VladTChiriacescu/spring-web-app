package com.in28minutes.springboot.myfirstwebapp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();

    private static int todoCount = 0;

    static {
        todos.add(new Todo(++todoCount, "in28Minutes", "Learn AWS 1",
                LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todoCount, "in28Minutes", "Learn DevOps 1",
                LocalDate.now().plusYears(2), false));
        todos.add(new Todo(++todoCount, "in28Minutes", "Learn Full Stack Development 1",
                LocalDate.now().plusYears(3), false));
    }

    public List<Todo> findByUsername(String username) {
        return todos.stream().filter(todo -> todo.getUsername().equals(username)).collect(Collectors.toList());
    }

    public List<Todo> findAll() {
        return todos;
    }

    public Todo findById(int id) {
        return todos.stream().filter(todo -> todo.getId() == id).findFirst().get();
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
        Todo todo = new Todo(++todoCount, username, description, targetDate, done);
        todos.add(todo);
    }

    public void updateTodo(Todo todo) {
        deleteTodoById(todo.getId());
        todos.add(todo);
    }

    public void deleteTodoById(int id) {
        todos.removeIf(todo -> todo.getId() == id);
    }
}
