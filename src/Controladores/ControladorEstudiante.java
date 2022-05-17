/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Estudiante;
import Servicios.Servicio;
import java.util.LinkedList;
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
            respuesta = procesarJson(resultado);
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
            respuesta = procesarJson(resultado);
        } catch (Exception e) {
            System.out.println("Error " + e);
            respuesta = null;
        }
        return respuesta;
    }

    public void eliminar(String id) {
        String endPoint = this.subUrl + "/" + id;
        this.miServicio.DELETE(endPoint);
    }

    public Estudiante procesarJson(String jsonString) {
        Estudiante nuevoEstudiante = new Estudiante();
        try {
            JSONParser parser = new JSONParser();
            JSONObject estudianteJSON = (JSONObject) parser.parse(jsonString);
            nuevoEstudiante=reArmar(estudianteJSON);
        } catch (Exception e) {
            nuevoEstudiante = null;
        }
        return nuevoEstudiante;
    }

    public Estudiante reArmar(JSONObject objetoJson) {
        Estudiante nuevoEstudiante=new Estudiante();
        nuevoEstudiante.setId((String) objetoJson.get("_id"));
        nuevoEstudiante.setNombre((String) objetoJson.get("nombre"));
        nuevoEstudiante.setCedula((String) objetoJson.get("cedula"));
        nuevoEstudiante.setEmail((String) objetoJson.get("email"));
        nuevoEstudiante.setApellido((String) objetoJson.get("apellido"));
        nuevoEstudiante.setInicialesDepartamento((String) objetoJson.get("inicialesDepartamento"));
        nuevoEstudiante.setMunicipioResidencia((String) objetoJson.get("municipioResidencia"));
        return nuevoEstudiante;
    }

    public LinkedList<Estudiante> listar() {
        LinkedList<Estudiante> respuesta = new LinkedList<>();
        try {
            String endPoint = this.subUrl;
            String resultado = this.miServicio.GET(endPoint);
            JSONParser parser = new JSONParser();
            JSONArray estudiantesJSON = (JSONArray) parser.parse(resultado);
            for (Object actual : estudiantesJSON) {
                JSONObject estudianteJSON= (JSONObject) actual;
                Estudiante nuevoEstudiante=new Estudiante();
                nuevoEstudiante=reArmar(estudianteJSON);
                respuesta.add(nuevoEstudiante);
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
            respuesta = null;
        }
        return respuesta;
    }
    public Estudiante actualizar(Estudiante actualizado){
        Estudiante respuesta=new Estudiante();
        try {
            String endPoint=this.subUrl+"/"+actualizado.getId();
            String resultado = this.miServicio.PUT(endPoint,actualizado.toJSON());
            respuesta = procesarJson(resultado);
        } catch (Exception e) {
            System.out.println("Error " + e);
            respuesta = null;
        }
        return respuesta;
    }

}
