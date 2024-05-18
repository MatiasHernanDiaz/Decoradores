package ar.edu.unsam.algo2

class EquipoConDedicacionPlena(val equipo: Equipo) : Equipo by equipo {
    override fun alquilarA(dj: Dj) {
        if (!dj.esDedicado()) {
            throw BusinessError("El dj no puede alquilar el equipo por tener dedicacion plena")
        }
        equipo.alquilarA(dj)
    }
}