package proyectoarquitecturacorte1.Proyectoarquitectura.modelo;

public class Tarea {

    public int id;
    public String descripcion;
    public int estado;
    public int idUsuarioTarea;
    public int idHistoriaUsuario;

    public Tarea(int id, String descripcion, int estado, int idUsuarioTarea, int idHistoriaUsuario) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = estado;
        this.idUsuarioTarea = idUsuarioTarea;
        this.idHistoriaUsuario = idHistoriaUsuario;
    }
}
