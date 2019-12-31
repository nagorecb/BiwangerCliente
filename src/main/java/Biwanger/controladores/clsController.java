package Biwanger.controladores;
import Biwanger.objetosDominio.*;
import Biwanger.vistas.*;
import Biwanger.Remote.clsServiceLocator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.GenericType;

public class clsController
{
    private clsServiceLocator sl;

    public clsController(String hostname, String port)
    {
        sl = new clsServiceLocator();
        sl.setService(hostname, port);

        frmInicioSesion GUI = new frmInicioSesion(this);
        GUI.setVisible(true);
    }

    public clsUsuario inicioSesion(String email, String password)
    {
        clsUsuario usuario = new clsUsuario ();
        usuario.setEmail(email);
        usuario.setPassword(password);

        WebTarget postRequestController = sl.getservice().path("resource/login");
        Invocation.Builder invocationBuilder = postRequestController.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(usuario, MediaType.APPLICATION_JSON));

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

    public String registro(String email, String password)
    {
        clsUsuario usuario = new clsUsuario ();
        usuario.setPassword(password);
        usuario.setEmail(email);
        usuario.setFondos(1000000);

        WebTarget postRequestController = sl.getservice().path("resource/registro");
        Invocation.Builder invocationBuilder = postRequestController.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(usuario, MediaType.APPLICATION_JSON));

        if (response.getStatus() == Status.OK.getStatusCode())
        {
            System.out.println("Todo OK");
        }
        else
        {
            System.out.println("No OK");
        }

        return response.readEntity(String.class);
    }
    
    public ArrayList<clsUsuario> premiarTresMejores()
    {
        WebTarget postRequestController = sl.getservice().path("resource/premiarTresMejores");
        Invocation.Builder invocationBuilder = postRequestController.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();

        clsUsuarioLista lUsuarios = response.readEntity(clsUsuarioLista.class);

        if(response.getStatus() != Status.OK.getStatusCode())
        {
            System.out.println("No OK");
        }
        else
        {
            System.out.println("Todo OK");
        }
        return lUsuarios.getlUsuarios();
    }

    public void modificarAlineacion(ArrayList<clsJugador> plantilla)
    {
        clsJugadorLista param = new clsJugadorLista(plantilla);

        WebTarget postRequestController = sl.getservice().path("resource/modificarAlineacion");
        Invocation.Builder invocationBuilder = postRequestController.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(param, MediaType.APPLICATION_JSON));

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

        clsJugadorLista lJugadores = response.readEntity(clsJugadorLista.class);

        if (response.getStatus() == Status.OK.getStatusCode())
        {
            System.out.println("Todo OK");
        }
        else
        {
            System.out.println("No OK");
        }

        return lJugadores.getlJugadores();
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
        jugadorVenta.setPrecio(precio);

        WebTarget postRequestController = sl.getservice().path("resource/VenderJugador");
        Invocation.Builder invocationBuilder = postRequestController.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(jugadorVenta, MediaType.APPLICATION_JSON));
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
        String aux = nuevoJugador.getNombre() +","+ nuevoJugador.getPosicion() +","+ nuevoJugador.getPrecio() +","+ nuevoJugador.getEquipo() +","+ nuevoJugador.isEnVenta() +","+ nuevoJugador.getEstado();
	    WebTarget postRequestController = sl.getservice().path("resource/crearJugador");
        Invocation.Builder invocationBuilder = postRequestController.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(aux, MediaType.APPLICATION_JSON));

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
        WebTarget getRequestController = sl.getservice().path("resource/obtenerTodosUsuarios");
        Invocation.Builder invocationBuilder = getRequestController.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();

        clsUsuarioLista lUsuarios = response.readEntity(clsUsuarioLista.class);

        if (response.getStatus() == Status.OK.getStatusCode())
        {
            System.out.println("Todo OK");
        }
        else
        {
            System.out.println("No OK");
        }

        return lUsuarios.getlUsuarios();
    }

    public ArrayList<clsJugador> obtenerTodosJugadores()
    {
        WebTarget getRequestController = sl.getservice().path("resource/obtenerTodosJugadores");
        Invocation.Builder invocationBuilder = getRequestController.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();

        clsJugadorLista lJugadores = response.readEntity(clsJugadorLista.class);

        if (response.getStatus() == Status.OK.getStatusCode())
        {
            System.out.println("Todo OK");
        }
        else
        {
            System.out.println("No OK");
        }

        return lJugadores.getlJugadores();
    }

    public ArrayList<clsJugador> clasificaconEquipo(String email)
    {
        WebTarget getRequestController = sl.getservice().path("resource/clasificacionEquipo");
        Invocation.Builder invocationBuilder = getRequestController.request(MediaType.APPLICATION_JSON);
        Response response =  invocationBuilder.post(Entity.entity(email, MediaType.APPLICATION_JSON));

        clsJugadorLista lJugadores = response.readEntity(clsJugadorLista.class);

        if (response.getStatus() == Status.OK.getStatusCode())
        {
            System.out.println("Todo OK");
        }
        else
        {
            System.out.println("No OK");
        }

        return lJugadores.getlJugadores();
    }

    public ArrayList<clsUsuario> clasificacionUsuarios()
    {
        WebTarget getRequestController = sl.getservice().path("resource/clasificacionUsuarios");
        Invocation.Builder invocationBuilder = getRequestController.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();

        clsUsuarioLista lUsuarios = response.readEntity(clsUsuarioLista.class);

        if (response.getStatus() == Status.OK.getStatusCode())
        {
            System.out.println("Todo OK");
        }
        else
        {
            System.out.println("No OK");
        }

        return lUsuarios.getlUsuarios();
    }

    public ArrayList<clsJugador> obtenerPlantilla(clsUsuario usuario)
    {
        WebTarget getRequestController = sl.getservice().path("resource/obtenerPlantilla");
        Invocation.Builder invocationBuilder = getRequestController.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(usuario.getEmail(), MediaType.APPLICATION_JSON));

        clsJugadorLista lJugadores= response.readEntity(clsJugadorLista.class);

        if (response.getStatus() == Status.OK.getStatusCode())
        {
            System.out.println("Todo OK");
        }
        else
        {
            System.out.println("No OK");
        }

        return lJugadores.getlJugadores();
    }
}
