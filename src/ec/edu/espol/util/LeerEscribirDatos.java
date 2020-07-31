package ec.edu.espol.util;

import ec.edu.espol.common.*;
import ec.edu.espol.constants.Constantes;
import ec.edu.espol.constants.Especialidad;
import ec.edu.espol.constants.Genero;
import ec.edu.espol.system.SysData;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class LeerEscribirDatos{

    //SINTOMAS
    public static List<Sintoma> cargarSintomas(){
        List<Sintoma> sintomas = new LinkedList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(Constantes.RUTASINTOMAS))){
            String line = br.readLine();
            while(line != null){
                String[] data = line.split("\\|");
                sintomas.add(new Sintoma(data[0], Integer.valueOf(data[1])));
                line = br.readLine();
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return sintomas;
    }

    public static boolean añadirSintoma(Sintoma s){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(Constantes.RUTASINTOMAS,true))){
            String line = s.getNombre() + "|" + s.getPrioridad();
            bw.newLine();
            bw.write(line);
        }catch(IOException ex){
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    //MEDICOS
    public static List<UsrMedico> cargarMedicos(){
        List<UsrMedico> medicos = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(Constantes.RUTAMEDICOS))){
            String line = br.readLine();
            while(line != null){
                String[] data = line.split("\\|");
                Puesto puesto = null;
                UsrMedico m = null;
                if(data[7].equals("null")) {
                    m = new UsrMedico(data[0],data[1],Integer.valueOf(data[2]),Genero.valueOf(data[3]),Especialidad.valueOf(data[4]),String.valueOf(data[5]),String.valueOf(data[6]));
                }else{
                    puesto = new Puesto(Integer.valueOf(data[7]));
                    m = new UsrMedico(data[0],data[1],Integer.valueOf(data[2]),Genero.valueOf(data[3]),Especialidad.valueOf(data[4]),String.valueOf(data[5]),String.valueOf(data[6]),puesto);
                    puesto.setMedico(m);
                }
                PriorityQueue<Turno> turnos = new PriorityQueue<>((Turno t1, Turno t2)->t1.getPacienteAtender().getSintoma().getPrioridad() - t2.getPacienteAtender().getSintoma().getPrioridad());
                if(!data[8].equals("null")){
                    String[] dataTurnos = data[8].split("-");
                    for(String s: dataTurnos){
                        String[] dataPaciente = s.split(",");
                        turnos.offer(new Turno(m,new UsrPaciente(dataPaciente[0],dataPaciente[1],Integer.valueOf(dataPaciente[2]),Genero.valueOf(dataPaciente[3]),new Sintoma(dataPaciente[4],Integer.valueOf(dataPaciente[5])))));
                    }
                }
                m.setTurnos(turnos);
                medicos.add(m);
                line = br.readLine();
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return medicos;
    }

    public static void updateMedicos(List<UsrMedico> medicos){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(Constantes.RUTAMEDICOS))){
            for(UsrMedico m: medicos){
                String line = m.infoText();
                bw.write(line);
                bw.newLine();
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    //TURNOS
    public static LinkedList<Turno> cargarTurnos(){
        LinkedList<Turno> turnos = new LinkedList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(Constantes.RUTATURNOS))){
            String line = br.readLine();
            while(line != null){
                String[] data = line.split("\\|");
                for(UsrMedico m: SysData.getMedicosConPuesto()){
                    if(m.getUsuario().equals(data[0])){
                        String[] dataPaciente = data[1].split(",");
                        turnos.add(new Turno(m,new UsrPaciente(dataPaciente[0],dataPaciente[1],Integer.valueOf(dataPaciente[2]),Genero.valueOf(dataPaciente[3]),new Sintoma(dataPaciente[4],Integer.valueOf(dataPaciente[5]))),Integer.valueOf(data[2])));
                    }
                }
                line = br.readLine();
            }
        }catch(IOException ex){
            System.out.println("Archivo no encontrado");
        }
        return turnos;
    }

    /*public static LinkedList<Turno> cargarTurnos(){
        LinkedList<Turno> turnos = new LinkedList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(Constantes.RUTATURNOS))){
            String line = br.readLine();
            while(line != null){
                String[] data = line.split("\\|");
                String[] dataMedico = data[0].split(",");
                String[] dataPaciente = data[1].split(",");
                turnos.add(new Turno(new UsrMedico(dataMedico[0],dataMedico[1],Integer.valueOf(dataMedico[2]),Genero.valueOf(dataMedico[3]),Especialidad.valueOf(dataMedico[4]),dataMedico[5],dataMedico[6]),new UsrPaciente(dataPaciente[0],dataPaciente[1],Integer.valueOf(dataPaciente[2]),Genero.valueOf(dataPaciente[3]),new Sintoma(dataPaciente[4],Integer.valueOf(dataPaciente[5]))),Integer.valueOf(data[2])));
            }
        }catch(IOException ex){
            System.out.println("Archivo no encontrado");
        }
        return turnos;
    }*/

    public static void updateTurnos(LinkedList<Turno> turnos){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(Constantes.RUTATURNOS))){
            for(Turno t: turnos){
                String line = t.infoText();
                bw.write(line);
                bw.newLine();
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    //VIDEOS
    public static CircularSimplyLinkedList<Video> cargarVideos(){
        CircularSimplyLinkedList<Video> videos = new CircularSimplyLinkedList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(Constantes.RUTAVIDEOS))){
            String line = br.readLine();
            while(line != null){
                String[] data = line.split("-");
                long l = Integer.parseInt(data[2]);
                videos.addLast(new Video(data[0],data[1],l));
                line = br.readLine();
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return videos;
    }

    //CONSULTAS
    public static boolean registrarConsulta(Consulta c){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(Constantes.RUTACONSULTAS,true))){
            String line = "Medico: " + c.getMedico().getNombre() + " " + c.getMedico().getApellido() + " | " +
                          "Paciente: " + c.getPaciente().getNombre() + " " + c.getPaciente().getApellido() + " | " +
                          "Fecha: " + LocalDate.now() + " | " +
                          "Hora: " + LocalDateTime.now().getHour() + "h" + LocalDateTime.now().getMinute() + " | " +
                          "Diagnostico: " + c.getDiagnostico() + " | " +
                          "Receta: " + c.getReceta() + "\n";
            bw.write(line);
        }catch(IOException ex){
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    //PUESTOS
    public static List<Puesto> cargarPuestos(List<UsrMedico> medicos){
        List<Puesto> puestos = new LinkedList<>();
        for(UsrMedico m: medicos){
            puestos.add(new Puesto(m));
        }
        return puestos;
    }

    /* SE BORRA */

    /*public static boolean añadirTurno(Turno t){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(Constantes.RUTAMEDICOS,true))){
            String line = t.infoText();
            bw.newLine();
            bw.write(line);
        }catch(IOException ex){
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }*/

    /*public static boolean añadirMedico(UsrMedico m){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(Constantes.RUTAMEDICOS,true))){
            String line = m.getNombre() + "|" + m.getApellido() + "|" + m.getEdad() + "|" + m.getGenero().toString() + "|" + m.getEspecialidad().toString() + "|" + m.getUsuario() + "|" + m.getContraseña() + "|null";
            bw.newLine();
            bw.write(line);
        }catch(IOException ex){
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }*/

    /*public static void cargarMedicos(PriorityQueue<UsrMedico> medicosRegistrados){
        try(BufferedReader br = new BufferedReader(new FileReader(Constantes.RUTAMEDICOS))){
            String line = br.readLine();
            while(line != null){
                String[] data = line.split("\\|");
                medicosRegistrados.offer(new UsrMedico(data[0],data[1],Integer.valueOf(data[2]),Genero.valueOf(data[3]),Especialidad.valueOf(data[4]),String.valueOf(data[5]),String.valueOf(data[6])));
                line = br.readLine();
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }*/

    //PERSISTENCIA SYSTEM
    /*public static List<UsrMedico> cargarMedicosBinary(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Constantes.INFOMED))){
            List<UsrMedico> listaMedicos = (LinkedList<UsrMedico>)ois.readObject();
            return listaMedicos;
        }catch(IOException | ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }*/

    //public static void updateMed(PriorityQueue<UsrMedico> medicos)

    /*public static void main(String[] args){
        List<Sintoma> sintomas = cargarSintomas();
        //System.out.println(sintomas.size());
        for(Sintoma s: sintomas){
            System.out.println(s);
        }
        System.out.println("#############");
        System.out.println(añadirSintoma(new Sintoma("dolor de cabeza",4)));
        sintomas = cargarSintomas();
        for(Sintoma s: sintomas){
            System.out.println(s);
        }

        List<UsrMedico> medicos = cargarMedicos();
        for(UsrMedico m: medicos){
            System.out.println(m);
        }
        System.out.println("#############");
        System.out.println(añadirMedico(new UsrMedico("Juanito","Alcachofa",31,Genero.MASCULINO,Especialidad.PEDIATRIA)));
        medicos = cargarMedicos();
        for(UsrMedico m: medicos){
            System.out.println(m);
        }
        System.out.println(registrarConsulta(new Consulta(new UsrMedico("a","b",15,Genero.MASCULINO,Especialidad.CARDIOLOGIA, "sdsds","dsdsds"), new UsrPaciente("aaa","bbb",21,Genero.FEMENINO,new Sintoma("fiebre",1)),"diagnostico","receta")));
    }*/
}
