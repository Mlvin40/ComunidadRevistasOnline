/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.servicios;

import com.ipc2.revistas.digitales.api.dabase.ComentarioDB;
import com.ipc2.revistas.digitales.api.dabase.SuscripcionDB;
import com.ipc2.revistas.digitales.api.dabase.reportes.RevistasTop;
import com.ipc2.revistas.digitales.api.modelos.revista.Comentario;
import com.ipc2.revistas.digitales.api.modelos.revista.Revista;
import com.ipc2.revistas.digitales.api.modelos.revista.Suscripcion;
import java.util.List;

/**
 *
 * @author melvin
 */
public class ReporteEditorService {

    private ComentarioDB comentarioDB = new ComentarioDB();
    private SuscripcionDB suscripcionDB = new SuscripcionDB();
    private RevistasTop revistasTop = new RevistasTop();

    public List<Comentario> obtenerTodosLosComentarios() {
        return comentarioDB.obtenerTodosLosComentarios();
    }

    public List<Comentario> obtenerComentariosPorRevista(String nombreRevista) {
        return comentarioDB.obtenerComentariosPorRevista(nombreRevista);
    }

    public List<Suscripcion> obtenerTodasLasSuscripciones() {
        return suscripcionDB.obtenerTodasSuscripciones();
    }
    
    public List<Suscripcion> obtenerSuscripcionesPorRevista(String nombreRevista) {
        return suscripcionDB.obtenerSuscripcionesPorRevista(nombreRevista);
    }

    public List<Revista> revistasMasGustadas() {
        return revistasTop.revistasMasGustadas();
    }
}
