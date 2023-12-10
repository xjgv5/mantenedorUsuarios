package com.xjgv.mantenedorUsuarios;

import com.xjgv.mantenedorUsuarios.modelo.Usuario;
import com.xjgv.mantenedorUsuarios.repositorio.Repositorio;
import com.xjgv.mantenedorUsuarios.repositorio.UsuarioRepositorioImpl;
import com.xjgv.mantenedorUsuarios.util.ConexionBaseDatos;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MantenedorUsuarios {
    public static void main(String[] args) {
        try(Connection conexion = ConexionBaseDatos.obtenerInstancia()) {
            Repositorio<Usuario> repositorio = new UsuarioRepositorioImpl();
            int opcionIndice = 0;
            do{
                Map<String, Integer> operaciones = new HashMap<>();
                operaciones.put("Actualizar", 1);
                operaciones.put("Eliminar", 2);
                operaciones.put("Agregar", 3);
                operaciones.put("Listar", 4);
                operaciones.put("Salir", 5);

                Object[] opArreglo = operaciones.keySet().toArray();
                Object opcion = JOptionPane.showInputDialog(null,
                        "Selecciones una operacion", "Mantenedor de usuario", JOptionPane.INFORMATION_MESSAGE,
                        null, opArreglo, opArreglo[0]);

                if (opcion == null){
                    JOptionPane.showMessageDialog(null, "Debes seleccionar una opcion ");
                } else {
                    opcionIndice = operaciones.get(opcion.toString());
                    Long id;
                    String username;
                    String password;
                    String email;

                    switch (opcionIndice){
                        case  1 -> {
                            id = Long.valueOf(JOptionPane.showInputDialog(null, "Ingresa el id del usuario a actualizar"));
                            Usuario usuario = repositorio.porID(id);
                            if (usuario != null){
                                username = JOptionPane.showInputDialog(null, "Ingresa el usuario : ", usuario.getUsuario());
                                password = JOptionPane.showInputDialog(null, "Ingresa la nueva contraseña : ", usuario.getContrasena());
                                email = JOptionPane.showInputDialog(null, "Ingresa el nuevo email : ", usuario.getCorreo());

                                usuario.setUsuario(username);
                                usuario.setContrasena(password);
                                usuario.setCorreo(email);

                                repositorio.guardar(usuario);

                                JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente ! ");
                            } else {
                                JOptionPane.showMessageDialog(null, "No existe el id del usuario en la base de datos ! ");
                            }

                        }
                        case 2->{
                            id = Long.valueOf(JOptionPane.showInputDialog(null, "Ingresa el id del usuario a eliminar"));
                            repositorio.eliminar(id);
                            JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
                        }
                        case 3->{
                            JOptionPane.showMessageDialog(null, "Se va a crear un nuevo usuario");
                            username = JOptionPane.showInputDialog(null, "Igresa el nombre de usuario");
                            password = JOptionPane.showInputDialog(null, "Inresa la contraseña");
                            email = JOptionPane.showInputDialog(null, "Ingresa el correo");

                            Usuario usuario = new Usuario();
                            usuario.setUsuario(username);
                            usuario.setContrasena(password);
                            usuario.setCorreo(email);

                            repositorio.guardar(usuario);
                            JOptionPane.showMessageDialog(null, "Usuario creado correctamente ! ");
                        }
                        case 4-> {
                            repositorio.listar().forEach(System.out::println);
                        }
                    }
                }

            } while (opcionIndice != 5);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
