package system;

import Util.DataBase;

public class Main {

    public static void main(String[] args) {

        DataBase dataBase = new DataBase();

        Clt clt  = new Clt("João", 'M', 12989148922L, "17/05/2004", 5500, 50, 70 );

        Pj pj = new Pj("Eduardo", 'M', 22489891192L, "28/8/1976", 7000);

        System.out.println(clt);
        System.out.println();
        System.out.println(pj);

        dataBase.cadastrar(clt);
        dataBase.cadastrar(pj);

        System.out.println(dataBase.retornarLista());

    }
}
