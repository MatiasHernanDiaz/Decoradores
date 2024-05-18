package ar.edu.unsam.algo2

class EquipoSofisticado(val equipo: Equipo, val aniosRequeridos: Int) : Equipo by equipo{
    // Hay equipos sofisticados, por lo que solo pueden alquilarse a los Dj que cumplan con los años de experiencia
    // que el mismo equipo requiere (los años dependen del equipo).

    override fun alquilarA(dj: Dj) {
        if(dj.aniosDeExperiencia() < aniosRequeridos){ throw BusinessError("No tiene suficiente experiencia") }
        equipo.alquilarA(dj)
    }

}