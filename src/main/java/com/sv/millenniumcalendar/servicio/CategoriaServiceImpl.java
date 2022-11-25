package com.sv.millenniumcalendar.servicio;

import com.sv.millenniumcalendar.clases.Bitacora;
import com.sv.millenniumcalendar.clases.Categoria;
import com.sv.millenniumcalendar.clases.Fecha;
import com.sv.millenniumcalendar.dao.CategoriaDAO;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class CategoriaServiceImpl implements CategoriaService{

    @Autowired
    private CategoriaDAO categoriaDao;
    
    @Autowired
    private BitacoraService bitacoraService;
    
    @Override
    @Transactional(readOnly = true)
    public List<Categoria> listarCategorias() {
        return (List<Categoria>) categoriaDao.listarCategorias();
    }

    @Override
    @Transactional
    public void insertarCategoria(Categoria categoria) {
        categoriaDao.save(categoria);
    }

    @Override
    @Transactional
    public void eliminarCategoria(Categoria categoria) {
        categoriaDao.delete(categoria);
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria buscarCategoria(Categoria categoria) {
        return categoriaDao.findById(categoria.getIdCategoria()).orElse(null);
    }   

    @Override
    @Transactional
    public void actualizarEstadoCategoria(Integer idCategoria) {
        categoriaDao.editarEstadoCategoria(idCategoria);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> listarCategoriasActivas() {
        return (List<Categoria>) categoriaDao.listarCategoriasActivas();
    }   

    @Override
    @Transactional
    public void agregarBitacoraCategoria(Model model, String tipoRegistro, String accion, String nombreTabla, String nombreCategoria) {
        Fecha fecha = new Fecha();
        
        Bitacora bitacora = new Bitacora();
        bitacora.setIdAdministrador((Integer) model.getAttribute("idAdministrador"));
        bitacora.setTipoRegistro(tipoRegistro);
        bitacora.setFechaRegistro(fecha.getFechaRegistro());
        bitacora.setDescripcionRegistro(bitacoraService.descripcionBitacora((String) model.getAttribute("nombreAdministrador"), accion, nombreTabla, nombreCategoria));
        bitacoraService.insertarBitacora(bitacora);
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria encontrarCategoriaInhabilitada(Integer idCategoria) {
        return categoriaDao.encontrarCategoriaInhabilitada(idCategoria);
    }
}
