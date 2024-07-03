package com.tareas.app.tareas_app.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tareas.app.tareas_app.Models.TareaModel;
import com.tareas.app.tareas_app.Query.TaskQuerys;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TaskController {
    // Variable inyectada para la ejecución de las operaciones de BD
    @Autowired
    private TaskQuerys query;

    /**
     * Route que lanza la pantalla de formulario, consulta tambien las tareas
     * actuales de la
     * base de datos
     * 
     * @param viewModel
     * @return
     */
    @GetMapping("/form")
    public String getForm(Model viewModel) {
        viewModel.addAttribute("task", new TareaModel());
        return "form.html";
    }

    /**
     * Route que guarda una nueva tarea y vuelve a consultar la lista actual de la
     * base de datos
     * 
     * @param model
     * @param viewModel
     * @return
     */
    @PostMapping("/task/save")
    public String save(TareaModel model, Model viewModel) {
        LocalDate fechaInicio = model.getFechaInicio();
        System.out.println("Fecha formateada: " + fechaInicio);
        try {
            query.save(model);
            viewModel.addAttribute("mensaje", "Tarea guardada correctamente");

        } catch (Exception e) {
            viewModel.addAttribute("mensaje", "Error al guardar la tarea: " + e.getMessage());
        }

        return "form.html";
    }

    /**
     * Route que realiza la eliminación de una tarea por medio de id
     * y vuelve a consultar la lista actual de tareas
     * 
     * @param id
     * @param viewModel
     * @return
     */
    @PostMapping("/task/delete/{id}")
    public String deleteTask(@PathVariable("id") Integer id, Model viewModel) {
        try {
            query.deleteById(id);
            viewModel.addAttribute("mensaje", "Tarea eliminada correctamente");

        } catch (Exception e) {
            viewModel.addAttribute("mensaje",
                    "Error al eliminar la tarea con ID " + id + ": " + e.getMessage());
        }
        return "redirect:/lista";

    }

    /**
     * Route que realiza la consulta de las tareas de la base de datos
     * @param viewModel
     * @return
     */
    @GetMapping("/lista")
    public String getTask(Model viewModel) {
        List<TareaModel> tareas = query.lista();
        if (tareas == null) {
            tareas = new ArrayList<>();
        }
        viewModel.addAttribute("tareas", tareas);
        return "lista.html";
    }

}
