package dsa;
import dsa.models.*;

import java.util.List;

public interface Covid19Manager {

    //Añadir una Persona
    int addPersona(String id, String name, String surname, String edad, String valoracion);

    //Añadir un Brote
    int addPersona(Persona persona);

    int numPersonas();

    //Listado de muestras de una determinada persona
    List<Muestra> getListaMuestrasPersona(String idPersona);

    int setListaMuestraPersona(String id, List<Muestra> listMuestra);

    //Lista de las Personas
    List<Persona> getListaPersonas();

    //Añadir una Muestra a una persona
    int addMuestraPersona(String idPersona, Muestra muestra);

    //Enviar Muestra al Lab
    int sendMuestraLab(String idLab, Muestra muestra);

    int processMuestra(String idLab);

    Persona getPersona(String idPersona);

    //Liberar Recursos
    void liberateReserves();


}
