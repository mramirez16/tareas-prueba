package com.tareas.app.tareas_app.Controller;

import java.time.LocalDate;
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
        addTasksToModel(viewModel);
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
        try {
            query.save(model);
            viewModel.addAttribute("mensaje", "Tarea guardada correctamente");
            addTasksToModel(viewModel);
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
            addTasksToModel(viewModel);
        } catch (Exception e) {
            viewModel.addAttribute("mensaje",
                    "Error al eliminar la tarea con ID " + id + ": " + e.getMessage());
        }
        return "redirect:/form";

    }

    /**
     * Metodo auxiliar para realizar la consulta de tareas de la BD, este metodo se
     * hizo para reutilizar el mismo código en los metodos anteriores
     * 
     * @param viewModel
     */
    private void addTasksToModel(Model viewModel) {
        List<TareaModel> tasks = query.lista();
        viewModel.addAttribute("tareas", tasks);
    }

}
