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
import static org.mockito.Mockito.*;

// *
// * Unit test for clsController.
// */
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
        mockedController.registro(mockedUsuario.getEmail(),mockedUsuario.getPassword());
        Mockito.verify(mockedController.inicioSesion("email","password"));

        //De primeras no funciona, devuelve false
        assertEquals(false, mockedController.inicioSesion("email","password"));
        //Con stubbing funcionaria y devuelve true
        Mockito.when(mockedController.registro("email","password")).thenReturn("OK");
        assertEquals("OK", mockedController.inicioSesion("email","password"));
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

    public void test_premiarTresMejores()
    {
       mockedListUsuarios=mockedController.premiarTresMejores();
       Mockito.verify(mockedController.premiarTresMejores(),times(1));
       assertEquals(0, mockedListUsuarios.size());
    }


    public void test_mostrarMercado()
    {
        mockedListJugadores = mockedController.MostrarMercado();

        Mockito.verify(mockedController.MostrarMercado());
        assertEquals(0,mockedListJugadores.size());

        //con stub
        mockedListJugadores.add(mockedJugador);
        Mockito.when(mockedController.MostrarMercado()).thenReturn(mockedListJugadores);
        assertEquals(1, mockedListJugadores.size());
    }

    public void test_puja()
    {
        //la puja no funciona
        boolean buleano = mockedController.Pujar(mockedPuja);
        assertEquals(false,buleano);
        //con stub funciona
        Mockito.when(mockedController.Pujar(mockedPuja)).thenReturn(true);
        assertEquals(true, mockedController.Pujar(mockedPuja));
    }

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
