package Main_Classes;

import java.util.Random;

public class Card_Policeman extends Card {
    @Override
    public int Walk_At_Night(Player[] Bots, int Num_Of_Players, int Target) {
        //Target - индекс игрока (Коммисара)

        double Coef = 0.85;             //коэффициент для временной удачи
        int Temp_Luck;                  //временный показатель удачи. Основан на базовой удачи, умноженной на коэфф. K (K < 1)

        int[] Num_Mafia = new int[Num_Of_Players];          //массив Мафий. Содержит индексы Мафий
        int Ptr_NM = 0;

        //вычисляю всех игроков-Мафий
        for (int i = 0; i < Num_Of_Players; i++)
        {
            if (Bots[i].Get_Role() == "Mafia" && Bots[i].Get_Diead() == false)
            {
                Num_Mafia[Ptr_NM++] = i;
            }
        }

        //вычисляю временную удачу текущего игрока
        Temp_Luck =  (int) (Bots[Target].Get_Luck() * Coef);


        Random random = new Random();
        int Temp_Random_For_Luck = random.nextInt(99) + 1;       //1 - 100

        //если удача улыбнулась игроку
        if (Temp_Random_For_Luck <= Temp_Luck)
        {
            int Temp_Random_For_Target = random.nextInt(Ptr_NM);
            Bots[Num_Mafia[Temp_Random_For_Target]].Set_Died(true);
            return 1;
        }
        else
        {
            //если не повезло
            int Temp_Random_For_Target = random.nextInt(Num_Of_Players);

            //если он все-таки выбрал Мафию
            for (int i = 0; i < Ptr_NM; i++)
            {
                if (Temp_Random_For_Target == Num_Mafia[i])
                {
                    Bots[Temp_Random_For_Target].Set_Died(true);
                    //break;
                    return 1;
                }
            }

            //он не выбрал Мафию
            return 0;
        }
    }

    @Override
    public String getRole() {
        return "Policeman";
    }
}
