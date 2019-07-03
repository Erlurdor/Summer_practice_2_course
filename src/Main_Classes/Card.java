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
        Random random = new Random();
        int Temp_Target;
        boolean Exit;


        do
        {
            Exit = false;
            Temp_Target = random.nextInt(Bots.length);

            //если он выбрал НЕ себя и НЕ мертвого персонажа
            if (Temp_Target != Target && Bots[Temp_Target].Get_Diead() != true)
            {
                return Temp_Target;
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
