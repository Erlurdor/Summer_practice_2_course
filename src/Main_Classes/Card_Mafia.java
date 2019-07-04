package Main_Classes;

//  Карта Мафии

import com.oracle.xmlns.internal.webservices.jaxws_databinding.ExistingAnnotationsType;

import java.util.Random;

public class Card_Mafia extends  Card {

    @Override
    public int Walk_At_Day(Player[] Bots, int Target) {
        //Target - индекс самого себя

        //если персонаж НЕ жив
        if (Bots[Target].Get_Diead() == true)
        {
            return -1;
        }

        //персонаж ЖИВ
        int[] Alive_Players = new int[Bots.length];        //массив с индексами игроков
        int Ptr_AP = 0;


        //вычисляю всех живых игроков
        for (int i = 0; i < Bots.length; i++)
        {
            //если игрок жив
            if (Bots[i].Get_Diead() == false)
            {
                Alive_Players[Ptr_AP++] = i;
                //Num_Alive_Players++;
            }
        }

        //персонаж ЖИВ
        Random random = new Random();
        int Temp_Target;
        boolean Exit;


        do
        {
            Exit = false;
            Temp_Target = random.nextInt(Ptr_AP);

            //если он выбрал НЕ себя и НЕ мертвого персонажа

            if (Alive_Players[Temp_Target] != Target && Bots[Alive_Players[Temp_Target]].Get_Diead() != true && Bots[Alive_Players[Temp_Target]].Get_Role() != "Mafia")
            //if (Temp_Target != Target && Bots[Temp_Target].Get_Diead() != true)
            {
                return Alive_Players[Temp_Target];
            }

        } while (!Exit);


        return  -1;
    }

    @Override
    public int Walk_At_Night(Player[] Bots, int Num_Of_Players, int Target)
    {
        //Target - индекс текущего игрока (Мафии)
        double Coef = 0.85;             //коэффициент для временной удачи
        int Temp_Luck;                  //временный показатель удачи. Основан на базовой удачи, умноженной на коэфф. K (K < 1)
        int Num_Alive_Players = 0;      //колчество живых игроков
        Player[] Alive_Players = new Player[Num_Of_Players];        //массив живых игроков
        boolean Other_Active_Roles = false;                          //есть ли другие активаные роли
        Random random = new Random();

        //вычисляю всех живых игроков
        for (int i = 0; i < Num_Of_Players; i++)
        {
            //если игрок жив
            if (Bots[i].Get_Diead() == false)
            {
                Alive_Players[Num_Alive_Players++] = Bots[i];
                //Num_Alive_Players++;
            }
        }

        //вычисляю наличие активных ролей (кроме Мафии). Может оказаться, что удача сработала, а активных ролей больше нет
        for (int i = 0; i < Num_Alive_Players; i++)
        {
            if (Alive_Players[i].Get_Role() != "Peaceful" && Alive_Players[i].Get_Role() != "Mafia")
            {
                Other_Active_Roles = true;
                break;
            }
        }

        //вычисляю временную удачу текущего игрока
        Temp_Luck =  (int) (Bots[Target].Get_Luck() * Coef);

        int Temp_Random_For_Luck = random.nextInt(99) + 1;       //1 - 100

        int Temp_Random_For_Target = random.nextInt(Num_Alive_Players);

        //если удача улыбнулась игроку и есть другие активные роли
        if (Temp_Random_For_Luck <= Temp_Luck && Other_Active_Roles == true)
        {
            //выбор цели
            boolean Exit;

            do {
                Exit = false;

            //если Мафия указала НЕ на саму себя, цель - НЕ мирный житель и НЕ другая Мафия
            if (Bots[Target] != Alive_Players[Temp_Random_For_Target] && Alive_Players[Temp_Random_For_Target].Get_Role() != "Peaceful" && Alive_Players[Temp_Random_For_Target].Get_Role() != "Mafia")
            //if (Temp_Random_For_Target != Target&& Bots[Temp_Random_For_Target].Get_Role() != "Peaceful" && Bots[Temp_Random_For_Target].Get_Role() != "Mafia")
            {
                Exit = true;
                break;
            }
            else
            {
                Temp_Random_For_Target = random.nextInt(Num_Alive_Players);
            }

        } while (!Exit);
        }
        else
        {
            //Удача не сработала

            //выбор цели
            boolean Exit;

            do {
                Exit = false;

                //если Мафия указала НЕ на саму себя, цель - НЕ другая Мафия
                if (Bots[Target] != Alive_Players[Temp_Random_For_Target] && Alive_Players[Temp_Random_For_Target].Get_Role() != "Mafia")
                //if (Temp_Random_For_Target != Target && Bots[Temp_Random_For_Target].Get_Role() != "Mafia")
                {
                    Exit = true;
                    break;
                }
                else
                {
                    Temp_Random_For_Target = random.nextInt(Num_Alive_Players);
                }

            } while (!Exit);
        }

        //цель найдена в массиве живых игроков. Нахожу эту цель в общем массиве и возвращаю ее индекс
        for (int i = 0; i < Num_Of_Players; i++)
        {
            if (Alive_Players[Temp_Random_For_Target] == Bots[i])
            {
                return i;
            }

        }

        return -1;
        //return Temp_Random_For_Target;
    }

    @Override
    public String getRole() {
        return "Mafia";
    }
}
