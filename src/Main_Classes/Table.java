package Main_Classes;

import java.io.Console;
import java.util.Random;

public class Table {
    private int Num_Of_Players;         //количество игроков
    private int Num_Active_Role;        //количество активных ролей
    private int Role_Mafia;             //количество ролей Мафии
    private int Role_Doctor;            //количество ролей Доктора
    private int Role_Policeman;         //количество ролей Коммисара
    //private int Num_All_Role;           //количество активных ролей всего: 0 - Мафия, 1 - Доктор, 2 - Коммисар
    private int[] Array_Of_Banned_Players;  //массив игроков, которые уже имеют активные роли
    private int Ptr_Of_AOBP;                //указатель на массив Array_Of_Banned_Players
    Player[] Bots;                          //массив игроков

    /*
    public void Display_Arr(int[] Arr, int Role)
    {
        for (int i = 0; i < Role; i++)
        {
            System.out.print(String.valueOf(Arr[i]) + " ");
        }
        System.out.print("\n");
    }
*/

    //Arr_Banned_Players - массив игроков, которые уже имеют активную роль, Arr_Ptr - указатель на Arr_Banned_Players, Role - количество ролей,
    //Value - значение, по которому будет создана соотв. карта,  Arr_Players - массив игроков
    public int Sets_Roles(int Role, int Value, int[] Arr_Roles, int Ptr_AR)
    {
        //Sets_Roles(Role_Doctor, 1, Arr_Doctor, Ptr_AD);
        Random Temp_random = new Random();

        for (int i = 0; i < Role; i++)
        {
            //В этом блоке Temp_Value служит временным хранилищем номера персонажа, которому присвоится активная роль
            int Temp_Value;

            Card card = new Card();

            switch (Value) {
                case 0: {
                    card = new Card_Mafia();
                    break;
                }

                case 1: {
                    card = new Card_Doctor();
                    break;
                }

                case 2: {
                    card = new Card_Policeman();
                    break;
                }
            }


            boolean Exit = false;           //переменная для того, чтобы сгенерировать нужного игрока
            do
            {
                Exit = false;

                Temp_Value = Temp_random.nextInt(Num_Of_Players);

                for (int j = 0; j < Ptr_Of_AOBP; j++)
                {
                    if (Temp_Value == Array_Of_Banned_Players[j])
                    {
                        Exit = true;
                        break;
                    }
                }
            } while (Exit);

            Array_Of_Banned_Players[Ptr_Of_AOBP++] = Temp_Value;
            Bots[Temp_Value].Set_Role(card);
            Arr_Roles[Ptr_AR++] = Temp_Value;
        }
        return Ptr_AR;
    }


    private int Refresh_Array_Witch_Role(int[] Arr, int Ptr) {
        //прохожу по всем элементам массива
        for (int i = 0; i < Ptr; i++)
        {
            //если персонажа убили
            if (Bots[Arr[i]].Get_Diead() == true)
            {
                //меняю местами убитого и живого, стоящего на последнем месте. Уменьшаю указатель
                Arr[i] = Arr[Ptr - 1];
                Arr[Ptr - 1] = 0;
                Ptr--;
                break;
            }
        }

        return Ptr;
    }


    //Начало игры
    public void Start_Game() {
        Num_Of_Players = 10;
        //Num_All_Role = 3;

        //определение количества активных ролей
        Num_Active_Role = Num_Of_Players / 2;
        Role_Mafia = Num_Active_Role / 2;
        Role_Doctor = Role_Mafia / 2;
        Role_Policeman = 1;

        //создание массива игроков
        Bots = new Player[Num_Of_Players];
        Random random = new Random();

        //первичное заполнение игроков. Заполняются поля: Удача, Имя.
        for (int i = 0; i < Num_Of_Players; i++)
        {
            int Temp_Luck = random.nextInt(98) + 1;
            String Temp_Name = "Bot " + String.valueOf(i + 1);

            Bots[i] = new Player();
            Bots[i].Init(Temp_Luck, Temp_Name);
        }

        //вычисляется новое количество активных ролей. Это нужно из-за того, что при распределении может остатся неиспользованная(-ые) активная(-ые) роль(-и)
        Num_Active_Role = Role_Mafia + Role_Doctor + Role_Policeman;

        //переменные, которые показывают. сколько ролей задействованно
//        int Num_Of_Mafia_Roles = 0;
//        int Num_Of_Doctor_Roles = 0;
//        int Num_Of_Policeman_Roles = 0;

        Array_Of_Banned_Players = new int[Num_Of_Players];
        Ptr_Of_AOBP = 0;

        for (int i = 0; i < Num_Of_Players; i++)
        {
            Array_Of_Banned_Players[i] = -1;
        }



        int Temp_Value;             //временная переменная
//        Card_Peaceful Temp_Card_Peaceful = new Card_Peaceful();
//        Player Test_PL = new Player();
//        Test_PL.Init(0, "test");


        /*
        for (int i = 0; i < Role_Mafia; i++)
        {
            //В этом блоке Temp_Value служит временным хранилищем номера персонажа, которому присвоится активная роль

            Card card = new Card_Mafia();

            boolean Exit = false;           //переменная для того, чтобы сгенерировать нужного игрока

            do
            {
                Temp_Value = random.nextInt(Num_Of_Players);

                for (int j = 0; j < Ptr_Of_AOBP; j++)
                {
                    if (Temp_Value == Array_Of_Banned_Players[j])
                    {
                        Exit = true;
                        break;
                    }
                }
            } while (Exit);

            Array_Of_Banned_Players[Ptr_Of_AOBP++] = Temp_Value;
            Bots[Temp_Value].Set_Role(card);
        }*/

        //Эти массивы содержат номера игроков с активной ролью
        int[] Arr_Mafia = new int[Role_Mafia];
        int Ptr_AM = 0;

        int[] Arr_Doctor = new int[Role_Doctor];
        int Ptr_AD = 0;

        int[] Arr_Policeman = new int[Role_Policeman];
        int Ptr_AP = 0;

        //распределяю каждую активную роль
        Ptr_AM = Sets_Roles(Role_Mafia, 0, Arr_Mafia, Ptr_AM);
        //Display_Arr(Arr_Mafia, Role_Mafia);

        Ptr_AD = Sets_Roles(Role_Doctor, 1, Arr_Doctor, Ptr_AD);
        //Display_Arr(Arr_Doctor, Role_Doctor);

        Ptr_AP = Sets_Roles(Role_Policeman, 2, Arr_Policeman, Ptr_AP);
        //Display_Arr(Arr_Policeman, Role_Policeman);

       // System.out.println("\n");


        //Основной цикл
        boolean Game_End = false;           //наступил ли конец игры
        int Num_Of_Days;                    //количество дней. Отсчет начинается с 1
        int Num_Of_Avile_Players;           //количество игроков, которые остались живы

        Num_Of_Days = 0;
        Num_Of_Avile_Players = Num_Of_Players;

        while (!Game_End)           //пока игра не закончена
        {
            Num_Of_Days++;

            //Ночь. Ходят только активные роли
            int Target_For_Doctor;              //цель, которую нужно будет лечить доктору

            //Ход Мафии

            //Выбор списка возможных кандидатов отправиться на тот свет
            int[] Array_For_Target_Of_Mafia = new int[Ptr_AM];
            int Ptr_AFTOM = 0;

            for (int j = 0; j < Ptr_AM; j++)
            {
                Array_For_Target_Of_Mafia[Ptr_AFTOM++] = Bots[Arr_Mafia[j]].Walk_At_Night(Bots, Num_Of_Players, Arr_Mafia[j]);

                //Player[] Bots, int Num_Of_Players, int[] Target
                //где Player[]] Bots - массив все игроков, Target - цель ()

            }

            //совершение выстрела. Финальный выбор цели
            int Target1 = random.nextInt(Ptr_AFTOM);
            int Target2 = random.nextInt(Ptr_AFTOM);

            //если у первой цели ниже показатель удачи, чем у второй
            if (Bots[Array_For_Target_Of_Mafia[Target1]].Get_Luck() < Bots[Array_For_Target_Of_Mafia[Target2]].Get_Luck())
            {
                Bots[Array_For_Target_Of_Mafia[Target1]].Set_Died(true);
                Target_For_Doctor = Array_For_Target_Of_Mafia[Target1];
            }
            else
            {
                //если у второй цели показатель удачи ниже, чем у первой
                if (Bots[Array_For_Target_Of_Mafia[Target1]].Get_Luck() > Bots[Array_For_Target_Of_Mafia[Target2]].Get_Luck())
                {
                    Bots[Array_For_Target_Of_Mafia[Target2]].Set_Died(true);
                    Target_For_Doctor = Array_For_Target_Of_Mafia[Target2];
                }
                else
                {
                    //у обоих персонажей показатель удачи раный. Умирает случайно один из них
                    Target1 = random.nextInt(2);
                    Bots[Array_For_Target_Of_Mafia[Target1]].Set_Died(true);
                    Target_For_Doctor = Array_For_Target_Of_Mafia[Target1];
                }
            }
            //System.out.print(Num_Of_Avile_Players - 1 + "   ");


            //Ход Доктора
            int Save;               //переменная, которую возвращает Доктор. Показывает, спас ли Доктор игрока, в которого выстрелила Мафия (0 - НЕ спас, 1 - спас).

            for (int i = 0; i < Ptr_AD; i++)
            {
                Save = Bots[Arr_Doctor[i]].Walk_At_Night(Bots, Arr_Doctor[i], Target_For_Doctor);
                if (Save == 0)
                {
                    Num_Of_Avile_Players--;

                    if (Bots[Target_For_Doctor].Get_Role() == "Doctor")
                        Ptr_AD = Refresh_Array_Witch_Role(Arr_Doctor, Ptr_AD);

                    if (Bots[Target_For_Doctor].Get_Role() == "Policeman")
                        Ptr_AP = Refresh_Array_Witch_Role(Arr_Policeman, Ptr_AP);
                }
            }
            //System.out.print(Num_Of_Avile_Players + "   ");
            //System.out.println(Num_Of_Avile_Players);


            //Ход Коммисара
            int Arrest;         //переменная, которая показывает, задержал ли Коммисар мафию ночью (0 - НЕ задержал, 1 - задержал)
            for (int i = 0; i < Ptr_AP; i++)
            {
                Arrest = Bots[Arr_Policeman[i]].Walk_At_Night(Bots, Num_Of_Players, Arr_Policeman[i]);

                if (Arrest == 1)
                {
                    Num_Of_Avile_Players--;
                    Ptr_AM = Refresh_Array_Witch_Role(Arr_Mafia, Ptr_AM);
                }
            }
            //System.out.println(Num_Of_Avile_Players);


            //День. Ходят все живые




            //System.out.println(Target);



            if (Num_Of_Days == 1)
                Game_End = true;
        }



/*
       for (int i = 0; i < Num_Of_Players; i++)
       {
           System.out.println(String.valueOf(Bots[i].Get_Role()));
       }
*/
//        for (int i = 0; i < Num_Active_Role; i++)
//        {
//            int Role_Number;           //временная переменная. Служит для определения номера активной роли
//            Role_Number = random.nextInt(Num_All_Role);





    }


}
