package Remote;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class clsServiceLocator
{
    private Client client;
    private WebTarget webTarget;

    public setService(String hostname, String port)
    {
        client = ClientBuilder.newClient();
        webTarget = client.target(String.format("http://%s:%s/rest", hostname, port));
    }

    public WebTarget getservice()
    {
        return webTarget;
    }

}