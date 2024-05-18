package ar.edu.unsam.algo2

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class EquipoBaseSpec: DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest


    val dj1 = Dj(
        comienzoActividades = LocalDate.of(2003,2,14),
        saldoDisponible = 400.0,
        equiposAlquilados = mutableSetOf(),
        dedicacionPlena = true
        )
    val dj2 = Dj(
        comienzoActividades = LocalDate.of(2015,7,23),
        saldoDisponible = 390.7,
        equiposAlquilados = mutableSetOf(),
        dedicacionPlena = false
    )
    val dj3 = Dj(
        comienzoActividades = LocalDate.of(1993,3,14),
        saldoDisponible = 450.0,
        equiposAlquilados = mutableSetOf(),
        dedicacionPlena = true
    )


    val equipo = EquipoBase(alquilado = false, costo = 400.0)

    describe("equipo base"){
        it("equipo puede alquilar dj1 ya que tiene el saldo suficiente"){
            shouldNotThrow<BusinessError> { equipo.alquilarA(dj1) }
        }
        it("cuando alquila este no se puede volver a alquilar"){
            equipo.alquilarA(dj1)
            shouldThrow<BusinessError> { equipo.alquilarA(dj3) }
        }
        it("no puede alquilar al dj2 ya que noo tiene el saldo suficiente"){
            shouldThrow<BusinessError> { equipo.alquilarA(dj2) }
        }
        it("alquilado es true despues de alquilar"){
            equipo.alquilarA(dj1)
            equipo.alquilado() shouldBe true
        }
        it("costo alquiler es el que dice"){
            equipo.costoAlquiler() shouldBe 400
        }
        it("inflacion"){
            equipo.establecerCosto(405.7)
            equipo.costoAlquiler() shouldBe 405.7
        }
        it("por culpa de la inflación, dj1 ya no puede comprar el producto"){
            equipo.establecerCosto(405.7)
            shouldThrow<BusinessError> { equipo.alquilarA(dj1) }
        }
    }
})