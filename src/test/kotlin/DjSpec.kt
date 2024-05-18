import ar.edu.unsam.algo2.Dj
import ar.edu.unsam.algo2.Equipo
import ar.edu.unsam.algo2.EquipoBase
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class DjSpec: DescribeSpec({
    isolationMode = IsolationMode.InstancePerLeaf

    describe("Dado un DJ sin equipos alquilados"){
        //Arrange
        val equipo = EquipoBase(alquilado = false, costo = 100.0)
        val dj1 = Dj(comienzoActividades = LocalDate.of(1990, 1, 1), saldoDisponible = 0.0)

        it("Varifica que no puede comprar"){
            //Assert
            dj1.puedeAlquilar(equipo) shouldBe false
        }

        it("Verifica que puede comprar cuando aumenta su saldo"){
            //act
            dj1.aumentarSaldo(10000.0)
            //assert
            dj1.puedeAlquilar(equipo) shouldBe true
        }

        it("Con saldo, alquila un equipo"){
            //Act
            dj1.aumentarSaldo(10000.0)
            dj1.alquilar(equipo)
            //assert
            dj1.saldoDisponible() shouldBe 9900.0
        }

        it("El dj deberia tener tantos años de experiencia"){
            dj1.aniosDeExperiencia() shouldBe 34 // deberia ser dinamico
        }
    }
})