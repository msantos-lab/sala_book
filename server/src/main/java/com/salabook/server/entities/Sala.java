package com.salabook.server.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;


import com.salabook.server.entities.enums.StatusSala;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_sala")
public class Sala implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String nome;
    public Integer capacidade;
    public Integer status;

     @OneToMany(mappedBy = "id.sala")
    private Set<ReservaItem> itens = new HashSet<>();

     public Sala() {
    }

    public Sala(Long id, String nome, Integer capacidade, StatusSala status) {
        this.id = id;
        this.nome = nome;
        this.capacidade = capacidade;
        setStatus(status);
    }

    public List<Reserva> getReservas(){
        List<Reserva> lista = new ArrayList<>();
        for(ReservaItem x : itens){
            lista.add(x.getReserva());
        }
        return lista;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public StatusSala getStatus() {
        return StatusSala.valueOf(status);
    }

    public void setStatus(StatusSala status) {
        if (status != null) {
            this.status = status.getCode();
        }
    }

    public Set<ReservaItem> getItens() {
        return itens;
    }

    public void setItens(Set<ReservaItem> itens) {
        this.itens = itens;
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
        Sala other = (Sala) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Sala [id=" + id + ", nome=" + nome + ", capacidade=" + capacidade + ", status=" + status + "]";
    }
}