package Biwanger.Remote;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *@brief Clase para obtener todos los servicios que utiliza la aplicación
 *
 */
public class clsServiceLocator
{
    private Client client;
    private WebTarget webTarget;

    /**
     * Método encargado de inicializar el servicio para poder conectar con el server al que se le realizarán las peticiones
     * @param hostname Nombre del host
     * @param port Puerto
     */
    public void setService(String hostname, String port)
    {
        client = ClientBuilder.newClient();
        webTarget = client.target(String.format("http://%s:%s/rest", hostname, port));
    }

    /**
     * Metodo que devuelve el servicio utilizado para conectar con el server al que se le realizarán las peticiones
     * @return Servicio para la conexión con el server
     */
    public WebTarget getservice()
    {
        return webTarget;
    }

}