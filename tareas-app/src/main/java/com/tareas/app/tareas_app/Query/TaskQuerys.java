package com.tareas.app.tareas_app.Query;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import com.tareas.app.tareas_app.Models.TareaModel;

@Mapper
public interface TaskQuerys {

    /**
     * Metodo que ejecuta el query del INSERT hacia la BD
     * 
     * @param model
     */
    @Insert("INSERT INTO tareas (descripcion, nombre, fechaInicio) VALUES (#{descripcion}, #{nombre}, #{fechaInicio})")
    public void save(TareaModel model);

    /**
     * Metodo que realiza la consulta de las tareas hacia la BD
     * 
     * @return
     */
    @Select("SELECT * FROM tareas")
    List<TareaModel> lista();

    /**
     * Metodo que realiza la eliminación de una tarea de la BD por medio del ID
     * 
     * @param id
     */
    @Delete("DELETE FROM tareas WHERE id = #{id}")
    void deleteById(@Param("id") Integer id);

}
