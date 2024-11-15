/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.servicios;

import com.ipc2.revistas.digitales.api.dabase.AdministradorDB;
import com.ipc2.revistas.digitales.api.dabase.reportes.GeneradorRevistaPopular;
import com.ipc2.revistas.digitales.api.dabase.reportes.RevistasTop;
import com.ipc2.revistas.digitales.api.modelos.reporte.ReporteEfectividadAnuncio;
import com.ipc2.revistas.digitales.api.modelos.reporte.RevistaPopular;
import com.ipc2.revistas.digitales.api.modelos.revista.Revista;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author melvin
 */
public class ReporteAdministradorService {

    private RevistasTop revistasTop = new RevistasTop();
    private GeneradorRevistaPopular generadorRevistaPopular = new GeneradorRevistaPopular();

    public List<Revista> revistasMasComentadas() {
        return revistasTop.revistasMasComentadas();

    }

    public List<RevistaPopular> obtenerRevistasPopulares() {
        return generadorRevistaPopular.obtenerTop5();
    }


}
