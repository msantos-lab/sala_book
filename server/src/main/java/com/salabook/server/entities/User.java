package com.salabook.server.entities;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salabook.server.entities.enums.NivelUser;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String nome;
    public String email;
    public String senha;
    public Integer nivelUser;

    public User() {
    }

    public User(Long id, String nome, String email, String senha, NivelUser nivelUser) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        setNivelUser(nivelUser);
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Reserva> reserva = new ArrayList<>();

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public NivelUser getNivelUser() {
        return NivelUser.valueOf(nivelUser);
    }

    public void setNivelUser(NivelUser nivelUser) {
        if (nivelUser != null) {
            this.nivelUser = nivelUser.getCode();
        }
    }


    public List<Reserva> getReservas() {
		return reserva;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reserva = reservas;
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
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", nivelUser="
                + nivelUser + "]";
    }

}
