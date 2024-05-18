import ar.edu.unsam.algo2.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class EquipoConDedicionPlenaSpec : DescribeSpec({
    isolationMode = IsolationMode.InstancePerLeaf

    val equipo = EquipoBase(alquilado = false, costo = 100.0)
    val equipoAlquilado = EquipoBase(alquilado = true, costo = 100.0)
    val equipoDedicado = EquipoBuilder(equipo).conDedicacionPlena().build()
    val djDedicado = Dj(
        comienzoActividades = LocalDate.of(2020, 1, 1),
        saldoDisponible = 100.0,
        dedicacionPlena = true
    )
    val djNoDedicado = Dj(
        comienzoActividades = LocalDate.of(2023, 8, 26),
        saldoDisponible = 100.0,
        dedicacionPlena = false
    )
    val djPobre = Dj(
        comienzoActividades = LocalDate.of(2000, 4, 21),
        saldoDisponible = 0.0,
        dedicacionPlena = true
    )
    val djSinSuerte = Dj(
        comienzoActividades = LocalDate.of(2000, 4, 21),
        saldoDisponible = 9999.0,
        dedicacionPlena = true
    )
    it("Puede alquilar un equipo que solo acepta djs con dedicacion plena") {
        equipoDedicado.alquilarA(djDedicado)
        djDedicado.saldoDisponible() shouldBe 0.0
    }
    it("Un dj no puede alquilar un equipo que solo acepta djs con dedicacion plena") {
        shouldThrow<BusinessError> { equipoDedicado.alquilarA(djNoDedicado) }
    }
    it("Un dj pobre no puede comprar por mas dedicacion plena que tenga") {
        shouldThrow<BusinessError> { equipoDedicado.alquilarA(djPobre) }
    }
    it("Un dj sin suerte tiene plata y dedicacion plena pero no hay equipo disponible") {
        shouldThrow<BusinessError> { equipoAlquilado.alquilarA(djSinSuerte) }
    }
})