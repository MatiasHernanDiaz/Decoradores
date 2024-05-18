package ar.edu.unsam.algo2
import java.time.LocalDate
import java.time.Period

class Dj(
    private val comienzoActividades: LocalDate,
    private var saldoDisponible: Double,
    private val equiposAlquilados: MutableSet<Equipo> = mutableSetOf(),
    private val dedicacionPlena: Boolean = false
) {
    fun puedeAlquilar(equipo: Equipo): Boolean = saldoDisponible >= equipo.costoAlquiler()

    fun alquilar(equipo: Equipo) {
        equiposAlquilados.add(equipo)
        aumentarSaldo( -1 * equipo.costoAlquiler() )
    }
    fun aumentarSaldo(valor: Double): Unit {
        saldoDisponible += valor
    }
    fun aniosDeExperiencia(): Int = Period.between(comienzoActividades, LocalDate.now()).years
    fun esDedicado(): Boolean = dedicacionPlena
    fun saldoDisponible() = saldoDisponible
}
