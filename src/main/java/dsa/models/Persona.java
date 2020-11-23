package dsa.models;

import java.util.LinkedList;
import java.util.List;

public class Persona {

    private String id;
    private String name;
    private String surname;
    private String edad;
    private String valoracion;

    List<Muestra> listaMuestras;
    public Persona() {

    }
    public Persona(String id, String name, String surname, String edad, String valoracion){
        this.id = id;
        this.name = name;
        this.surname=surname;
        this.edad=edad;
        this.valoracion=valoracion;
        this.listaMuestras = new LinkedList<>();
    }
    public Persona(String id, String name, String surname, String edad, String valoracion,List<Muestra> listaMuestras){
        this.id = id;
        this.name = name;
        this.surname=surname;
        this.edad=edad;
        this.valoracion=valoracion;
        this.listaMuestras = listaMuestras;
    }

    public int addMuestra(Muestra muestra){
        try{
            this.listaMuestras.add(muestra);
        }
        catch (ExceptionInInitializerError e)
        {
            return 400;//400 Bad Request
        }
        catch (IndexOutOfBoundsException e){
            return 507;//Insufficient storage
        }
        return 201;//201 Created
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getEdad() {
        return edad;
    }
    public String getValoracion() {
        return valoracion;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setEdad(String edad) {
        this.edad = edad;
    }
    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }
    public int getNumMuestras(){
        return this.listaMuestras.size();
    }

    public void setListaMuestras(List<Muestra> listaMuestras) {
        this.listaMuestras = listaMuestras;
    }

    public List<Muestra> getListaMuestras() {
        return listaMuestras;
    }

    @Override
    public String toString(){
        return "ID Persona: " + this.getId() + " | Persona Nombre: " + this.getName() ;
    }

}
