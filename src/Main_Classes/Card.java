package Main_Classes;

import java.util.Random;

public class Card {
    public int Walk_At_Day(Player[] Bots, int Target)
    {
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

            if (Alive_Players[Temp_Target] != Target && Bots[Alive_Players[Temp_Target]].Get_Diead() != true)
            //if (Temp_Target != Target && Bots[Temp_Target].Get_Diead() != true)
            {
                return Alive_Players[Temp_Target];
            }

        } while (!Exit);


        return  -1;

    }

    public int Walk_At_Night(Player[] Bots, int Num_Of_Players, int Target)
    {
        return -1;
    }

    public String getRole()
    {
        return "";
    }
}
