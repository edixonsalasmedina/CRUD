package com.crudv2.crudSpring.service;
import java.util.List;
import com.crudv2.crudSpring.entity.Imagen;

public interface IImagenService {
    public List<Imagen> listar();
    public Imagen listImagenId(String id);
    public Imagen agregar(Imagen I);
    public Imagen editar(Imagen I);
    public int delete(Imagen imagen);
    public Imagen listImagenIdPersona(String id);
}
