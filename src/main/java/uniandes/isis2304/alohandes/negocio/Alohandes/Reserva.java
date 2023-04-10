package uniandes.isis2304.alohandes.negocio.Alohandes;

import java.util.Date;

public class Reserva implements VOReserva {
    /* ****************************************************************
     * 			Atributos
     *****************************************************************/
    private long id;
    private long userId;
    private long alojamientoId;
    private Date fechaInicial;
    private Date fechaFinal;
    private int costo;

    /* ****************************************************************
     * 			Métodos
     *****************************************************************/
    public Reserva() {
        this.id = 0;
        this.userId = 0;
        this.alojamientoId = 0;
        this.fechaInicial = null;
        this.fechaFinal = null;
        this.costo = 0;
    }

    public Reserva(long id, long userId, long alojamientoId, Date fechaInicial, Date fechaFinal, int costo) {
        this.id = id;
        this.userId = userId;
        this.alojamientoId = alojamientoId;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.costo = costo;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public long getAlojamientoId() {
        return alojamientoId;
    }

    public void setAlojamientoId(long alojamientoId) {
        this.alojamientoId = alojamientoId;
    }

    @Override
    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    @Override
    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    @Override
    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "Reserva [id=" + id + ", userId=" + userId + ", alojamientoId=" + alojamientoId
                + ", fechaInicial=" + fechaInicial + ", fechaFinal=" + fechaFinal + ", costo=" + costo + "]";
    }
}
