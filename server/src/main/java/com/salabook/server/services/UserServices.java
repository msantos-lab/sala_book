package com.salabook.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.salabook.server.entities.User;
import com.salabook.server.repositories.UserRepository;
import com.salabook.server.services.exceptions.DatabaseException;
import com.salabook.server.services.exceptions.ResourceNotFoundException;

@Service
public class UserServices {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(Long id){
        Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User obj){
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

    public User update(Long id, User obj){
        try {
            User entity =  repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (Exception e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(User entity, User obj){
        entity.setNome(obj.getNome());
        entity.setEmail(obj.getEmail());
        entity.setNivelUser(obj.getNivelUser());
        entity.setSenha(obj.getSenha());
    }


}
