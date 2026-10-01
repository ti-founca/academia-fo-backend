package py.edu.odontounca.academiafoback.feature.nominadocente.presentation.dto;

public record NominaResumenDTO(
        Integer id,
        String nombre,
        PeriodoLectivoDTO periodoLectivo,
        PlanCurricularDTO planCurricular
) {
}
