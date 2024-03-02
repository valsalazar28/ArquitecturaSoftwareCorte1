package proyectoarquitecturacorte1.Proyectoarquitectura.repositorio;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import proyectoarquitecturacorte1.Proyectoarquitectura.modelo.Tarea;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TareaRepositorio {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insert;

    private final TaskMapper mapper = new TaskMapper();

    private final JdbcTemplate template;
    private final String table = "tarea";


    public TareaRepositorio(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                            DataSource dataSource, JdbcTemplate template) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.insert = new SimpleJdbcInsert(dataSource).withTableName(table).usingGeneratedKeyColumns("id");
        this.template = template;
    }

    public List<Tarea> obtenerTareas(int id) {
        String sql = "select * from " + table + " where historia_usuario_id = "+ id;
        return namedParameterJdbcTemplate.query(sql,mapper);
    }

    public String crearTarea(int id_usuario, Tarea tareaNueva) {
        if(tipoUsuario(id_usuario) != 1){
            return "No tienes permisos para crear taraes";
        }

        insert.execute(new MapSqlParameterSource()
                .addValue("descripcion", tareaNueva.descripcion)
                .addValue("usuario_tarea_id", tareaNueva.idUsuarioTarea)
                .addValue("estado_tarea_id", tareaNueva.estado)
                .addValue("historia_usuario_id",tareaNueva.idHistoriaUsuario));

        return "Tarea creada";

    }

    public String actualizarTarea(int id_usuario, int id_tarea, Tarea tareaNueva) {
        if(tipoUsuario(id_usuario) != 1){
            return "No tienes permisos para crear taraes";
        }

        String sql = "update "+ table + " set " +
                "descripcion = :descripcion," +
                "usuario_tarea_id = :usuario_tarea_id," +
                "estado_tarea_id = :estado_tarea_id," +
                "historia_usuario_id = :historia_usuario_id" +
                " where id = " + id_tarea;

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("descripcion", tareaNueva.descripcion)
                .addValue("usuario_tarea_id", tareaNueva.idUsuarioTarea)
                .addValue("estado_tarea_id", tareaNueva.estado)
                .addValue("historia_usuario_id", tareaNueva.idHistoriaUsuario);
        namedParameterJdbcTemplate.update(sql,parameters);

        return "Se ha modificado la tarea";
    }

    public String borrarTarea(int id_usuario, int id_tarea) {
        if(tipoUsuario(id_usuario) != 1){
            return "No tienes permisos para crear taraes";
        }else if(estadoTarea(id_tarea) != 3){
            return "La tarea no se ha finalizado";
        }

        String sql = "delete from " + table + " where id = ?";
        template.update(sql,id_tarea);
        return "Se ha elimiando la tarea de usuario";
    }



    public int tipoUsuario(int id){
        String sql = "select tipo_usuario_id from usuario where id = ?";
        return template.queryForObject(sql,Integer.class,id);
    }

    public int estadoTarea(int id){
        String sql = "select estado_tarea_id from tarea where id = ?";
        return template.queryForObject(sql,Integer.class,id);
    }

    private static class TaskMapper implements RowMapper<Tarea>{

        @Override
        public Tarea mapRow(ResultSet rs, int rowNum) throws SQLException{
            int id = rs.getInt("id");
            String descripcion = rs.getString("descripcion");
            int idEstadoTarea = rs.getInt("estado_tarea_id");
            int idUsuarioTarea = rs.getInt("usuario_tarea_id");
            int idHistoriaUsuario = rs.getInt("historia_usuario_id");

            return new Tarea(id, descripcion, idEstadoTarea, idUsuarioTarea, idHistoriaUsuario);
        }
}
}
