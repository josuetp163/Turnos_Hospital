package ec.edu.espol.system;

import ec.edu.espol.common.*;
import ec.edu.espol.constants.Especialidad;
import ec.edu.espol.constants.Genero;
import ec.edu.espol.util.LeerEscribirDatos;

import java.util.Iterator;

public class SysController {

    //Hide de public constructor
    private SysController(){
    }

    private static UsrMedico medicoLogeado = null;
    private static Consulta consulta = null;
 
    public static boolean addMedico(String nomb, String ape, String ed, Genero gen, Especialidad esp,String usr,String contr) {
        if(nomb.equals("") || ape.equals("") || ed.equals("") || gen == null || esp == null || usr.equals("") || contr.equals("")) return false;
        UsrMedico med = new UsrMedico(nomb, ape, Integer.parseInt(ed), gen, esp,usr,contr);
        SysData.addMedico(med);
        return true;
    }

    public static boolean addPaciente(String nomb, String ape, String ed, Genero gen, Sintoma s){
        if(nomb.equals("") || ape.equals("") || ed.equals("") || gen == null || s == null) return false;
        if(SysData.getMedicosDisponibles().isEmpty()) return false;
        UsrPaciente pac = new UsrPaciente(nomb, ape, Integer.parseInt(ed), gen,s);
        SysData.addPaciente(pac);
        return true;
    }

    public static Consulta siguienteTurno(){
        Turno turno = medicoLogeado.siguienteTurno();
        if(turno != null){
            Consulta c = new Consulta(medicoLogeado,turno.getPacienteAtender());
            SysData.addConsulta(c);
            consulta = c;
            SysData.agregarTurno(turno);
            SysData.guardarTurnos();
            return c;
        }
        return null;
    }

    public static boolean iniciarSesion(String username, String pass) {
        Iterator<UsrMedico> it = SysData.medicosRegistrados.iterator();
        while(it.hasNext()) {
            UsrMedico m = it.next();
            if (m.verificarUsuario(username,pass)) {
                medicoLogeado = m;
                return true;
            }
        }
        return false;
    }

    public static boolean terminarConsulta(String diag, String rec){
        consulta.setDiagnostico(diag);
        consulta.setReceta(rec);
        LeerEscribirDatos.registrarConsulta(consulta);
        medicoLogeado.getTurnos().poll();
        LeerEscribirDatos.updateMedicos(SysData.medicosRegistrados);
        consulta = null;
        return true;
    }
    
    public static boolean crearPuesto(UsrMedico medico) {
        if(medico == null) return false;
        if (medico.getPuesto() != null) return false;
        try {
            Puesto puesto = new Puesto(medico);
            medico.setPuesto(puesto);
            SysData.addPuesto(puesto);
            SysData.getMedicosDisponibles().offer(medico);
            SysData.cargarPuestos();
            LeerEscribirDatos.updateMedicos(SysData.medicosRegistrados);
            return true;
        }
        catch(Exception ex) {
            return false;
        }
    }

    public static boolean asignarMedicoPuesto(Puesto p, UsrMedico medico) {
        if(medico == null || p == null) return false;
        if (medico.getPuesto() != null) return false;
        try {
            if (p.getMedico() == null) {
                p.setMedico(medico);
                medico.setPuesto(p);
            } else {
                p.getMedico().setPuesto(null);
                medico.setPuesto(p);
                p.setMedico(medico);
            }
            SysData.getMedicosDisponibles().offer(medico);
            LeerEscribirDatos.updateMedicos(SysData.medicosRegistrados);
            return true;
        }catch(Exception ex) {
            return false; }
    }

    public static boolean desasignarPuesto(Puesto p) {
        if(p == null) return false;
        try {
            if (p.getMedico() == null) {
                return false;
            } else if (p.getMedico() != null) {
                p.getMedico().setPuesto(null);
                p.setMedico(null);
                SysData.getMedicosDisponibles().remove(p.getMedico());
                LeerEscribirDatos.updateMedicos(SysData.medicosRegistrados);
                return true;
            }
            return false;
        } catch(Exception ex) {
            return false;
        }
    }
    public static boolean eliminarPuesto(Puesto p) {
        if(p == null) return false;
        try {
            if (p != null && p.getMedico() == null) {
                SysData.puestos.remove(p);
                return true; }
            return false;
        }
        catch(Exception ex) {
            return false;
        }
    }
}
