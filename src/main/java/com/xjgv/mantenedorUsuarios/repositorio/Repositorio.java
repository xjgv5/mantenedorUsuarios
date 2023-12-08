package com.xjgv.mantenedorUsuarios.repositorio;

import java.util.List;

public interface Repositorio<T> {
    List<T> listar();

    T porID(Long id);

    void guardar(T t);

    void eliminar(Long id);
}
