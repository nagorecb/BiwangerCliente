package main.java.Biwanger.Remote;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class clsServiceLocator
{
    private Client client;
    private WebTarget webTarget;

    public void setService(String hostname, String port)
    {
        client = ClientBuilder.newClient();
        webTarget = client.target(String.format("http://%s:%s/rest", hostname, port));
    }

    public WebTarget getservice()
    {
        return webTarget;
    }

}