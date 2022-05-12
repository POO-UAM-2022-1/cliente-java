/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import org.json.simple.JSONObject;

/**
 *
 * @author pipet
 */
public class Estudiante extends Persona{
    String apellido;
    String inicialesDepartamento;
    String municipioResidencia;

    public Estudiante() {
    }

    

    public Estudiante(String cedula, String nombre, String email, String apellido, String inicialesDepartamento, String municipioResidencia) {
        super(cedula, nombre, email);
        this.apellido = apellido;
        this.inicialesDepartamento = inicialesDepartamento;
        this.municipioResidencia = municipioResidencia;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getInicialesDepartamento() {
        return inicialesDepartamento;
    }

    public void setInicialesDepartamento(String inicialesDepartamento) {
        this.inicialesDepartamento = inicialesDepartamento;
    }

    public String getMunicipioResidencia() {
        return municipioResidencia;
    }

    public void setMunicipioResidencia(String municipioResidencia) {
        this.municipioResidencia = municipioResidencia;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject respuesta=new JSONObject();
        respuesta.put("cedula", this.getCedula());
        respuesta.put("nombre", this.getNombre());
        respuesta.put("email", this.getEmail());
        respuesta.put("apellido", this.getApellido());
        respuesta.put("inicialesDepartamento", this.getInicialesDepartamento());
        respuesta.put("municipioResidencia", this.getMunicipioResidencia());
        
        return respuesta;
    }
}

