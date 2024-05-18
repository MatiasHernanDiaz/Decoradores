package ar.edu.unsam.algo2

class EquipoBuilder(var equipo: Equipo) {

    fun conPromocion(promocion: Double): EquipoBuilder {
        equipo = EquipoConPromocion(promocion, equipo)
        return this
    }

    fun conDedicacionPlena(): EquipoBuilder {
        equipo = EquipoConDedicacionPlena(equipo)
        return this
    }

    fun sofisticado(aniosRequeridos: Int): EquipoBuilder {
        equipo = EquipoSofisticado(equipo, aniosRequeridos)
        return this
    }

    fun conRegistroDJs(): EquipoBuilder {
        equipo = EquipoConRegistroDJs(equipo)
        return this
    }

    fun build(): Equipo = equipo
}