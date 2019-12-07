package Biwanger.objetosDominio;

import java.util.ArrayList;

public class clsPujaLista
{
    private ArrayList<clsPuja> lPujas;

    public clsPujaLista() {}

    public clsPujaLista(ArrayList param)
    {
        lPujas = param;
    }

    public ArrayList<clsPuja> getlPujas()
    {
        return lPujas;
    }

    public void setlPujas(ArrayList<clsPuja> lPujas)
    {
        this.lPujas = lPujas;
    }
}
