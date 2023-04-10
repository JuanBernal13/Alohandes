package uniandes.isis2304.alohandes.persistencia.Alohandes;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.List;
import uniandes.isis2304.alohandes.negocio.Alohandes.Servicio;

public class SQLServicio {
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

    public SQLServicio(PersistenciaAlohandes pa) {
        this.pa = pa;
    }

    public long adicionarServicio(PersistenceManager pm, long id, String nombreServicio) {
        Query q = pm.newQuery(SQL,
                "INSERT INTO " + pa.darTablaServicio()
                        + "(id, nombre_servicio) values (?, ?)");
        q.setParameters(id, nombreServicio);
        return (long) q.executeUnique();
    }

    public long eliminarServicioPorId(PersistenceManager pm, long id) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaServicio() + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
    }

    public Servicio darServicioPorId(PersistenceManager pm, long id) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaServicio() + " WHERE id = ?");
        q.setResultClass(Servicio.class);
        q.setParameters(id);
        return (Servicio) q.executeUnique();
    }

    public List<Servicio> darServicios(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaServicio());
        q.setResultClass(Servicio.class);
        return (List<Servicio>) q.executeList();
    }
}

