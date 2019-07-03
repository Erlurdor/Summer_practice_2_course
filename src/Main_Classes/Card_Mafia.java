package Main_Classes;

//  Карта Мафии

import com.oracle.xmlns.internal.webservices.jaxws_databinding.ExistingAnnotationsType;

import java.util.Random;

public class Card_Mafia extends  Card {
    @Override
    public int Walk_At_Night(Player[] Bots, int Num_Of_Players, int Target)
    {
        //Target - идекс текущего игрока (Мафии)
        double Coef = 0.85;             //коэффициент для временной удачи
        int Temp_Luck;                  //временный показатель удачи. Основан на базовой удачи, умноженной на коэфф. K (K < 1)
        int Num_Alive_Players = 0;      //колчество живых игроков
        //Player[] Alive_Players = new Player[Num_Of_Players];


        //вычисляю всех живых игроков
        for (int i = 0; i < Num_Of_Players; i++)
        {
            if (Bots[i].Get_Diead() == false)
            {
               // Alive_Players[Ptr_AP++] = Bots[i];
                Num_Alive_Players++;
            }
        }

        //вычисляю временную удачу текущего игрока
        Temp_Luck =  (int) (Bots[Target].Get_Luck() * Coef);


        Random random = new Random();
        int Temp_Random_For_Luck = random.nextInt(99) + 1;       //1 - 100


        int Temp_Random_For_Target = random.nextInt(Num_Alive_Players);

        //если удача улыбнулась игроку
        if (Temp_Random_For_Luck <= Temp_Luck)
        {
            //выбор цели
            boolean Exit;

            do {
                Exit = false;

                //если Мафия указала НЕ на саму себя, цель - НЕ мирный житель и НЕ другая Мафия
                if (Temp_Random_For_Target != Target&& Bots[Temp_Random_For_Target].Get_Role() != "Peaceful" && Bots[Temp_Random_For_Target].Get_Role() != "Mafia")
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
                if (Temp_Random_For_Target != Target && Bots[Temp_Random_For_Target].Get_Role() != "Mafia")
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

        return Temp_Random_For_Target;
    }

    @Override
    public String getRole() {
        return "Mafia";
    }
}
