package proyectoarquitecturacorte1.Proyectoarquitectura.modelo;

public class HistoriaUsuario {

    public int id;

    public String detalles;

    public String criteriosAceptacion;

    public int idHistoriaUsuario;

    public int estado;

    public int idProyecto;

    public HistoriaUsuario(int id, String detalles, String criteriosAceptacion, int idHistoriaUsuario, int estado, int idProyecto) {
        this.id = id;
        this.detalles = detalles;
        this.criteriosAceptacion = criteriosAceptacion;
        this.idHistoriaUsuario = idHistoriaUsuario;
        this.estado = estado;
        this.idProyecto = idProyecto;
    }
}
