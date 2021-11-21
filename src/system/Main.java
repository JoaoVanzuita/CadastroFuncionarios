package system;

import Util.DataBase;
import Util.Menu;

public class Main {

    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        Menu menu = new Menu(dataBase);

        Clt clt  = new Clt("João", 'M', 12989148922L, "17/05/2004", 5500, 50, 70 );

        Pj pj = new Pj("Eduardo", 'M', 22489891192L, "28/8/1976", 7000);

        menu.abrirMenu();

    //System.out.println(dataBase.retornarLista());

    //dataBase.editarCadastro(12989148922L, 0);


    //System.out.println(clt);

    }
}
