package proyectoarquitecturacorte1.Proyectoarquitectura.servicios;


import org.springframework.stereotype.Service;
import proyectoarquitecturacorte1.Proyectoarquitectura.modelo.HistoriaUsuario;
import proyectoarquitecturacorte1.Proyectoarquitectura.repositorio.HistoriaUsuarioRepositorio;
import java.util.List;

@Service
public class HistoriaUsuarioServicio {

    private final HistoriaUsuarioRepositorio repositorio;

    public HistoriaUsuarioServicio(HistoriaUsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<HistoriaUsuario> obtenerHistoriasUsuario(int id_proyecto) {
        return repositorio.obtenerHistoriasUsuario(id_proyecto);
    }

    public String crearHistoriasUsuario(HistoriaUsuario historiaUsuarioNueva, int id_usuario) {
        return repositorio.crearHistoriasUsuario(historiaUsuarioNueva, id_usuario);
    }

    public String actualizarHistoriasUsuario(int id_usuario, int id_proyecto, HistoriaUsuario actualizarHistoriaUsuario) {
        return repositorio.actualizarHistoriasUsuario(id_usuario, id_proyecto, actualizarHistoriaUsuario);
    }

    public String eliminarHistoriasUsuario(int id_usuario, int id_historiaUsuario) {
        return repositorio.eliminarHistoriasUsuario(id_usuario, id_historiaUsuario);
    }
}
