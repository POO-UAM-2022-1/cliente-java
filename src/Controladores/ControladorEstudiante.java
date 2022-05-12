/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Estudiante;
import Servicios.Servicio;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author pipet
 */
public class ControladorEstudiante {

    Servicio miServicio;
    String subUrl;

    public ControladorEstudiante(String server, String subUrl) {
        this.miServicio = new Servicio(server);
        this.subUrl = subUrl;
    }

    public Estudiante crear(Estudiante nuevoEstudiante) {
        Estudiante respuesta = new Estudiante();
        try {
            String resultado = this.miServicio.POST(this.subUrl, nuevoEstudiante.toJSON());
            respuesta=reArmar(resultado);
        } catch (Exception e) {
            System.out.println("Error " + e);
            respuesta = null;
        }
        return respuesta;
    }

    public Estudiante buscarPorCedula(String cedula) {
        Estudiante respuesta = new Estudiante();
        try {
            String endPoint = this.subUrl + "/cedula/" + cedula;
            String resultado = this.miServicio.GET(endPoint);
            respuesta=reArmar(resultado);
        } catch (Exception e) {
            System.out.println("Error " + e);
            respuesta = null;
        }
        return respuesta;
    }
    public void eliminar(String id){
        String endPoint=this.subUrl+"/"+id;
        this.miServicio.DELETE(endPoint);
    }

    public Estudiante reArmar(String jsonString) {
        Estudiante nuevoEstudiante = new Estudiante();
        try {
            JSONParser parser = new JSONParser();
            JSONObject estudianteJSON = (JSONObject) parser.parse(jsonString);
            nuevoEstudiante.setId((String) estudianteJSON.get("_id"));
            nuevoEstudiante.setNombre((String) estudianteJSON.get("nombre"));
            nuevoEstudiante.setCedula((String) estudianteJSON.get("cedula"));
            nuevoEstudiante.setEmail((String) estudianteJSON.get("email"));
            nuevoEstudiante.setApellido((String) estudianteJSON.get("apellido"));
            nuevoEstudiante.setInicialesDepartamento((String) estudianteJSON.get("inicialesDepartamento"));
            nuevoEstudiante.setMunicipioResidencia((String) estudianteJSON.get("municipioResidencia"));
        } catch (Exception e) {
            nuevoEstudiante=null;
        }
        return nuevoEstudiante;
    }
}
