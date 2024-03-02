package proyectoarquitecturacorte1.Proyectoarquitectura.controlador;

import org.springframework.web.bind.annotation.*;
import proyectoarquitecturacorte1.Proyectoarquitectura.modelo.HistoriaUsuario;
import proyectoarquitecturacorte1.Proyectoarquitectura.repositorio.HistoriaUsuarioRepositorio;

import java.util.List;

@RestController
public class HistoriaUsuarioControlador {
    private final HistoriaUsuarioRepositorio historiaUsuarioServicio;

    public HistoriaUsuarioControlador(HistoriaUsuarioRepositorio historiaUsuarioRepositorio) {
        this.historiaUsuarioServicio = historiaUsuarioRepositorio;
    }
    @GetMapping("/usuario/{id_usuario}/proyecto/{id_proyecto}/historias")
    public List<HistoriaUsuario> obtenerHistoriasUsuario(@PathVariable("id_proyecto") int id_proyecto){
        return historiaUsuarioServicio.obtenerHistoriasUsuario(id_proyecto);
    }

    @PostMapping("/usuario/{id_usuario}/proyecto/{id_proyecto}/historias")
    public String crearHistoriasUsuario(@RequestBody HistoriaUsuario newUserStory, @PathVariable("id_usuario") int id_user){
        return historiaUsuarioServicio.crearHistoriasUsuario(newUserStory, id_user);
    }

    @PutMapping("/usuario/{id_usuario}/proyecto/{id_proyecto}/historias/{id_historia_usuario}")
    public String actualizarHistoriasUsuario(@PathVariable("id_usuario")  int id_usuario,
                                             @PathVariable("id_proyecto") int id_proyecto,
                                             @RequestBody HistoriaUsuario actualizarHistoriaUsuario){
        return historiaUsuarioServicio.actualizarHistoriasUsuario(id_usuario,id_proyecto,actualizarHistoriaUsuario);
    }

    @DeleteMapping("/usuario/{id_usuario}/proyecto/{id_proyecto}/historias/{id_historia_usuario}")
    public String eliminarHistoriasUsuario(@PathVariable("id_usuario")  int id_usuario,
                                           @PathVariable("id_historia_usuario") int id_historia_usuario){
        return historiaUsuarioServicio.eliminarHistoriasUsuario (id_usuario,id_historia_usuario);
    }
}
