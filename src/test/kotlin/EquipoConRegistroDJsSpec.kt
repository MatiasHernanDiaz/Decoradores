import ar.edu.unsam.algo2.Dj
import ar.edu.unsam.algo2.EquipoBase
import ar.edu.unsam.algo2.EquipoBuilder
import ar.edu.unsam.algo2.EquipoConRegistroDJs
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate


class EquipoConRegistroDJsSpec : DescribeSpec({
    isolationMode = IsolationMode.SingleInstance

    // Arrange
    val equipoConRegistro1 = EquipoBuilder(EquipoBase(false, 200.0))
        .conRegistroDJs()
        .build()

    val dj1 = Dj(
        comienzoActividades = LocalDate.of(2000, 1, 1),
        saldoDisponible = 2000.0
    )

    val dj2 = Dj(
        comienzoActividades = LocalDate.of(2000, 1, 1),
        saldoDisponible = 2000.0
    )


    describe("Dados diversos alquileres del mismo equipo, por parte de dos Djs distintos"){
        // Act
        equipoConRegistro1.alquilarA(dj1)

        // Assert
        it("El primer alquiler debe registrar al dj") {
            EquipoConRegistroDJs.cantidadAlquileres(dj1) shouldBe 1
        }

        it("El segundo alquiler debe lanzar una excepción, porque el equipo ya está alquilado") {
            shouldThrow<RuntimeException> { equipoConRegistro1.alquilarA(dj2) }
        }

        it("Si lo desalquilo y lo vuelve a alquilar el primer DJ, el registro devuelve dos alquieres") {
            // Act
            equipoConRegistro1.alquilado = false
            equipoConRegistro1.alquilarA(dj1)
            EquipoConRegistroDJs.cantidadAlquileres(dj1) shouldBe 2
        }
    }
})