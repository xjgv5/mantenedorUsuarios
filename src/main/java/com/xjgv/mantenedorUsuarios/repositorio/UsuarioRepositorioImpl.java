package com.xjgv.mantenedorUsuarios.repositorio;

import com.xjgv.mantenedorUsuarios.modelo.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.xjgv.mantenedorUsuarios.util.ConexionBaseDatos.obtenerInstancia;

public class UsuarioRepositorioImpl implements Repositorio{

    @Override
    public List<Usuario> listar() {

        List<Usuario> listaUsuarios = new ArrayList<>(); //una lista de usuarios
        try(Statement consulta = obtenerInstancia().createStatement();
            ResultSet resultadoConsulta = consulta.executeQuery("select * from usuarios")){
            while (resultadoConsulta.next()){
                Usuario usuario = crearUsuario(resultadoConsulta);
                listaUsuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object porID(Long id) {
        return null;
    }

    @Override
    public void guardar(Object o) {

    }

    @Override
    public void eliminar(Long id) {

    }

    public Usuario crearUsuario(ResultSet resultadoConsulta) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setUsuario(resultadoConsulta.getString("usuario"));
        usuario.setContrasena(resultadoConsulta.getString("contrasena"));
        usuario.setContrasena(resultadoConsulta.getString("email"));
        return usuario;

    }
}
