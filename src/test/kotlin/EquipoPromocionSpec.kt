package ar.edu.unsam.algo2

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class EquipoPromocionSpec: DescribeSpec ({
    isolationMode = IsolationMode.InstancePerTest

    val equipo = EquipoBase(alquilado = false, costo = 300.0)
    val dj1 = Dj(
        comienzoActividades = LocalDate.of(2003,2,14),
        saldoDisponible = 400.0,
        equiposAlquilados = mutableSetOf(),
        dedicacionPlena = true
    )
    val dj2 = Dj(
        comienzoActividades = LocalDate.of(2015,7,23),
        saldoDisponible = 299.0,
        equiposAlquilados = mutableSetOf(),
        dedicacionPlena = false
    )

    describe("Equipo con promoción del 20%"){
        val equipoPromocion = EquipoBuilder(equipo)
            .conPromocion(20.0)
            .build()

        it("el valor del equipo es el mismo que el del equipo base"){
            equipoPromocion.costoAlquiler() shouldBe equipo.costoAlquiler()
        }
        it("cuando se compra el producto, el dj recibe un reintegro"){
            equipoPromocion.alquilarA(dj1)
            dj1.saldoDisponible() shouldBe (400.0 - 300.0 + 60)//300*20/100
        }
        it("un cliente sin dinero no lo puede alquilar, incluso si le alcanzara con el reintegro"){
            shouldThrow<BusinessError> { equipoPromocion.alquilarA(dj2) }
        }
    }

})