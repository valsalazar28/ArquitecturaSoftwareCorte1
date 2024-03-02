package proyectoarquitecturacorte1.Proyectoarquitectura.repositorio;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import proyectoarquitecturacorte1.Proyectoarquitectura.modelo.HistoriaUsuario;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class HistoriaUsuarioRepositorio {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insert;

    private final UserStoryMapper mapper = new UserStoryMapper();

    private final JdbcTemplate template;
    private final String table = "historia_usuario";


    public HistoriaUsuarioRepositorio(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                                      DataSource dataSource, JdbcTemplate template) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.insert = new SimpleJdbcInsert(dataSource).withTableName(table).usingGeneratedKeyColumns("id");
        this.template = template;
    }

    public List<HistoriaUsuario> obtenerHistoriasUsuario(int id_proyecto) {
        String sql = "select * from " + table + " where proyecto_id = " + id_proyecto;
        return namedParameterJdbcTemplate.query(sql, mapper);
    }

    public String crearHistoriasUsuario(HistoriaUsuario historiaUsuarioNueva, int id_usuario) {
        if(tipoUsuario(id_usuario) !=1){
            return "No tienes permiso para crear historias de usuario";
        }

        insert.execute(new MapSqlParameterSource()
                .addValue("detalles",historiaUsuarioNueva.detalles)
                .addValue("criterios_aceptacion", historiaUsuarioNueva.criteriosAceptacion)
                .addValue("usuario_historia_id", historiaUsuarioNueva.idHistoriaUsuario)
                .addValue("estado_id", historiaUsuarioNueva.estado)
                .addValue("proyecto_id", historiaUsuarioNueva.idProyecto));
        return "Historia de usuario creada";

    }

    public String actualizarHistoriasUsuario(int id_usuario, int id_historiaUsuario, HistoriaUsuario actualizarHistoriaUsuario){
        if (tipoUsuario(id_usuario) != 1){
            return "No tienes permiso para modificar las historias de usuario";
        }

        String sql = "update "+ table + " set " +
                "detalles = :detalles," +
                "criterios_aceptacion = :criterios_aceptacion," +
                "usuario_historia_id = :usuario_historia_id," +
                "estado_id = :estado_id," +
                "proyecto_id = :proyecto_id" +
                " where id = " + id_historiaUsuario;

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("detalles", actualizarHistoriaUsuario.detalles)
                .addValue("criterios_aceptacion", actualizarHistoriaUsuario.criteriosAceptacion)
                .addValue("usuario_historia_id", actualizarHistoriaUsuario.idHistoriaUsuario)
                .addValue("estado_id", actualizarHistoriaUsuario.estado)
                .addValue("proyecto_id", actualizarHistoriaUsuario.idProyecto);
        namedParameterJdbcTemplate.update(sql,parameters);

        return"Se ha modificado la historia de usuario";
    }

    public String eliminarHistoriasUsuario(int id_usuario, int id_historiaUsuario) {
        if (tipoUsuario(id_usuario) != 1){
            return "No tienes permiso para eliminar las historias de usuario";
        }else if(estadoHistoriaUsuario(id_historiaUsuario) != 3){
            return "La hisotira de usuario no se ha finalizado";
        }

        String sql = "delete from " + table + " where id = ?";
        template.update(sql,id_historiaUsuario);
        return "Se ha elimiando la historia de usuario";
    }

    public int tipoUsuario(int id){
        String sql = "select tipo_usuario_id from usuario where id = ?";
        return template.queryForObject(sql,Integer.class,id);
    }

    public int estadoHistoriaUsuario(int id){
        String sql = "select estado_id from historia_usuario where id = ?";
        return template.queryForObject(sql,Integer.class,id);
    }


    private static class UserStoryMapper implements RowMapper<HistoriaUsuario>{
        @Override
        public HistoriaUsuario mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String detalles = rs.getString("detalles");
            String criteriosAceptacion = rs.getString("criterios_aceptacion");
            int hsitoriaUsuario = rs.getInt("usuario_historia_id");
            int estado = rs.getInt("estado_id");
            int proyecto = rs.getInt("proyecto_id");

            return new HistoriaUsuario(id, detalles, criteriosAceptacion, hsitoriaUsuario, estado, proyecto);
        }

    }
}


