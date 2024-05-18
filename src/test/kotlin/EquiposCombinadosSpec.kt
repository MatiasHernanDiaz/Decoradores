import ar.edu.unsam.algo2.Dj
import ar.edu.unsam.algo2.EquipoBase
import ar.edu.unsam.algo2.EquipoBuilder
import ar.edu.unsam.algo2.EquipoConRegistroDJs
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class EquiposCombinadosSpec : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    val equipoCombinacion1 = EquipoBuilder(EquipoBase(false, 200.0))
        .conDedicacionPlena()
        .sofisticado(5)
        .conRegistroDJs()
        .conPromocion(20.0)
        .build()

    val equipoCombinacion2 = EquipoBuilder(EquipoBase(false, 200.0))
        .sofisticado(5)
        .conRegistroDJs()
        .conDedicacionPlena()
        .conPromocion(20.0)
        .build()

    val equipoCombinacion3 = EquipoBuilder(EquipoBase(false, 200.0))
        .conRegistroDJs()
        .conDedicacionPlena()
        .conPromocion(20.0)
        .sofisticado(5)
        .build()

    val equipoCombinacion4 = EquipoBuilder(EquipoBase(false, 200.0))
        .conPromocion(20.0)
        .sofisticado(5)
        .conRegistroDJs()
        .conDedicacionPlena()
        .build()

    val dj1 = Dj(comienzoActividades = LocalDate.of(2018, 1, 1), saldoDisponible = 1000.0, dedicacionPlena = true)
    val dj2 = Dj(comienzoActividades = LocalDate.of(2022, 1, 1), saldoDisponible = 100.0)
    val dj3 = Dj(comienzoActividades = LocalDate.of(2018, 1, 1), saldoDisponible = 100.0)
    val dj4 = Dj(comienzoActividades = LocalDate.of(2022, 1, 1), saldoDisponible = 1000.0)

    describe("Se alquilan todos los combinados a dj1 y se prueban los resultados") {
        it("Como tiene dedicacion plena y años de experiencia, lo puede alquilar") {
            shouldNotThrow<RuntimeException> { equipoCombinacion1.alquilarA(dj1) }
        }
        it("el saldo disponible del DJ debe computar el reintegro") {
            equipoCombinacion1.alquilarA(dj1)
            equipoCombinacion2.alquilarA(dj1)
            dj1.saldoDisponible() shouldBe 1000 - 200 * 0.8 * 2
        }
        it("Como tiene dedicacion plena y años de experiencia, lo puede alquilar. Además, se comprueba el saldo y el registro de alquileres") {
            // Act
            equipoCombinacion2.alquilarA(dj1)
            equipoCombinacion3.alquilarA(dj1)
            equipoCombinacion4.alquilarA(dj1)

            // Assert
            EquipoConRegistroDJs.cantidadAlquileres(dj1) shouldBe 3
        }
    }
    describe("Se alquilan los combinados al resto de los djs, y fracasan por diferentes circunstancias") {
        it("Como no tiene dedicacion plena ni años de experiencia, no lo puede alquilar") {
            shouldThrow<RuntimeException> { equipoCombinacion2.alquilarA(dj2) }
        }
        it("Como no tiene años de experiencia, no lo puede alquilar") {
            shouldThrow<RuntimeException> { equipoCombinacion3.alquilarA(dj3) }
        }
        it("Como no tiene dedicacion plena, no lo puede alquilar") {
            shouldThrow<RuntimeException> { equipoCombinacion4.alquilarA(dj4) }
        }
    }

})