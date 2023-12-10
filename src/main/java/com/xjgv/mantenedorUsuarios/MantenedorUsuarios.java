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
                        "Selecciones una operacion", "Mantenedor de uduario", JOptionPane.INFORMATION_MESSAGE,
                        null, opArreglo, opArreglo[0]);

                if (opcion == null){
                    JOptionPane.showMessageDialog(null, "Debes seleccionar una opcion ");
                } else {
                    opcionIndice = operaciones.get(opcion.toString());
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
