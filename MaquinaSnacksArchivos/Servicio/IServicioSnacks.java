package MaquinaSnacksArchivos.Servicio;
import java.util.List;
import MaquinaSnacksArchivos.dominio.Snack;

public interface IServicioSnacks {

    void agregarSnack(Snack snack);

    void mostrarSnacks();
    
    List<Snack> getSnacks();
}
