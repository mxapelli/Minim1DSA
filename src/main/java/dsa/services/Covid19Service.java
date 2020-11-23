package dsa.services;

import dsa.Covid19Manager;
import dsa.Covid19ManagerImpl;
import dsa.models.Muestra;
import dsa.models.Persona;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

//Models or Element Entity
//Swagger Imports
@Api(value = "/Covid19", description = "Endpoint to User Service")
@Path("/Covid19Service")
public class Covid19Service {
    static final Logger logger = Logger.getLogger(Covid19Service.class);
    private Covid19Manager manager;
    //Estructura de datos
    Persona persona;
    List<Muestra> listaMuestras1;
    List<Muestra> listaMuestras2;
    public Covid19Service(){
        //Configuring Log4j, location of the log4j.properties file and must always be inside the src folder
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        this.manager = Covid19ManagerImpl.getInstance();
        if (this.manager.numPersonas() == 0) {
            //Adding
            manager = Covid19ManagerImpl.getInstance();
            //Initializing Object List
            listaMuestras1 =  new LinkedList<Muestra>();
            listaMuestras2 =  new LinkedList<Muestra>();
            //Appending Muestra en Lista
            //Example Date firstDate3 = new Date(int year, int month, int date, int hrs, int min, int sec);

            Date date = new Date(2020,04,12,05,10,10);
            //Caso( String idCaso,String nombre, String apellidos, String genero, String correo, String direccion, Date fechaNacimiento,Date fechaInforme, String nivelRiesgo, String classificacion, int telefono)
            listaMuestras1.add(new Muestra("001", "002", "002", date , "1"));
            listaMuestras2.add(new Muestra("001", "003", "001", date , "2"));

            persona = new Persona("001", "James", "Horner","45","B",listaMuestras1);
            manager.addPersona(persona);
            persona = new Persona("002", "Marc", "Xapelli","24","B",listaMuestras2);
            manager.addPersona(persona);

        }
    }

    //Lista de Personas
    @GET
    @ApiOperation(value = "Get all Personas", notes = "Retrieves the list of personas")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Persona.class, responseContainer="List"),
    })
    @Path("/listPersonas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        List<Persona> user = this.manager.getListaPersonas();
        GenericEntity<List<Persona>> entity = new GenericEntity<List<Persona>>(user) {};
        return Response.status(201).entity(entity).build()  ;
    }



    //Añadir una Persona
    @POST
    @ApiOperation(value = "Create a new persona", notes = "Adds a new persona")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Persona.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/addPersona/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newUser(@PathParam("name") String namePersona,@PathParam("surname") String surname,@PathParam("edad") String edad,@PathParam("valoracion") String valoracion) {
        if (namePersona.isEmpty())  return Response.status(500).entity(new Persona()).build();
        String idPersona = "003";
        this.manager.addPersona(idPersona,namePersona,surname,edad,valoracion);
        return Response.status(201).entity(manager.getPersona(idPersona)).build();
    }

}
