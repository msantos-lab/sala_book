package com.salabook.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.salabook.server.entities.Sala;
import com.salabook.server.repositories.SalaRepository;
import com.salabook.server.services.exceptions.DatabaseException;
import com.salabook.server.services.exceptions.ResourceNotFoundException;

@Service
public class SalaServices {

    @Autowired
    private SalaRepository repository;

    public List<Sala> findAll(){
        return repository.findAll();
    }

    public Sala findById(Long id){
        Optional<Sala> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Sala insert(Sala obj){
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

    public Sala update(Long id, Sala obj){
        try {
            Sala entity =  repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (Exception e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Sala entity, Sala obj){
        entity.setNome(obj.getNome());
        entity.setCapacidade(obj.getCapacidade());
        entity.setStatus(obj.getStatus());
    }


}
