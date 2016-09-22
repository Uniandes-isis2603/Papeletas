/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.hospital.mocks;

import co.edu.uniandes.rest.hospital.dtos.ConsultorioDTO;
import co.edu.uniandes.rest.hospital.dtos.EspecializacionDTO;
import co.edu.uniandes.rest.hospital.dtos.MedicoDTO;
import co.edu.uniandes.rest.hospital.exceptions.MedicoException;
import co.edu.uniandes.rest.hospital.exceptions.TurnoLogicException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author n.simmonds10
 */
public class MedicoMock {

    /**
     * Logger de la clase
     */
    private final static Logger logger = Logger.getLogger(MedicoMock.class.getName());

    /**
     * Constante con el nunmero de medicos
     */
    private final static int NUM_MED = 103;

    /**
     * Arraylist con los medicos
     */
    private static ArrayList<MedicoDTO> medicos;
    
    
    private CitaMock cita;


    public MedicoMock()
    {
        if (medicos == null)
        {
            cita = new CitaMock();
            medicos = new ArrayList<>();

            medicos.add(new MedicoDTO("Nicolas Simmonds", 1L  , new EspecializacionDTO(1, "Cardiologia")));
            medicos.add(new MedicoDTO("Juan Mendez", 2L, new EspecializacionDTO(1,"Cardiologia")));
            medicos.add(new MedicoDTO("Diego Castro", 3L, new EspecializacionDTO(2, "Endocrinologia")));
            medicos.add(new MedicoDTO("Juan Useche", 4L, new EspecializacionDTO(3, "Neumologia")));
            medicos.add(new MedicoDTO("Juan Lara", 5L, new EspecializacionDTO(4, "Neurologia")));
           
        }
        logger.setLevel(Level.INFO);
        logger.info("Inicializa la lista de medicos");
        logger.info("medicos" + medicos);
    }

    /**
     * Lista de los medicos
     *
     * @return lista de medicos
     * @throws MedicoException Si la lista no existe
     */
    public List<MedicoDTO> getMedico() throws MedicoException {
        if (medicos == null) {
            logger.severe("Error interno: lista de medicos no existe.");
            throw new MedicoException("la lista de los medicos esta vacia");
        }
        logger.info("Lista de medicos");
        return medicos;
    }

    /**
     * Crea un nuevo medico
     * @param medico medico a crear
     * @return medico nuevo
     * @throws MedicoException si ya hay un medico con el id dado 
     */
    public MedicoDTO createMedico(MedicoDTO medico) throws MedicoException {
        logger.info("recibiendo solicitud de agrega r medico " + medico);

        if (medico.getId() != null) {
            for (MedicoDTO medic : medicos) {
                if ((medic.getId()==medico.getId())) {
                    throw new MedicoException("Ya existe un medico con ese id");
                }
            }
        } else {
            Long nid = 1L;
            for (MedicoDTO medic : medicos) {
                if (nid <= medic.getId()) {
                    nid = medic.getId() + 1;
                }
            }
            medico.setId(nid);
        }
        medicos.add(medico);
        return medico;
    }

    /**
     * Retorna un medico dado su id
     * @param id id de; medico          
     * @return medico con el id dado
     * @throws MedicoException  si no existe un medico con el id dado
     */
    public MedicoDTO getMedID(Long id) throws MedicoException {
        MedicoDTO med = null;
        if (medicos == null) {
            throw new MedicoException("La lista de medicos esta vacia");
        } else {
            for (MedicoDTO medic : medicos) {
                if (medic.getId().equals(id)) {
                    med = medic;
                }
            }
            if (med == null) {
                throw new MedicoException("No hay medicos con el id dado");
            }
        }
        return med;
    }

    /**
     * Borra un medico de la lista
     * @param id id del medico
     * @throws MedicoException si el medico no existe  
     */
    public void deleteMedico(Long id) throws MedicoException {
        if (medicos == null) {
            throw new MedicoException("La lista de medicos esta vacia");
        } else {
            boolean ya = false;
            for (MedicoDTO medic : medicos) {
                if (medic.getId().equals(id)) {
                    medicos.remove(medic);
                    ya = true;
                }
            }
            if (!ya) {
                throw new MedicoException("El medico que desea eliminar no existe");
            }
        }
    }

    /**
     * Actualiza un medico con los datos dados por parametro
     * @param medicoN medico con los datos nuevos
     * @return medico actualizado
     * @throws MedicoException si no existe un medico con el id dado para actualizar
     */
    public MedicoDTO updateMedico(MedicoDTO medicoN) throws MedicoException {
        if (medicos == null) {
            throw new MedicoException("La lista de medicso esta vacia");
        } else {
            boolean ya = false;
            for (MedicoDTO medic : medicos) {
                if (Objects.equals(medic.getId(), medicoN.getId())) {
                    medic.setEspecialidad(medicoN.getEspecializacion());
                    medic.setNombre(medicoN.getNombre());
                    ya = true;

                }
            }
            if (!ya) {
                throw new MedicoException("No existe el medico con el id dado para actualizar");
            }
        }
        return medicoN;
    }
    
    
    
    /**
     * Calcula el promedio de duracion de citas dado un medico
     * @param id del medico
     * @return 
     */
      public double calcularPromedioCitaMedico(Long id)
    {
        double promedio=0;
        for(int i=0;i<medicos.size();i++)
        {
            if(medicos.get(i).getId().equals(id))
            {
               
               
            }
        }
        return promedio;
    
    }
    
    
      /**
       * Calcula el promedio de citas dada una especialidad;
       * @param pEspecialidad
       * @return 
       */
   public double calcularPromedioEspecialidad(String pEspecialidad)
   {
       double promedio=0;
       for(int i=0;i<medicos.size();i++)
       {
           if(medicos.get(i).getEspecializacion().equals(pEspecialidad)) 
            {
                promedio+=medicos.get(i).calcularPromedioCitaMedico();
            }          
       }
       return promedio;
       
   }
  
   
   /**
    * Crea un turno en el médco con cierta id
    * @param pIdMedico Id del médico
    * @param pFecha Fecha del medico
    * @param pDuracion Duracion del turno en minutos
    */
   
   public List listaPorSpec(String spec)      
   {
       ArrayList temp = new ArrayList();
       for(int i=0;i<medicos.size();i++)
       {
           if(medicos.get(i).getEspecializacion().getNombre().equals(spec))
           {
               temp.add(medicos.get(i));
           }
       }
       return temp;
   }
}
   
   
  
   
