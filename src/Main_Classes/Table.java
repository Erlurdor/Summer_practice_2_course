package Main_Classes;

import sample.Controller;

import java.io.Console;
import java.lang.management.PlatformLoggingMXBean;
import java.util.Random;

public class Table {
    private static Table instance;
    private Table()    {
    }

    public static Table Get_Instance() {
        if (instance == null)
            instance = new Table();
        return instance;
    }

    private int Num_Of_Players;         //количество игроков
    private int Num_Active_Role;        //количество активных ролей
    private int Role_Mafia;             //количество ролей Мафии
    private int Role_Doctor;            //количество ролей Доктора
    private int Role_Policeman;         //количество ролей Коммисара
    //private int Num_All_Role;           //количество активных ролей всего: 0 - Мафия, 1 - Доктор, 2 - Коммисар
    private int[] Array_Of_Banned_Players;  //массив игроков, которые уже имеют активные роли
    private int Ptr_Of_AOBP;                //указатель на массив Array_Of_Banned_Players

    Player[] Bots;                          //массив игроков
    Random random;                          //функция рандома

    private int[] Arr_Mafia;
    private int Ptr_AM;

    private int[] Arr_Doctor;
    private int Ptr_AD;

    private int[] Arr_Policeman;
    private int Ptr_AP;

    private boolean Game_Over;              //конец ли игры. True - Конец игры
    private int Num_Of_Days;                //количество дней. Отсчет начинается с 1
    private int Num_Of_Avile_Players;       //количество игроков, которые остались живы

    private String Event_Mafia;             //что произошло после хода Мафии
    private String Event_Doctor;             //что произошло после хода Доктора
    private String Event_Policeman;             //что произошло после хода Комиссара
    private String Event_Votes;             //что произошло после Дневного голосования
    private String Victory;                 //победитель
    private String Cur_Time;                    //время суток

    /*
    private Player Target_Mafia;                //цель Мафии
    private Player Target_Doctor;
    private Player Target_Policeman;
*/
    Player[] Died;
    int Ptr_Died;
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


    private int Refresh_Array_With_Role(int[] Arr, int Ptr) {
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

    public void Init()
    {
        Num_Of_Players = 10;


        //определение количества активных ролей
        Num_Active_Role = Num_Of_Players / 2;
        Role_Mafia = Num_Active_Role / 2;
        Role_Doctor = Role_Mafia / 2;
        Role_Policeman = 1;

        //создание массива игроков
        Bots = new Player[Num_Of_Players];
        random = new Random();

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

        Array_Of_Banned_Players = new int[Num_Of_Players];
        Ptr_Of_AOBP = 0;

        for (int i = 0; i < Num_Of_Players; i++)
        {
            Array_Of_Banned_Players[i] = -1;
        }

        int Temp_Value;             //временная переменная


        //Эти массивы содержат номера игроков с активной ролью
        Arr_Mafia = new int[Role_Mafia];
        Ptr_AM = 0;

        Arr_Doctor = new int[Role_Doctor];
        Ptr_AD = 0;

        Arr_Policeman = new int[Role_Policeman];
        Ptr_AP = 0;

        //распределяю каждую активную роль
        Ptr_AM = Sets_Roles(Role_Mafia, 0, Arr_Mafia, Ptr_AM);
        //Display_Arr(Arr_Mafia, Role_Mafia);

        Ptr_AD = Sets_Roles(Role_Doctor, 1, Arr_Doctor, Ptr_AD);
        //Display_Arr(Arr_Doctor, Role_Doctor);

        Ptr_AP = Sets_Roles(Role_Policeman, 2, Arr_Policeman, Ptr_AP);
        //Display_Arr(Arr_Policeman, Role_Policeman);

        // System.out.println("\n");

        Game_Over = false;           //наступил ли конец игры
        Num_Of_Days = 0;
        Num_Of_Avile_Players = Num_Of_Players;

    }


    public void Night()
    {
        Num_Of_Days++;
        //System.out.println("Ночь");
        Cur_Time = "Ночь";
        //Ночь. Ходят только активные роли
        int Target_For_Doctor;              //цель, которую нужно будет лечить доктору

        //Ход Мафии

        //Выбор списка возможных кандидатов отправиться на тот свет
        int[] Array_For_Target_Of_Mafia = new int[Ptr_AM];
        int Ptr_AFTOM = 0;

        for (int j = 0; j < Ptr_AM; j++)
        {
            Array_For_Target_Of_Mafia[Ptr_AFTOM++] = Bots[Arr_Mafia[j]].Walk_At_Night(Bots, Num_Of_Players, Arr_Mafia[j]);
            //Player[] Bots, int Num_Of_Players, int Target
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

                //если осталось больше 1 Мафии
                Target1 = random.nextInt(Array_For_Target_Of_Mafia.length);

                Bots[Array_For_Target_Of_Mafia[Target1]].Set_Died(true);
                Target_For_Doctor = Array_For_Target_Of_Mafia[Target1];
            }
        }
        Event_Mafia = "Мафия сделала свой выбор!";
        Num_Of_Avile_Players--;
        //Target_Mafia = Bots[Target_For_Doctor];

        //если доктора не осталось в живых до выстрела, а выстрел был произведен в другую активную роль
        if (Ptr_AD == 0)
        {
            if (Bots[Target_For_Doctor].Get_Role() == "Policeman")
                Ptr_AP = Refresh_Array_With_Role(Arr_Policeman, Ptr_AP);
        }

        //Event_Mafia = "Мафия выстрелила в "


        //System.out.print(Num_Of_Avile_Players - 1 + "   ");


        //Ход Доктора
        int Save;               //переменная, которую возвращает Доктор. Показывает, спас ли Доктор игрока, в которого выстрелила Мафия (0 - НЕ спас, 1 - спас).

        for (int i = 0; i < Ptr_AD; i++)
        {
            Save = Bots[Arr_Doctor[i]].Walk_At_Night(Bots, Arr_Doctor[i], Target_For_Doctor);
            if (Save == 0)
            {
                if (Bots[Target_For_Doctor].Get_Role() == "Doctor")
                    Ptr_AD = Refresh_Array_With_Role(Arr_Doctor, Ptr_AD);

                if (Bots[Target_For_Doctor].Get_Role() == "Policeman")
                    Ptr_AP = Refresh_Array_With_Role(Arr_Policeman, Ptr_AP);

                Event_Doctor = "Доктор не спас жертву Мафии...";
            }
            else
            {
                Num_Of_Avile_Players++;
                Event_Doctor = "Жертва Мафии была спасена!";
                break;
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
                Ptr_AM = Refresh_Array_With_Role(Arr_Mafia, Ptr_AM);
                Event_Policeman = "Мафия попалась на глаза Комиссару!";
            }
            else
            {
                Event_Policeman = "Комиссар не поймал Мафию!";
            }
        }
        //System.out.println(Num_Of_Avile_Players);


        //Конец игры

        //Мафии больше не осталось
        if (Ptr_AM == 0)
        {
            System.out.println("Победа Мирных жителей!");
            Victory = "Победа Мирных жителей!";
            //Controller.Print_Victory("Победа Мирных жителей!");
            Game_Over = true;
        }

        //Количество Мафии равно количеству оставшихся в живых игроков
        if (Num_Of_Avile_Players == Ptr_AM)
        {
            System.out.println("Победа Мафии!");
            Victory = "Победа Мафии!";
            //Controller.Print_Victory("Победа Мафии!");
            Game_Over = true;
        }
    }


    public void Day() {
        //System.out.println("День");
        Cur_Time = "День";
        //День. Ходят все живые
        int[] Arr_For_Daily_Votes = new int[Num_Of_Players];          //массив для тех, кого хотят посадить
        //int Ptr_AFDV = 0;

        //заполенние массива
        for (int i = 0; i < Num_Of_Players; i++)
        {
            int Temp_Vote = Bots[i].Walk_At_Day(Bots, i);
            if (Temp_Vote != -1)
                Arr_For_Daily_Votes[Temp_Vote] += 1;
            //Ptr_AFDV++;
        }

        //нахождения наибольшего количества голосов против игрока
        int Max_Votes = 0;              //максимальное количество голосов

        for (int i = 0; i < Num_Of_Players; i++)
        {
            if (Arr_For_Daily_Votes[i] > Max_Votes)
                Max_Votes = Arr_For_Daily_Votes[i];
        }

        //вынесение в массисв все игроков, против которых больше всего голосовали
        int[] Arr_Max_Votes = new int[Num_Of_Players];
        int Ptr_AMV = 0;

        for (int i = 0; i < Num_Of_Players; i++)
        {
            if (Arr_For_Daily_Votes[i] == Max_Votes)
                Arr_Max_Votes[Ptr_AMV++] = i;
        }

        //нахожу самую минимальную удачу среди них
        int Min_Luck = 100;

        for (int i = 0; i < Ptr_AMV; i++)
        {
            if (Bots[Arr_Max_Votes[i]].Get_Luck() < Min_Luck)
                Min_Luck = Bots[Arr_Max_Votes[i]].Get_Luck();
        }

        //прохожу по массиву и ищу игрока с самым минимальным показателем удачи и убиваю его
        for (int i = 0; i < Ptr_AMV; i++)
        {
            if (Bots[Arr_Max_Votes[i]].Get_Luck() == Min_Luck)
            {
                Bots[Arr_Max_Votes[i]].Set_Died(true);

                if (Bots[Arr_Max_Votes[i]].Get_Role() == "Mafia")
                    Ptr_AM = Refresh_Array_With_Role(Arr_Mafia, Ptr_AM);

                if (Bots[Arr_Max_Votes[i]].Get_Role() == "Doctor")
                    Ptr_AD = Refresh_Array_With_Role(Arr_Doctor, Ptr_AD);

                if (Bots[Arr_Max_Votes[i]].Get_Role() == "Policeman")
                    Ptr_AP = Refresh_Array_With_Role(Arr_Policeman, Ptr_AP);

                Num_Of_Avile_Players--;
                Event_Votes = "Жители проголосовали против " + String.valueOf(Bots[Arr_Max_Votes[i]].Get_Name() + "!!!");
                break;
            }
        }



        //Конец игры

        //Мафии больше не осталось
        if (Ptr_AM == 0)
        {
            System.out.println("Победа Мирных жителей!");
            //Controller.Print_Victory("Победа Мирных жителей!");
            Victory = "Победа Мирных жителей!";
            Game_Over = true;
        }

        //Количество Мафии равно количеству оставшихся в живых игроков
        if (Num_Of_Avile_Players == Ptr_AM)
        {
            System.out.println("Победа Мафии!");
            Victory = "Победа Мафии!";
            //Controller.Print_Victory("Победа Мафии!");
            Game_Over = true;
        }
    }

    public int  Walk(int Time)
    {
        if (Game_Over == false) {
            if (Time == 0) {
                //ход ночью
                Night();
                Time++;
                return Time;
                //System.out.println("Ночь");
            } else {
                if (Time == 1) {
                    //ход днем
                    Day();
                    Time--;
                    return Time;

                }
            }


        }


         return  0;
    }

    public boolean Is_Game_Over() {
        return Game_Over;
    }

    public Player[] getBots() {
        return Bots;
    }

    public int getNum_Of_Players() {
        return Num_Of_Players;
    }

    public String getEvent_Votes() {
        return Event_Votes;
    }

    public String getCur_Time() {
        return Cur_Time;
    }

    public int getNum_Of_Days() {
        return Num_Of_Days;
    }

    public String getVictory() {
        return Victory;
    }

    public String getEvent_Mafia() {
        return Event_Mafia;
    }

    public String getEvent_Doctor() {
        return Event_Doctor;
    }

    public String getEvent_Policeman() {
        return Event_Policeman;
    }
}


