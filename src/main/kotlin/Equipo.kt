package ar.edu.unsam.algo2


interface Equipo {
    var alquilado: Boolean
    var costo: Double

    fun validarAlquiler(dj: Dj): Unit
    fun alquilarA(dj: Dj):Unit
    fun alquilado(): Boolean
    fun costoAlquiler(): Double
    fun establecerCosto(costo: Double): Unit
}

class EquipoBase(override var alquilado: Boolean, override var costo: Double): Equipo{
    override fun validarAlquiler(dj: Dj): Unit{
        if(!dj.puedeAlquilar(this) or alquilado()) throw BusinessError("el dj no puede alquilar el equipo"+this.costoAlquiler().toString())
    }
    override fun alquilarA(dj: Dj) {
            validarAlquiler(dj)
            alquilado = true
            dj.alquilar(this)
        }
    override fun alquilado(): Boolean = alquilado
    override fun costoAlquiler(): Double = costo
    override fun establecerCosto(costo: Double)  {this.costo = costo}
}
