package com.xjgv.mantenedorUsuarios.modelo;

public class Usuario {
    private Long id;
    private String usuario;
    private String contrasena;
    private String correo;

    public Usuario() {
    }

    public Usuario(Long id, String usuario, String contrasena, String correo) {
        this.id = id;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.correo = correo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return id +
                " | " + usuario +
                " | " + contrasena +
                " | " + correo;
    }
}
