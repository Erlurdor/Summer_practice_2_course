package Main_Classes;

import java.util.Random;

public class Table {
    private int Num_Of_Players;         //количество игроков
    private int Num_Active_Role;        //количество активных ролей
    private int Role_Mafia;             //количество ролей Мафии
    private int Role_Doctor;            //количество ролей Доктора
    private int Role_Policeman;         //количество ролей Коммисара
    private int Num_All_Role;           //количество активных ролей всего: 0 - Мафия, 1 - Доктор, 2 - Коммисар

    //Начало игры
    public void Start_Game() {
        Num_Of_Players = 10;
        Num_All_Role = 3;

        //определение количества активных ролей
        Num_Active_Role = Num_Of_Players / 2;
        Role_Mafia = Num_Active_Role / 2;
        Role_Doctor = Role_Mafia / 2;
        Role_Policeman = 1;

        //создание массива игроков
        Player[] Bots = new Player[Num_Of_Players];
        Random random = new Random();

        //первичное заполнение игроков. Заполняются поля: Удача, Имя.
        for (int i = 0; i < Num_Of_Players; i++)
        {
            int Temp_Luck = random.nextInt(98) + 1;
            String Temp_Name = "Bot " + String.valueOf(i + 1);

            Bots[i] = new Player();
            Bots[i].Init(Temp_Luck, Temp_Name);
        }

        //вычисляется новое количество активных ролей. Это нужно из-за того, что при распределении может остатся неиспользованная (-ые) активная(-ые) роль(-и)
        Num_Active_Role = Role_Mafia + Role_Doctor + Role_Policeman;

        //распределяю каждую активную роль
        for (int i = 0; i < Num_Active_Role; i++)
        {
            int Temp;           //временная переменная. Служит для определения номера активной роли
            Temp = random.nextInt(Num_All_Role);

            switch (Temp)
            {
                case 0:
                {
                    //Bots[i].setRole();
                    break;
                }

            }


            //Bots[random.nextInt(Num_Of_Players)]


        }




    }
}
