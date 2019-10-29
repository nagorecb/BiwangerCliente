package Controller;
import Swing.*;
import Remote.*;

public class clsController
{
    private clsServiceLocator sl = null;

    public clsController() //throws RemoteException
    {
        sl = new clsServiceLocator();
        sl.setService();

        //Habra que poner la ventana principal cuando este lista
        //frmInicio GUI = new frmInicio(this);
        frmInicioSesion GUI = new frmInicioSesion(this);
        GUI.setVisible(true);
    }

    public String inicioSesion(email, password)
    {
        WebTarget postRequestController = sl.getService().path("resource/LoginRequest");
        Invocation.Builder invocationBuilder = postRequestController.request(MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.post(Entity.entity(email, password, MediaType.APPLICATION_JSON));

        if (response.getStatus() != Status.OK.getStatusCode())
        {
            System.out.println("Todo no OK");
            throw new Exception("no OK");
        }
        else
        {
            System.out.println("Todo OK");
            String resultado = response.readEntity(String.class);
        }
        return resultado;
    }

}
