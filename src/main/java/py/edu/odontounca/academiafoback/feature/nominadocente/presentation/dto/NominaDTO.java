package py.edu.odontounca.academiafoback.feature.nominadocente.presentation.dto;

import java.util.List;

public record NominaDTO(
        Integer id,
        String nombre,
        PeriodoLectivoDTO periodoLectivo,
        PlanCurricularDTO planCurricular,
        List<NominaDetalleDTO> detalles
) {
}
