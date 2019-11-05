package Biwanger.controladores;
import Biwanger.vistas.*;
import Biwanger.Remote.clsServiceLocator;
import Biwanger.objetosDominio.clsUsuario;

import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class clsController
{
    private clsServiceLocator sl = null;

    public clsController() //throws RemoteException
    {
        sl = new clsServiceLocator();
        String hostname ="";
        String port = "";
        sl.setService(hostname, port);

        //Habra que poner la ventana principal cuando este lista
        //frmInicio GUI = new frmInicio(this);
        frmInicioSesion GUI = new frmInicioSesion(this);
        GUI.setVisible(true);
    }

    public String inicioSesion(String email, String password)
    {
        clsUsuario usuario = new clsUsuario ();
        usuario.setEmail(email);
        usuario.setPassword(password);

        WebTarget postRequestController = sl.getservice().path("resource/login");
        Invocation.Builder invocationBuilder = postRequestController.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity( usuario, MediaType.APPLICATION_JSON));
        String resultado = "";

        if(response.getStatus() != Status.OK.getStatusCode())
        {
            System.out.println("No OK");
        }
        else
        {
            System.out.println("Todo OK");
            resultado = response.readEntity(String.class);
        }
        return resultado;
    }

    public boolean registro(String email, String password)
    {
        clsUsuario usuario = new clsUsuario ();
        usuario.setPassword(password);
        usuario.setEmail(email);
        WebTarget postRequestController = sl.getservice().path("resource/registroRequest");
        Invocation.Builder invocationBuilder = postRequestController.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(usuario, MediaType.APPLICATION_JSON));

        if (response.getStatus() != Status.OK.getStatusCode())
        {
            System.out.println("Todo OK");
            
            return true;
        }
        else
        {
            System.out.println("No OK");

            return false;
        }
    }
    
    public Response PremiarTresMejores()
    {
        WebTarget postRequestController = sl.getservice().path("resource/premiarTresMejores");
        Invocation.Builder invocationBuilder = postRequestController.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();

        return response;
    }
}
