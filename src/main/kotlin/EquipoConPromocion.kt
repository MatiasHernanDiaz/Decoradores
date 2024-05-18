package ar.edu.unsam.algo2

class EquipoConPromocion(val promocion: Double, val equipo: Equipo): Equipo by equipo{
    override fun alquilarA(dj: Dj) {
        equipo.alquilarA(dj)
        dj.aumentarSaldo(costoAlquiler() * promocion / 100)
    }

}