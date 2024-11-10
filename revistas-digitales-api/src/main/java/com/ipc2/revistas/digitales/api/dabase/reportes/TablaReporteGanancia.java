/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api.dabase.reportes;

import com.ipc2.revistas.digitales.api.modelos.anuncios.AnuncioComprado;
import com.ipc2.revistas.digitales.api.modelos.reporte.RevistaMantenimiento;
import java.util.List;

/**
 *
 * @author melvin
 */
public class TablaReporteGanancia {

    private List<RevistaMantenimiento> revistaMantenimiento;
    private List<AnuncioComprado> anuncioComprado;
    private double ingresos;
    private double egresos;
    private double ganancias;

    public TablaReporteGanancia(List<RevistaMantenimiento> revistaMantenimiento, List<AnuncioComprado> anuncioComprado, double ingresos, double egresos, double ganancias) {
        this.revistaMantenimiento = revistaMantenimiento;
        this.anuncioComprado = anuncioComprado;
        this.ingresos = ingresos;
        this.egresos = egresos;
        this.ganancias = ganancias;
    }

    public List<RevistaMantenimiento> getRevistaMantenimiento() {
        return revistaMantenimiento;
    }

    public void setRevistaMantenimiento(List<RevistaMantenimiento> revistaMantenimiento) {
        this.revistaMantenimiento = revistaMantenimiento;
    }

    public List<AnuncioComprado> getAnuncioComprado() {
        return anuncioComprado;
    }

    public void setAnuncioComprado(List<AnuncioComprado> anuncioComprado) {
        this.anuncioComprado = anuncioComprado;
    }

    public double getIngresos() {
        return ingresos;
    }

    public void setIngresos(double ingresos) {
        this.ingresos = ingresos;
    }

    public double getEgresos() {
        return egresos;
    }

    public void setEgresos(double egresos) {
        this.egresos = egresos;
    }

    public double getGanancias() {
        return ganancias;
    }

    public void setGanancias(double ganancias) {
        this.ganancias = ganancias;
    }
    
    
    

}
