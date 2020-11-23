package dsa.models;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Muestra {
    //Formato
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    String idMuestra ;
    String idClinico;
    String idPersona;
    Date fechaMuestra;
    String idLab;
    Informe informe;


    //Constructor vacio
    public Muestra(){
    }

    //Constructor caso
    public Muestra(String idMuestra, String idClinico, String idPersona,  Date fechaMuestra, String idLab) {
        this.idMuestra = idMuestra;
        this.idClinico=idClinico;
        this.idPersona=idPersona;
        this.fechaMuestra=fechaMuestra;
        this.idLab=idLab;
    }

    public SimpleDateFormat getFormatter() {
        return formatter;
    }

    public void setFormatter(SimpleDateFormat formatter) {
        this.formatter = formatter;
    }

    public String getIdMuestra() {
        return idMuestra;
    }

    public void setIdMuestra(String idMuestra) {
        this.idMuestra = idMuestra;
    }

    public String getIdClinico() {
        return idClinico;
    }

    public void setIdClinico(String idClinico) {
        this.idClinico = idClinico;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public Date getFechaMuestra() {
        return fechaMuestra;
    }

    public void setFechaMuestra(Date fechaMuestra) {
        this.fechaMuestra = fechaMuestra;
    }

    public String getIdLab() {
        return idLab;
    }

    public void setIdLab(String idLab) {
        this.idLab = idLab;
    }
}
