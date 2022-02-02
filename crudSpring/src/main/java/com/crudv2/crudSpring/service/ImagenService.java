package com.crudv2.crudSpring.service;

import com.crudv2.crudSpring.entity.Imagen;
import com.crudv2.crudSpring.repository.ImagenRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ImagenService implements IImagenService{
    @Autowired
    ImagenRepository imgRepo;
    @Override
    public List<Imagen> listar(){
        List<Imagen> res = imgRepo.findAll();
        return res;
    }

    @Override
    public Imagen listImagenId(String id) {
        Imagen res = imgRepo.findItemById(id);
        return res;
    }
    @Override
    public Imagen listImagenIdPersona(String id) {
        Imagen res = imgRepo.findItemByIdPersona(id);
        return res;
    }
    @Override
    public Imagen agregar(Imagen I) {
        return imgRepo.save(I);
    }
    @Override
    public Imagen editar(Imagen I) {
        //Imagen I2 = listImagenIdPersona(I.getIdPersona());
        //imgRepo.deleteById(I2.getId());

        return imgRepo.save(I);
    }
    @Override
    public int delete(Imagen imagen) {
        //Imagen I = listImagenIdPersona(id);
        int res = imgRepo.deleteImagenByIdPersona(imagen.getIdPersona());
        return res;


    }
}





















