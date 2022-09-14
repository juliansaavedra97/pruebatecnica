package com.example.demo.controller;

import com.example.demo.Service.EmpleadoService;
import com.example.demo.models.EmpleadoModel;
import com.example.demo.models.SalidaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value="/empleados")
public class EmpleadoController {

    @Autowired  //Tomar lo qe hay en servicios y conectarlo con el contralador automaticamente
    EmpleadoService empleadoService; //Instancia

    @GetMapping()//Metod Get
    public ArrayList<EmpleadoModel> getEmpleado(){
        return empleadoService.ObtenerEmpleados();
    }
    /* @PostMapping()// Metodo Post
     public EmpleadoModel postEmpleado(@RequestBody EmpleadoModel empleadoModel){
         empleadoService.Guardar(empleadoModel);
         return empleadoModel;
     }*/
    @PostMapping()// Metodo Post
    public SalidaModel postEmpleado(@RequestBody EmpleadoModel empleadoModel){

        ArrayList<String>  edadEmpleado = empleadoService.edadEmpleado(empleadoModel.getFecha_Nacimiento());
        ArrayList<String> tiempoVinculacion = empleadoService.tiempoVinculacion(empleadoModel.getFecha_Vinculacion());
        Boolean edadMayor = empleadoService.mayorEdad(empleadoModel.getFecha_Nacimiento());
        Boolean camposValidos =empleadoService.atributosCompletos(empleadoModel);
        Boolean esValidoNacimiento = empleadoService.validarFecha(empleadoModel.getFecha_Nacimiento());
        Boolean esValidoVinculacion = empleadoService.validarFecha(empleadoModel.getFecha_Vinculacion());

        if(edadMayor && esValidoNacimiento && esValidoVinculacion && camposValidos ){
            SalidaModel salidaModel = new SalidaModel();
            salidaModel.setNombre(empleadoModel.getNombre());
            salidaModel.setApellidos(empleadoModel.getApellidos());
            salidaModel.setTipo_Documento(empleadoModel.getTipo_Documento());
            salidaModel.setNumero_Documento(empleadoModel.getNumero_Documento());
            salidaModel.setFecha_Nacimiento(empleadoModel.getFecha_Nacimiento());
            salidaModel.setFecha_Vinculacion(empleadoModel.getFecha_Vinculacion());
            salidaModel.setCargo(empleadoModel.getCargo());
            salidaModel.setSalario(empleadoModel.getSalario());
            salidaModel.setTiempo_Vinculacion(tiempoVinculacion.get(0)+" Años "+tiempoVinculacion.get(1)+" Meses ");
            salidaModel.setEdad_Empleado(edadEmpleado.get(0)+" Años "+edadEmpleado.get(1)+" Meses ");//+edadEmpleado.get(2)+" Dias ");
            empleadoService.Guardar(empleadoModel);
            salidaModel.setId(empleadoModel.getId());
            return salidaModel;
        }
        return null;
    }
}