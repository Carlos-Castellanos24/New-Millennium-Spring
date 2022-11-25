package com.sv.millenniumcalendar.servicio;

import com.sv.millenniumcalendar.clases.Bitacora;
import com.sv.millenniumcalendar.dao.BitacoraDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BitacoraServiceImpl implements BitacoraService{

    @Autowired
    private BitacoraDAO bitacoraDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Bitacora> listarBitacoras() {
        return (List<Bitacora>) bitacoraDao.listarBitacoras();
    }

    @Override
    @Transactional
    public void insertarBitacora(Bitacora bitacora) {
        bitacoraDao.save(bitacora);
    }

    @Override
    @Transactional
    public void eliminarBitacora(Bitacora bitacora) {
        bitacoraDao.delete(bitacora);
    }

    @Override
    @Transactional(readOnly = true)
    public Bitacora buscarBitacora(Bitacora bitacora) {
        return bitacoraDao.findById(bitacora.getIdRegistro()).orElse(null);
    }   

    @Override
    @Transactional
    public String descripcionBitacora(String nombreAdministrador, String accion, String tabla, String dato) {
        return nombreAdministrador + " " + accion +" un/a " + tabla + " con el nombre: " + dato;        
    }
}
