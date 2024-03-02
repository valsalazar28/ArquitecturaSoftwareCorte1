package proyectoarquitecturacorte1.Proyectoarquitectura.controlador;


import org.springframework.web.bind.annotation.*;
import proyectoarquitecturacorte1.Proyectoarquitectura.modelo.Tarea;
import proyectoarquitecturacorte1.Proyectoarquitectura.servicios.TareaServicio;

import java.util.List;

@RestController
public class TareaControlador {

    private final TareaServicio tareaServicio;

    public TareaControlador(TareaServicio tareaServicio) {
        this.tareaServicio = tareaServicio;
    }

    @GetMapping("/usuario/{id_usuario}/proyecto/{id_proyecto}/historias/{id_historiaUsuario}/tareas")
    public List<Tarea> obtenerTareas(@PathVariable("id_historiaUsuario") int id){
        return tareaServicio.obtenerTareas(id);
    }

    @PostMapping("/usuario/{id_usuario}/proyecto/{id_proyecto}/historias/{id_historiaUsuario}/tareas")
    public String crearTarea(@PathVariable("id_user") int id_usuario, Tarea tareaNueva){
        return tareaServicio.crearTarea(id_usuario, tareaNueva);
    }

    @PutMapping("/usuario/{id_usuario}/proyecto/{id_proyecto}/historias/{id_historiaUsuario}/tareas/{id_tarea}")
    public String actualizarTarea(@PathVariable("id_usuario") int id_usuario,
                                  @PathVariable("id_tarea") int id_tarea,
                                  @RequestBody Tarea actualizarTarea){
        return tareaServicio.actualizarTarea(id_usuario, id_tarea, actualizarTarea);
    }

    @DeleteMapping("/usuario/{id_usuario}/proyecto/{id_proyecto}/historias/{id_historiaUsuario}/tareas/{id_tarea}")
    public String borrarTarea(@PathVariable("id_usuario") int id_usuario,
                              @PathVariable("id_tarea") int id_tarea){
        return tareaServicio.borrarTarea(id_usuario,id_tarea);
    }
}
