package system;

import util.DataBase;
import util.Menu;

//Utilizado apenas caso queira iniciar cadastrando registros diretamente no método Main
import java.util.Date;

public class Main {

    public static void main(String[] args){
        DataBase dataBase = new DataBase();
        Menu menu = new Menu(dataBase);

        /*Caso queira iniciar com registros pré cadastrados para testes

            Date data = new Date(1084752000000L);
            // 17/05/2004

            Clt clt  = new Clt("Teste Clt", 'M', 12345678900L, data, 5500, 850, 850 );
            Pj pj = new Pj("Teste Pj", 'M', 98765432100L, data, 5500);

            dataBase.cadastrar(clt);
            dataBase.cadastrar(pj);
         //*/

        menu.abrirMenu();
    }
}
