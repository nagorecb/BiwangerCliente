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

/**
 * @brief Clase para la conexión y comunicación con la parte servidora del proyecto
 */
public class clsController
{
    private clsServiceLocator sl;

    /**
     * Constructor de la clase clsController en el que se inicializa el Service Locator, indicandole el hostname y el puerto.
     * Asimismo, arranca la aplicación en la vista "Inicio de sesión".
     *
     * @param hostname Nombre del host
     * @param port Puerto
     */
    public clsController(String hostname, String port)
    {
        sl = new clsServiceLocator();
        sl.setService(hostname, port);

        frmInicioSesion GUI = new frmInicioSesion(this);
        GUI.setVisible(true);
    }

    /**
     * Método para el inicio de sesión en la aplicación, indicando el email introducido por el usuario y la contraseña.
     *
     * @param email Dirección de correo electrónico identificativo del usuario
     * @param password Contraseña
     * @return usuario correspondiente al email y contraseña indicados
     */
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

    /**
     * Método para el registro en la aplicación, pasando como parámetro el email y contraseña indicados por el usuario.
     *
     * @param email Dirección de correo electrónico identificativo del usuario
     * @param password Contraseña
     * @return texto que indica si el registro se ha realizado correctamente o no
     */
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

    /**
     * Método que premia a los tres mejores usuarios de la jornada
     *
     * @return lista de usuarios del sistema
     */
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

    /**
     * Método que modifica la alineación de un usuario, indicando los jugadores que este desea que formen parte
     * de la alineación.
     * @param plantilla Lista de jugadores
     */
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

    /**
     * Método que modifica la formación de un usuario dado
     * @param usuario Usuario cuya formación se procederá a modificar
     */
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

    /**
     * Metodo para mostrar los jugadores que forman parte actualmente del mercado de fichajes
     * @return Lista de jugadores que están actualmente en venta
     */
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

    /**
     * Método que registra una nueva puja en el sistema
     * @param puja Puja a guardar
     * @return Booleano que indica si la puja se ha almacenado correctamente en el sistema o no
     */
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

    /**
     * Método para proceder a la venta de un jugador, al precio indicado por el usuario
     * @param precio Precio de venta del jugador
     * @param jugadorVenta Jugador que se desea poner a la venta
     */
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

    /**
     * Método para registrar cambios en los jugadores tras la jornada
     * @param jugador Jugador al que se le añadirán los puntos
     * @param puntos puntos a sumar
     * @param asistencias asistenacias a sumar
     * @param goles goles a sumar
     * @param partidos partidos a sumar
     * @param TA tarjetas amarillas a sumar
     * @param TR tarjetas rojas a sumar
     */
    public void anadirPuntos(clsJugador jugador, int puntos, int asistencias, int goles, int partidos, int TA, int TR)
    {
    	WebTarget postRequestController = sl.getservice().path("resource/anadirPuntos");
//		Invocation.Builder invocationBuilder = postRequestController.request(MediaType.APPLICATION_JSON);
    	//si no funciona: tupla <clsJugador, Integer>
		MultivaluedMap <String, String> formData = new MultivaluedHashMap<String, String>();
	    formData.add("idJugador", String.valueOf(jugador.getId()));
	    formData.add("puntos", String.valueOf(puntos));
        formData.add("asistencias", String.valueOf(asistencias));
        formData.add("goles", String.valueOf(goles));
        formData.add("partidos", String.valueOf(partidos));
        formData.add("TA", String.valueOf(TA));
        formData.add("TR", String.valueOf(TR));
		Response response = postRequestController.request().post(Entity.form(formData));
		
		if (response.getStatus() != Status.OK.getStatusCode()) {
			System.out.println("Error connecting with the server. Code: " + response.getStatus());
		} else {
			System.out.println("ok");
		}
    }
    /**
     * Método que se encarga de guardar en el sistema un nuevo jugador
     *
     * @param nuevoJugador Datos del nuevo jugador a guardar
     */
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
    /**
     * Método que devuelve la lista de usuarios registrados en el sistema
     *
     * @return Lista de usuarios del sistema
     */
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

    /**
     * Método que devuelve la lista de usuarios registrados en el sistema
     *
     * @return Lista de usuarios del sistema
     */
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

    /**
     * Método que realiza la petición para obtener la clasificación de jugadores de un usuario
     * @param email Dirección de correo electrónico identificativo del usuario
     * @return Lista de jugadores del usuario ordenada
     */
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
    /**
     * Método que realiza la petición para obtener la clasificación de los usuarios registrados en el sistema
     * @return Lista de usuarios ordenada en base a su puntuación actual
     */
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
    /**
     * Método que realiza la petición para obtener la plantilla de un usuario dado
     * @param usuario Usuario del que se desea obtener la plantilla
     * @return Lista de jugadores que conforman la plantilla
     */
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
