package com.example.demo.Service;

import com.example.demo.models.EmpleadoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repositories.EmpleadoRepositories;

import java.text.SimpleDateFormat;
import java.util.ArrayList ;
import java.util.Calendar;
import java.util.Date;

@Service
public class EmpleadoService {
    @Autowired
    EmpleadoRepositories empleadoRepositories;
    public ArrayList<EmpleadoModel> ObtenerEmpleados() {
        return (ArrayList<EmpleadoModel>) empleadoRepositories.findAll(); /// Retornar toda la lista de la tabla
    }
    //Guardar
    public EmpleadoModel Guardar(EmpleadoModel empleadoModel) {
        return empleadoRepositories.save(empleadoModel); ///Guardar los datos
    }
    //Validar Fecha
    public Boolean validarFecha(String fechaEntrada) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date Objeto = format.parse(fechaEntrada);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    //Validar Campos Completos
    public Boolean atributosCompletos(EmpleadoModel empleadoModel) {
        if (empleadoModel.getNombre().equals("") ||
                empleadoModel.getApellidos().equals("") ||
                empleadoModel.getTipo_Documento().equals("") ||
                empleadoModel.getNumero_Documento().equals("") ||
                empleadoModel.getFecha_Vinculacion().equals("") ||
                empleadoModel.getFecha_Nacimiento().equals("") ||
                empleadoModel.getCargo().equals("") ||
                empleadoModel.getSalario().equals("")) {
            return false;
        }
        return true;
    }
    //Validar Mayor de Edad
    public Boolean mayorEdad(String fecha_Nacimiento) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());

        String matrizFecha[] = fecha_Nacimiento.split("-");

        if ((date.getYear() + 1900 - Integer.parseInt(matrizFecha[2]) > 18)) {
            return true;
        }
        else {
            if((date.getYear() + 1900 - Integer.parseInt(matrizFecha[2]) == 18)){
                if ((date.getMonth() + 1 >= Integer.parseInt(matrizFecha[1]))) {
                    //"Es Mayor de Edad por mes";
                    if ((date.getDate() >= Integer.parseInt(matrizFecha[0]))){
                        //"Es Mayor de Edad por dia";
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else{
                    return false;
                }
            }
            return false;
        }
    }
    //Tiempo de Vinculacion
    public ArrayList<String> tiempoVinculacion (String fecha_Vinculacion){
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        String matrizFecha[] = fecha_Vinculacion.split("-");

        ArrayList<String> vinculacionTime = new ArrayList<String>(); //Almacenar año y fecha de resultado

        if((date.getMonth()+1)<Integer.parseInt(matrizFecha[1])) {
            int temp=((date.getYear()+1900)-Integer.parseInt(matrizFecha[2])-1);
            vinculacionTime.add(String.valueOf(temp));
            int temp1 = (12 - Integer.parseInt(matrizFecha[1]) + (date.getMonth() + 1));
            if(temp1==12){
                temp1=0;
            }
            vinculacionTime.add(String.valueOf(temp1));
        }
        else{
            int temp=((date.getYear()+1900)-Integer.parseInt(matrizFecha[2]));
            vinculacionTime.add(String.valueOf(temp));
            int temp1 = ((date.getMonth() + 1-Integer.parseInt(matrizFecha[1])));
            vinculacionTime.add(String.valueOf(temp1));
        }
        return vinculacionTime;
    }
    //Edad Empleado
    public ArrayList<String> edadEmpleado(String fecha_Nacimiento){
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        String matrizFecha[] = fecha_Nacimiento.split("-");

        ArrayList<String> yearTime = new ArrayList<String>(); //Almacenar año y fecha de resultado

        if((date.getMonth()+1)<Integer.parseInt(matrizFecha[1])) {
            int temp=((date.getYear()+1900)-Integer.parseInt(matrizFecha[2])-1);
            yearTime.add(String.valueOf(temp));
            int temp1 = (12 - Integer.parseInt(matrizFecha[1]) + (date.getMonth() + 1));
            if(temp1==12){
                temp1=0;
            }
            yearTime.add(String.valueOf(temp1));
        }
        else{
            int temp=((date.getYear()+1900)-Integer.parseInt(matrizFecha[2]));
            yearTime.add(String.valueOf(temp));
            int temp1 = ((date.getMonth() + 1-Integer.parseInt(matrizFecha[1])));
            yearTime.add(String.valueOf(temp1));
        }
        return yearTime;
    }
}