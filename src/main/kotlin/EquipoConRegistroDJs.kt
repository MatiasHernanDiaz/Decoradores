package ar.edu.unsam.algo2


class EquipoConRegistroDJs(val equipo: Equipo) : Equipo by equipo {
    companion object {
        private val registroDJs = mutableListOf<Dj>()

        private fun registrarDJ(dj: Dj) {
            registroDJs.add(dj)
        }

        fun cantidadAlquileres(dj: Dj) = registroDJs.count { it === dj }
    }

    override fun alquilarA(dj: Dj) {
        equipo.alquilarA(dj)
        registrarDJ(dj)
    }
}
