package dsa;

import dsa.models.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Covid19ManagerImplTest {
    // THE QUICK REMINDER: Remember to name the test class public smh
    //Log4j Logger initialization
    private static Logger logger = Logger.getLogger(Covid19ManagerImplTest.class);
    //GameManager
    public Covid19Manager manager = null;
    //Estructura de datos
    Persona persona;
    List<Muestra> listaMuestras;
    //Metodo SetUp
    @Before
    public void setUp() {
        //Configurando Log4j
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        logger.debug("Debug Test Message!");
        logger.info("Info Test Message!");
        logger.warn("Warning Test Message!");
        logger.error("Error Test Message!");
        //Implementacion Covid19Manager
        manager = Covid19ManagerImpl.getInstance();
        //Inicializando lista objetos
        listaMuestras =  new LinkedList<Muestra>();
        //Initialzing Test User
        persona= new Persona("002", "James", "Horner","45","B");
        //Appending data to Object List
        Date date = new Date(2020,10,23,05,30,10);
        listaMuestras.add(new Muestra("001", "002", "002", date , "1"));
        //Adding Objects list to Game Manager
        manager.setListaMuestraPersona(persona.getId(),listaMuestras);

    }
    //Tests
    //Metodo Test para crear un nuevo brote
    @Test
    public void crearNuevaPersona(){
        //Test inicial, 0 personas
        Assert.assertEquals(0, this.manager.numPersonas());
        //Test Persona
        persona= new Persona("001", "Marc", "Xape","23","A");
        manager.addPersona(persona);
        //Ahora hay 1 Persona
        Assert.assertEquals(1, this.manager.numPersonas());
    }
    //Tests
    //Metodo Test para una muestra sobre una persona
    @Test
    public void addMuestraPersona(){
        //Añadimos Persona
        persona= new Persona("001", "Luke", "Skil","20","A");
        manager.addPersona(persona);
        //Initial Test
        Assert.assertEquals(0, this.manager.getPersona("001").getNumMuestras());
        //Añadimos 1 muestra
        this.manager.addMuestraPersona("001", listaMuestras.get(0));
        //Esperamos 1 muestra
        Assert.assertEquals(1, this.manager.getPersona("001").getNumMuestras());
        //Añadimos 2o caso
        this.manager.addMuestraPersona("001", listaMuestras.get(1));
        //Ahora esperamos 2 casos
        Assert.assertEquals(2, this.manager.getPersona("001").getNumMuestras());
    }
    @After
    public void tearDown() throws Exception {
        manager.liberateReserves();
    }
}
