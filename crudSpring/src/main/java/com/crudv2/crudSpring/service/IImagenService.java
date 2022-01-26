package com.crudv2.crudSpring.service;
import java.util.List;
import com.crudv2.crudSpring.entity.Imagen;

public interface IImagenService {
    public List<Imagen> listar();
    public Imagen listImagenId(String id);
    public void agregar(Imagen I);
    public void editar(Imagen I);
    public void delete(String id);
    public Imagen listImagenIdPersona(String id);
}
