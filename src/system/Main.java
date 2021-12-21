package system;

import util.DataBase;
import util.Menu;

public class Main {

    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        Menu menu = new Menu(dataBase);

        /*Caso queira iniciar com registros pré cadastrados para testes

        Clt clt  = new Clt("Teste Clt", 'M', 12345678900L, "17/05/2004", 5500, 850, 850 );
        Pj pj = new Pj("Teste Pj", 'M', 98765432100L, "17/05/2004", 5500);

        dataBase.cadastrar(clt);
        dataBase.cadastrar(pj);
         */

        menu.abrirMenu();

    }
}
