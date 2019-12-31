package Biwanger;

import Biwanger.controladores.clsController;
import Biwanger.objetosDominio.clsJugador;
import Biwanger.objetosDominio.clsPuja;
import Biwanger.objetosDominio.clsUsuario;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class clsControllerTest
{
    @Mock
    private clsUsuario mockedUsuario;

    @Mock
    private ArrayList<clsUsuario> mockedListUsuarios;

    @Mock
    private ArrayList<clsJugador> mockedListJugadores;

    @Mock
    private clsPuja mockedPuja;

    @Mock
    private clsJugador mockedJugador;

    @Mock
    private clsController mockedController;

    @Before
    public void init()
    {
        mockedUsuario.setEmail("email");
        mockedUsuario.setPassword("password");
        mockedUsuario.setFormacion("4-3-3");

        mockedJugador.setNombre("Messi");
        mockedListJugadores.add(mockedJugador);
    }
    @Test
    public void test_registro()
    {
        //Registro
        mockedController.registro("email","password");
        //De primeras no funciona, devuelve false
        assertEquals(null, mockedController.registro("email","password"));
        //Con stubbing funcionaria y devuelve true
        Mockito.when(mockedController.registro("email","password")).thenReturn("email");
        assertEquals("email", mockedController.registro("email","password"));
    }
    @Test
    public void test_inicioSesion()
    {
        //No devuelve nada
        assertEquals(null, mockedController.inicioSesion("email","password"));

        //Con stubbing, devuelve el usuario
        Mockito.when(mockedController.inicioSesion("email","password")).thenReturn(mockedUsuario);
        assertEquals(mockedUsuario, mockedController.inicioSesion("email","password"));
    }
    @Test
    public void test_premiarTresMejores()
    {
        ArrayList<clsUsuario>listaUsuarios=mockedController.premiarTresMejores();
        assertEquals(0, listaUsuarios.size());
    }
    @Test
    public void test_mostrarMercado()
    {
        ArrayList<clsJugador>listaJugadores = mockedController.MostrarMercado();
        assertEquals(0,listaJugadores.size());
        //con stub
        listaJugadores.add(mockedJugador);
        Mockito.when(mockedController.MostrarMercado()).thenReturn(listaJugadores);
        assertEquals(1, mockedController.MostrarMercado().size());
    }
    @Test
    public void test_puja()
    {
        //la puja no funciona
        boolean buleano = mockedController.Pujar(mockedPuja);
        assertEquals(false,buleano);
        //con stub funciona
        Mockito.when(mockedController.Pujar(mockedPuja)).thenReturn(true);
        assertEquals(true, mockedController.Pujar(mockedPuja));
    }
    @Test
    public void test_obtenerTodosUsuarios()
    {
        mockedListUsuarios = mockedController.obtenerTodosUsuarios();
        assertEquals(0,mockedListUsuarios.size());
        //con stub
        mockedListUsuarios.add(mockedUsuario);
        Mockito.when(mockedController.obtenerTodosUsuarios()).thenReturn(mockedListUsuarios);
        assertEquals(1, mockedListUsuarios.size());
    }
}
