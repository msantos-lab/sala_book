package com.salabook.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.salabook.server.entities.Reserva;
import com.salabook.server.repositories.ReservaRepository;
import com.salabook.server.services.exceptions.DatabaseException;
import com.salabook.server.services.exceptions.ResourceNotFoundException;

@Service
public class ReservaServices {

    @Autowired
    private ReservaRepository repository;

    public List<Reserva> findAll(){
        return repository.findAll();
    }

    public Reserva findById(Long id){
        Optional<Reserva> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Reserva insert(Reserva obj){
        return repository.save(obj);
    }

    public void delete(Long id){
        try{
            repository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
        catch(DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public Reserva update(Long id, Reserva obj){
        try {
            Reserva entity =  repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (Exception e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Reserva entity, Reserva obj){
        entity.setMomento(obj.getMomento());
    }


}
