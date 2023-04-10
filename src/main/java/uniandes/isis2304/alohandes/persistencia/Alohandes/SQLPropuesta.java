package uniandes.isis2304.alohandes.persistencia.Alohandes;

import java.util.List;
import uniandes.isis2304.alohandes.negocio.Alohandes.Propuesta;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

class SQLPropuesta {
    /* ****************************************************************
     * 			Constantes
     *****************************************************************/
    private final static String SQL = PersistenciaAlohandes.SQL;

    /* ****************************************************************
     * 			Atributos
     *****************************************************************/
    private PersistenciaAlohandes pa;

    /* ****************************************************************
     * 			Métodos
     *****************************************************************/

    public SQLPropuesta(PersistenciaAlohandes pa) {
        this.pa = pa;
    }

    public long adicionarPropuesta(PersistenceManager pm, long idPropuesta, String titulo, String descripcion, long idAlojamiento) {
        Query q = pm.newQuery(SQL,
                "INSERT INTO " + pa.darTablaPropuesta()
                        + "(id, titulo, descripcion, id_alojamiento) values (?, ?, ?, ?)");
        q.setParameters(idPropuesta, titulo, descripcion, idAlojamiento);
        return (long) q.executeUnique();
    }

    public long eliminarPropuestaPorId(PersistenceManager pm, long idPropuesta) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaPropuesta() + " WHERE id = ?");
        q.setParameters(idPropuesta);
        return (long) q.executeUnique();
    }

    public Propuesta darPropuestaPorId(PersistenceManager pm, long idPropuesta) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaPropuesta() + " WHERE id = ?");
        q.setResultClass(Propuesta.class);
        q.setParameters(idPropuesta);
        return (Propuesta) q.executeUnique();
    }

    public List<Propuesta> darPropuestas(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaPropuesta());
        q.setResultClass(Propuesta.class);
        return (List<Propuesta>) q.executeList();
    }
    public long actualizarPropuestaTitulo(PersistenceManager pm, long idPropuesta, String nuevoTitulo) {
        Query q = pm.newQuery(SQL, "UPDATE " + pa.darTablaPropuesta() + " SET titulo = ? WHERE id = ?");
        q.setParameters(nuevoTitulo, idPropuesta);
        return (long) q.executeUnique();
    }

    public long actualizarPropuestaDescripcion(PersistenceManager pm, long idPropuesta, String nuevaDescripcion) {
        Query q = pm.newQuery(SQL, "UPDATE " + pa.darTablaPropuesta() + " SET descripcion = ? WHERE id = ?");
        q.setParameters(nuevaDescripcion, idPropuesta);
        return (long) q.executeUnique();
    }

    public long actualizarPropuestaAlojamiento(PersistenceManager pm, long idPropuesta, long nuevoIdAlojamiento) {
        Query q = pm.newQuery(SQL, "UPDATE " + pa.darTablaPropuesta() + " SET id_alojamiento = ? WHERE id = ?");
        q.setParameters(nuevoIdAlojamiento, idPropuesta);
        return (long) q.executeUnique();
    }
}

