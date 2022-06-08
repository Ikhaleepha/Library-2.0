package com.mam.io.Library20.repository;

import com.mam.io.Library20.entity.Identifiable;


import java.util.List;
import java.util.Optional;

public abstract class Repository<T extends Identifiable> {

    private List<T> entityList;

    public Repository(List<T> entityList){
        this.entityList = entityList;
    }

    public List<T> getAllEntities() {
        return entityList;
    }

    public Optional<T> exist(String id){

        for(T t: entityList){


            if(t.getId().equalsIgnoreCase(id)){

                return Optional.of(t);
            }
        }

        return Optional.empty();
    }

    public boolean existById(String id){

        if(exist(id).isPresent()){
            return true;
        }

        return false;
    }


    public void deleteById(String id){

    }
}
