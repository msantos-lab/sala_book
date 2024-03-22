package com.salabook.server.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salabook.server.entities.pk.ReservaItemPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_reserva_item")
public class ReservaItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ReservaItemPK id = new ReservaItemPK();

    private Date comeco;
    private Date fim;

    
    public ReservaItem() {
    }


    public ReservaItem(Reserva reserva, Sala sala, Date comeco, Date fim) {
        super();
        id.setReserva(reserva);
        id.setSala(sala);
        this.comeco = comeco;
        this.fim = fim;
    }

     @JsonIgnore //evitar o loop na requisicao
    public Reserva getReserva() {
        return id.getReserva();
    }

    public void setReserva(Reserva reserva) {
        id.setReserva(reserva);
    }


    public Sala getSala() {
        return id.getSala();
    }

    public void setSala(Sala sala) {
        id.setSala(sala);
    }


    public Date getComeco() {
        return comeco;
    }

    public void setComeco(Date comeco) {
        this.comeco = comeco;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ReservaItem other = (ReservaItem) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "ReservaItem [id=" + id + ", comeco=" + comeco + ", fim=" + fim + "]";
    }

}
