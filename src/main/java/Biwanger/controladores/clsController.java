package Controller;
import Swing.*;

public class clsController
{
    //private clsServiceLocator sl = null;

    public clsController() //throws RemoteException
    {
        //sl = new clsServiceLocator();
        //sl.setService();

        //Habra que poner la ventana principal cuando este lista
        //frmInicio GUI = new frmInicio(this);
        frmInicioSesion GUI = new frmInicioSesion(this);
        GUI.setVisible(true);

    }

    public String inicioSesion(email, password)
    {
        String resultado = "";
        //sl.getservice.inicioSesion(email, password)
        return resultado;
    }

}
