package Biwanger.comun;

import Biwanger.controladores.clsController;
import Biwanger.objetosDominio.clsUsuario;
import org.glassfish.jersey.servlet.ServletContainer;

import java.util.ArrayList;

/**
 * La clase main desde el que se inicia el profile client. Lanza el controlador del cliente.
 */
final class clsMain
{
    /**
     * El método main desde el que se inicia el artefacto.
     *
     * @param args Recibe el server.hostname y server.port como parámetros.
     */
    public static void main(String[] args)
    {
        clsController controller = new clsController(args[0], args[1]);
    }
}