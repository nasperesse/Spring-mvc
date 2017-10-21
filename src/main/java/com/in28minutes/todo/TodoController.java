package com.in28minutes.todo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.in28minutes.model.Todo;
import com.in28minutes.todo.service.TodoService;

@Controller
@SessionAttributes("name")
public class TodoController {
	
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, false));
		
	}

	@Autowired
	private TodoService service;

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showTodosList(ModelMap model) {
		String user = (String) model.get("name");
		model.addAttribute("todos", service.retrieveTodos(user));
		return "list-todos";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showTodoPage(ModelMap model) {
		model.put("todo", new Todo(0, "in28Minutes", "Default", new Date(),
				false));
		return "todo";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		
		if(result.hasErrors()) {
			
			return "todo";
		}
		service.addTodo((String) model.get("name"), todo.getDesc(), todo.getTargetDate(), false);
		model.clear(); // to prevent request parameter "name" to be passed
		return "redirect:/list-todos";
	}
	
	
	@RequestMapping(value="/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(ModelMap model, @RequestParam int id) {
		service.deleteTodo(id);
		model.clear();
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value="/update-todo", method = RequestMethod.GET)
	public String updateTodo(ModelMap model, @RequestParam int id) {
		
		model.put("todo", service.retrieveTodoById(id));
		return "todo";
	}
	
	@RequestMapping(value="/update-todo", method = RequestMethod.POST)
	public String updateTodo1(ModelMap model, @Valid Todo todo, BindingResult result) {
		
		if(result.hasErrors()) {
			
			return "todo";
		}
		
		todo.setUser((String)model.get("name"));
		service.updateTodo(todo);
		model.clear();
		return "redirect:/list-todos";
	}
	
	
}