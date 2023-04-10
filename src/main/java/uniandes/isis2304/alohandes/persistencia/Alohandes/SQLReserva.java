package uniandes.isis2304.alohandes.persistencia.Alohandes;

import java.util.List;
import java.util.Date;
import uniandes.isis2304.alohandes.negocio.Alohandes.Reserva;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

class SQLReserva {
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

    public SQLReserva(PersistenciaAlohandes pa) {
        this.pa = pa;
    }

    public long adicionarReserva(PersistenceManager pm, long idReserva, long idUsuario, long idPropuesta, Date fechaInicio, Date fechaFin, int costo) {
        // Verificar si la propuesta está libre para las fechas a reservar
        Query q1 = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaReserva() + " WHERE id_propuesta = ? AND ((? BETWEEN fecha_inicio AND fecha_fin) OR (? BETWEEN fecha_inicio AND fecha_fin))");
        q1.setParameters(idPropuesta, fechaInicio, fechaFin);
        List<Reserva> reservas = (List<Reserva>) q1.executeList();
        
        if (reservas.isEmpty()) {
            // La propuesta está libre para las fechas a reservar
            Query q2 = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaReserva() + "(id, id_usuario, id_propuesta, fecha_inicio, fecha_fin, costo) values (?, ?, ?, ?, ?, ?)");
            q2.setParameters(idReserva, idUsuario, idPropuesta, fechaInicio, fechaFin, costo);
            return (long) q2.executeUnique();
        } else {
            // La propuesta no está libre para las fechas a reservar
            return 0;
        }
    }
    

    public long eliminarReservaPorId(PersistenceManager pm, long idReserva) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaReserva() + " WHERE id = ?");
        q.setParameters(idReserva);
        return (long) q.executeUnique();
    }

    public Reserva darReservaPorId(PersistenceManager pm, long idReserva) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaReserva() + " WHERE id = ?");
        q.setResultClass(Reserva.class);
        q.setParameters(idReserva);
        return (Reserva) q.executeUnique();
    }

    public List<Reserva> darReservas(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaReserva());
        q.setResultClass(Reserva.class);
        return (List<Reserva>) q.executeList();
    }

    public long actualizarReservaFechaInicio(PersistenceManager pm, long idReserva, Date nuevaFechaInicio) {
        Query q = pm.newQuery(SQL, "UPDATE " + pa.darTablaReserva() + " SET fecha_inicio = ? WHERE id = ?");
        q.setParameters(nuevaFechaInicio, idReserva);
        return (long) q.executeUnique();
    }

    public long actualizarReservaFechaFin(PersistenceManager pm, long idReserva, Date nuevaFechaFin) {
        Query q = pm.newQuery(SQL, "UPDATE " + pa.darTablaReserva() + " SET fecha_fin = ? WHERE id = ?");
        q.setParameters(nuevaFechaFin, idReserva);
        return (long) q.executeUnique();
    }

    public long actualizarReservaPropuesta(PersistenceManager pm, long idReserva, long nuevoIdPropuesta) {
        Query q = pm.newQuery(SQL, "UPDATE " + pa.darTablaReserva() + " SET id_propuesta = ? WHERE id = ?");
        q.setParameters(nuevoIdPropuesta, idReserva);
        return (long) q.executeUnique();
    }

    public long actualizarReservaUsuario(PersistenceManager pm, long idReserva, long nuevoIdUsuario) {
        Query q = pm.newQuery(SQL, "UPDATE " + pa.darTablaReserva() + " SET id_usuario = ? WHERE id = ?");
        q.setParameters(nuevoIdUsuario, idReserva);
        return (long) q.executeUnique();
}
public List<Reserva> darReservasPorFechas(PersistenceManager pm, Date fechaInicio, Date fechaFin) {
    Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaReserva() + " WHERE fecha_inicio >= ? AND fecha_fin <= ?");
    q.setResultClass(Reserva.class);
    q.setParameters(fechaInicio, fechaFin);
    return (List<Reserva>) q.executeList();
}

}

