package Biwanger.controladores;
import Biwanger.objetosDominio.clsJugador;
import Biwanger.objetosDominio.clsPuja;
import Biwanger.objetosDominio.clsUsuario;
import Biwanger.vistas.*;
import Biwanger.Remote.clsServiceLocator;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
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

        frmInicioSesion GUI = new frmInicioSesion(this);
    }

    public clsUsuario inicioSesion(String email, String password)
    {
        clsUsuario usuario = new clsUsuario ();
        usuario.setEmail(email);
        usuario.setPassword(password);

        WebTarget postRequestController = sl.getservice().path("resource/login");
        Invocation.Builder invocationBuilder = postRequestController.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity( usuario, MediaType.APPLICATION_JSON));

        clsUsuario resultado = response.readEntity(clsUsuario.class);

        if(response.getStatus() != Status.OK.getStatusCode())
        {
            System.out.println("No OK");
        }
        else
        {
            System.out.println("Todo OK");
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

        if (response.getStatus() == Status.OK.getStatusCode())
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
    
    public ArrayList<clsUsuario> premiarTresMejores()
    {
        ArrayList<clsUsuario> usuarios;

        WebTarget postRequestController = sl.getservice().path("resource/premiarTresMejores");
        Invocation.Builder invocationBuilder = postRequestController.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();

        usuarios = response.readEntity(ArrayList.class);

        if(response.getStatus() != Status.OK.getStatusCode())
        {
            System.out.println("No OK");
        }
        else
        {
            System.out.println("Todo OK");
        }
        return usuarios;
    }

    public void modificarAlineacion(clsUsuario usuario)
    {
        WebTarget postRequestController = sl.getservice().path("resource/modificarAlineacion");
        Invocation.Builder invocationBuilder = postRequestController.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(usuario, MediaType.APPLICATION_JSON));

        if(response.getStatus() != Status.OK.getStatusCode())
        {
            System.out.println("No OK");
        }
        else
        {
            System.out.println("Todo OK");
        }
    }


    public void modificarFormacion (clsUsuario usuario)
    {
        WebTarget postRequestController = sl.getservice().path("resource/modificarFormacion");
        Invocation.Builder invocationBuilder = postRequestController.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity( usuario, MediaType.APPLICATION_JSON));

        if(response.getStatus() != Status.OK.getStatusCode())
        {
            System.out.println("No OK");
        }
        else
        {
            System.out.println("Todo OK");
        }
    }

    public ArrayList<clsJugador> MostrarMercado()
    {
        WebTarget postRequestController = sl.getservice().path("resource/MostrarMercado");
        Invocation.Builder invocationBuilder = postRequestController.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();

        ArrayList<clsJugador> lJugadores = null;

        if (response.getStatus() == Status.OK.getStatusCode())
        {
            System.out.println("Todo OK");
        }
        else
        {
            System.out.println("No OK");
            lJugadores = response.readEntity(ArrayList.class);
        }

        return lJugadores;
    }

    public boolean Pujar(clsPuja puja)
    {
        WebTarget postRequestController = sl.getservice().path("resource/pujar");
        Invocation.Builder invocationBuilder = postRequestController.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(puja, MediaType.APPLICATION_JSON));

        if (response.getStatus() == Status.OK.getStatusCode())
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

    public void venderJugador(double precio, clsJugador jugadorVenta)
    {

        WebTarget postRequestController = sl.getservice().path("resource/VenderJugador");
        Invocation.Builder invocationBuilder = postRequestController.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        if (response.getStatus() == Status.OK.getStatusCode())
        {
            System.out.println("Todo OK");
        }
        else
        {
            System.out.println("No OK");
        }
    }
    
    public void anadirPuntos(clsJugador jugador, int puntos)
    {
    	WebTarget postRequestController = sl.getservice().path("resource/anadirPuntos");
//		Invocation.Builder invocationBuilder = postRequestController.request(MediaType.APPLICATION_JSON);
    	//si no funciona: tupla <clsJugador, Integer>
		MultivaluedMap <String, String> formData = new MultivaluedHashMap<String, String>();
	    formData.add("idJugador", String.valueOf(jugador.getId()));
	    formData.add("puntos", String.valueOf(puntos));
		Response response = postRequestController.request().post(Entity.form(formData));
		
		if (response.getStatus() != Status.OK.getStatusCode()) {
			System.out.println("Error connecting with the server. Code: " + response.getStatus());
		} else {
			System.out.println("ok");
		}
    }

	public void guardarNuevoJugador(clsJugador nuevoJugador) 
	{
        WebTarget postRequestController = sl.getservice().path("resource/crearJugador");
        Invocation.Builder invocationBuilder = postRequestController.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(nuevoJugador, MediaType.APPLICATION_JSON));

        if (response.getStatus() != Status.OK.getStatusCode())
        {
            System.out.println("Todo OK");    
        }
        else
        {
            System.out.println("No OK");
        }
	}

	public ArrayList<clsUsuario> obtenerTodosUsuarios()
    {
        WebTarget postRequestController = sl.getservice().path("resource/obtenerTodosUsuarios");
        Invocation.Builder invocationBuilder = postRequestController.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();

        ArrayList<clsUsuario> lUsuarios = null;

        if (response.getStatus() == Status.OK.getStatusCode())
        {
            System.out.println("Todo OK");
        }
        else
        {
            System.out.println("No OK");
            lUsuarios = response.readEntity(ArrayList.class);
        }

        return lUsuarios;
    }

}
