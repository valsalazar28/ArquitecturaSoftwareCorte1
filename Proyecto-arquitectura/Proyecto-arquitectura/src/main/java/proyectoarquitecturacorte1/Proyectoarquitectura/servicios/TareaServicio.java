package proyectoarquitecturacorte1.Proyectoarquitectura.servicios;


import org.springframework.stereotype.Service;
import proyectoarquitecturacorte1.Proyectoarquitectura.modelo.Tarea;
import proyectoarquitecturacorte1.Proyectoarquitectura.repositorio.TareaRepositorio;

import java.util.List;

@Service
public class TareaServicio {

    private final TareaRepositorio repositorio;

    public TareaServicio(TareaRepositorio repositorio) {
        this.repositorio = repositorio;
    }


    public List<Tarea> obtenerTareas(int id) {
        return repositorio.obtenerTareas(id);
    }

    public String crearTarea(int id_usuario, Tarea tareaNueva) {
        return repositorio.crearTarea(id_usuario, tareaNueva);
    }

    public String actualizarTarea(int id_usuario, int id_tarea, Tarea tareaNueva) {
        return repositorio.actualizarTarea(id_usuario,id_tarea,tareaNueva);
    }

    public String borrarTarea(int id_usuario, int id_tarea) {
        return repositorio.borrarTarea(id_usuario, id_tarea);
    }
}
