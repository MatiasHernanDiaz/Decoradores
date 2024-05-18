import ar.edu.unsam.algo2.Dj
import ar.edu.unsam.algo2.EquipoBase
import ar.edu.unsam.algo2.EquipoBuilder
import ar.edu.unsam.algo2.EquipoSofisticado
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class EquipoSofisticadoSpec: DescribeSpec ({
    isolationMode = IsolationMode.InstancePerLeaf
    //Arange
    val equipo = EquipoBase(alquilado = false, costo = 100.0)
    val equipoSofisticado30anios = EquipoBuilder(equipo)
        .sofisticado(30)
        .build()
    val equipoSofisticado2anios = EquipoBuilder(equipo)
        .sofisticado(2)
        .build()
    val djCon4AniosExp = Dj(comienzoActividades = LocalDate.of(2020, 1, 1), saldoDisponible = 100.0)

    describe("Un Dj con mucha experiencia"){
        it("Intenta alquilar un equipo sofisticado de poca experiencia"){
            //Act
            equipoSofisticado2anios.alquilarA(djCon4AniosExp)
            //Assert
            djCon4AniosExp.saldoDisponible() shouldBe 0.0
        }
        it("Intenta alquilar un equipo y de mayor costo"){
            //Act
            equipoSofisticado2anios.establecerCosto(costo = 101.0)
            //Assert
            shouldThrow<Exception> { equipoSofisticado2anios.alquilarA(djCon4AniosExp) }
        }

        it("Intenta alquilar un equipo de mayor experiencia"){
            //equipoSofisticado30anios.alquilarA(djCon4AniosExp)
            //djCon4AniosExp.saldoDisponible() shouldBe 0.0
            shouldThrow<Exception> { equipoSofisticado30anios.alquilarA(djCon4AniosExp) }
        }
    }
})

