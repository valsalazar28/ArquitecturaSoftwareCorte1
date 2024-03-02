package proyectoarquitecturacorte1.Proyectoarquitectura.controlador;

import org.springframework.web.bind.annotation.*;
import proyectoarquitecturacorte1.Proyectoarquitectura.modelo.Usuario;
import proyectoarquitecturacorte1.Proyectoarquitectura.servicios.UsuarioServicio;

import java.util.List;

@RestController
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;

    public UsuarioControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping("/usuario")
    public List<Usuario> obtenerUsuarios(){
        return usuarioServicio.obtenerUsuarios();
    }

    @PostMapping("/usuario")
    public int crearUsuarios(@RequestBody Usuario nuevoUsuario) {
        return usuarioServicio.crearUsuarios(nuevoUsuario);
    }

    @PutMapping("/usuario/{id}")
    public int actualizarUsuario(@PathVariable("id") int id, @RequestBody Usuario actualizarUsuario){
        return usuarioServicio.actualizarUsuario(id, actualizarUsuario);
    }

    @DeleteMapping("/usuario/{id}")
    public int eliminarUsuario(@PathVariable("id") int id){
        return usuarioServicio.eliminarUsuario(id);
    }



}
