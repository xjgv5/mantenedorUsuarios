package com.xjgv.mantenedorUsuarios.repositorio;

import com.xjgv.mantenedorUsuarios.modelo.Usuario;
import com.xjgv.mantenedorUsuarios.util.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.xjgv.mantenedorUsuarios.util.ConexionBaseDatos.obtenerInstancia;

public class UsuarioRepositorioImpl implements Repositorio<Usuario>{

    private Connection obtenerConexion() throws SQLException {
        return ConexionBaseDatos.obtenerInstancia();

    }

    @Override
    public List<Usuario> listar() {

        List<Usuario> listaUsuarios = new ArrayList<>(); //una lista de usuarios
        try(Statement consulta = obtenerConexion().createStatement();
            ResultSet resultadoConsulta = consulta.executeQuery("select * from usuarios")){
            while (resultadoConsulta.next()){
                Usuario usuario = crearUsuario(resultadoConsulta);
                listaUsuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;
    }

    @Override
    public Usuario porID(Long id) {
        Usuario usuario = null;
        try(PreparedStatement sentencia = obtenerConexion().
                prepareStatement("select * from usuarios where id =?")){
            sentencia.setLong(1, id);
            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    usuario = crearUsuario(resultado);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    @Override
    public void guardar(Usuario usuario) {
        String sql;
        if (usuario.getId() != null && usuario.getId() > 0){
            sql = "update usuarios set usuario=? contrasena=?, email=? where id=?";

        }else {
            sql = "insert into usuarios(usuario, contrasena email) values(?,?,?)";

        }
        try {
            PreparedStatement sentencia = obtenerConexion().prepareStatement(sql);
            sentencia.setString(1, usuario.getUsuario());
            sentencia.setString(2, usuario.getContrasena());
            sentencia.setString(3, usuario.getCorreo());

            sentencia.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void eliminar(Long id) {
        try(PreparedStatement sentencia = obtenerConexion()
                .prepareStatement("delete from usuarios where id = ?")){
            sentencia.setLong(1, id);
            sentencia.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario crearUsuario(ResultSet resultadoConsulta) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setUsuario(resultadoConsulta.getString("usuario"));
        usuario.setContrasena(resultadoConsulta.getString("contrasena"));
        usuario.setContrasena(resultadoConsulta.getString("email"));
        return usuario;

    }
}
