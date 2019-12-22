package Biwanger.comun;

import Biwanger.controladores.clsController;
import Biwanger.objetosDominio.clsUsuario;
import org.glassfish.jersey.servlet.ServletContainer;

import java.util.ArrayList;

final class clsMain {

    public static void main(String[] args)
    {
        clsController controller = new clsController(args[0], args[1]);
        controller.hardcode();
    }
}