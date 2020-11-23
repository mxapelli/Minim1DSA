package dsa.models;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
public class Laboratorio {
    String idLab;
    String nameLab;
    Queue<Muestra> colaMuestras;
    public Laboratorio(String idLab, String nameLab,Queue<Muestra> colaMuestras)
    {
        this.idLab=idLab;
        this.nameLab=nameLab;
        this.colaMuestras=colaMuestras;
    }
    public void addMuestra(Muestra muestra)
    {
        colaMuestras.add(muestra);
    }

    public Muestra processMuestra()
    {
        return colaMuestras.poll();
    }

    public String getIdLab() {
        return idLab;
    }

    public void setIdLab(String idLab) {
        this.idLab = idLab;
    }

    public String getNameLab() {
        return nameLab;
    }

    public void setNameLab(String nameLab) {
        this.nameLab = nameLab;
    }

    public Queue<Muestra> getColaMuestras() {
        return colaMuestras;
    }

    public void setColaMuestras(Queue<Muestra> colaMuestras) {
        this.colaMuestras = colaMuestras;
    }
}
