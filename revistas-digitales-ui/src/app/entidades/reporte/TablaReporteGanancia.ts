
import { AnuncioComprado } from "./AnuncioComprado";
import { RevistaMantenimiento } from "./RevistaMantenimiento";

export interface TablaReporteGanancia {
    revistaMantenimiento: RevistaMantenimiento[];
    anuncioComprado: AnuncioComprado[];
    ingresos: number;
    egresos: number;
    ganancias: number;
  }