package com.crudv2.crudSpring.repository;

import com.crudv2.crudSpring.entity.Imagen;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;



public interface ImagenRepository  extends MongoRepository<Imagen, String> {
    @Query("{id:'?0'}")
    Imagen findItemById(String id);
    @Query("{idPersona:'?0'}")
    Imagen findItemByIdPersona(String id);

    //@Query(value="{url:'?0'}", fields="{'idPersona' : 1, 'idClodinary' : 1}")
    @Query(value="{imagen:'?0'}")
    List<Imagen> findAll(String url);

    public long count();
    int deleteImagenByIdPersona(String idPersona);
    Long deleteImagenById(String id);




}
