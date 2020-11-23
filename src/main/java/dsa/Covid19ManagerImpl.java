package dsa;

import dsa.models.*;
import org.apache.log4j.Logger;

import java.util.*;

import static java.lang.Integer.parseInt;

public class Covid19ManagerImpl implements Covid19Manager {
    private static Covid19Manager instance;
    //HashMap is same as Dictionary
    private HashMap<String , Persona> personas;
    private LinkedList<Muestra> listaMuestras;
    private ArrayList<Laboratorio> vectorLab;
    private Queue<Muestra> muestrasPendientes;
    private static Logger log = Logger.getLogger(Covid19Manager.class);
    //Singleton fachada
    public static Covid19Manager getInstance(){
        if(instance == null) {
            instance = new Covid19ManagerImpl();
        }
        return instance;
    }
    //Private Constructor
    private Covid19ManagerImpl(){
        //Singleton Private Constructor
        this.personas = new HashMap<>();
        this.listaMuestras = new LinkedList<>();
        this.vectorLab= new ArrayList<>();
        this.muestrasPendientes= new LinkedList<>();
    }
    @Override
    public int addPersona(String id, String name, String surname, String edad, String valoracion) {

        try{
            personas.put(id,new Persona(id,name,surname,edad,valoracion));
            log.info("Persona Añadida: " + name );
            return 201; //OK CREATED
        }
        catch (IndexOutOfBoundsException e){
            log.error("UserMap Full Error");
            return 507; //INSUFFICIENT STORAGE
        }
        catch (IllegalArgumentException e){
            log.error("Incorrect format exception");
            return 400; //BAD REQUEST
        }
    }
    //Añadir Persona con Persona
    @Override
    public int addPersona(Persona persona) {

        try{
            if(persona.getId() == null || persona.getId().isEmpty()){
                throw new IllegalArgumentException();
            }
            personas.put(persona.getId(), persona);
            log.info("Persona Añadida: " + persona.getName() );
            return 201; //OK CREATED
        }
        catch (IndexOutOfBoundsException e){
            log.error("UserMap Full Error");
            return 507; //INSUFFICIENT STORAGE
        }
        catch (IllegalArgumentException e){
            log.error("Brote is Empty");
            return 400; //BAD REQUEST
        }
    }
    @Override
    public List<Persona> getListaPersonas() {
        List<Persona> result = null;
        if(this.personas.size() !=0){
            result = new LinkedList<>(this.personas.values());
            log.info("List Personas: "+result.toString());
        }
        return result; //Null: 404 Empty User HashMap
    }

    @Override
    public int addMuestraPersona(String idPersona, Muestra muestra) {
        if(personas.size() ==0){
            log.info("204: No ha personas ");
            return 204;//204 No Content
        }
            Persona temp_persona = personas.get(idPersona);
            if(temp_persona != null){
                int err = temp_persona.addMuestra(muestra);
                if(err == 201){
                    personas.put(idPersona, temp_persona);
                    log.info("201: Muestra añadida a persona " + temp_persona.getName());
                    return 201; //OK CREATED
                }
                else if(err == 400){
                    log.error("400: Bad Format");
                    return 400; //BAD REQUEST
                }
                else{
                    log.error("507:Insufficient storage "+ temp_persona.getName());
                    return 507; //204 No Content
                }
            }
            else{
                log.error("Persona Not found");
                return 404; //Brote NOT FOUND
            }
    }

    @Override
    public int sendMuestraLab(String idLab, Muestra muestra) {
        vectorLab.get(parseInt(idLab)).addMuestra(muestra);
        muestrasPendientes.add(muestra);
        return 201;
    }
    @Override
    public int processMuestra(String idLab)
    {
        Muestra result=vectorLab.get(parseInt(idLab)).processMuestra();
        addMuestraPersona(result.getIdPersona(), result);
        return 201;
    }

    @Override
    public Persona getPersona(String idPersona) {
            return personas.get(idPersona);
    }

    @Override
    public int numPersonas(){return this.personas.size();
    }

    @Override
    public List<Muestra> getListaMuestrasPersona(String idPersona) {
        List<Muestra> listaMuestras;
        Persona persona = getPersona(idPersona);
        if(persona ==null){return null;}
        //We have found the persona but it doesn't contain muestras
        int nums = persona.getNumMuestras();
        if(nums==0){return null;}
        listaMuestras = persona.getListaMuestras();
        return listaMuestras;

    }

    @Override
    public int setListaMuestraPersona(String id, List<Muestra> listMuestra) {
        Persona persona = getPersona(id);
        persona.setListaMuestras(listMuestra);
        return 201;
    }

    @Override
    public void liberateReserves() {
        this.listaMuestras.clear();
        this.personas.clear();
        this.vectorLab.clear();
        this.muestrasPendientes.clear();

    }


}
