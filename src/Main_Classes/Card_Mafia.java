package Main_Classes;

//  Карта Мафии

import java.util.Random;

public class Card_Mafia extends  Card {
    @Override
    public int Walk_At_Day(int All_Players) {
        Random random = new Random();



        return 1;
        //super.Walk_At_Day();
    }

    @Override
    public int Walk_At_Night(int All_Players, int Luck, int[] Arr, int Ptr_Arr)
    {
        //for (int i = 0; )


        //проверяю, сколько элементов в массиве

        //для мафии необходимы: Список всех игроков (All_Players), Удача Мафии (Luck) и массив целей (Arr) в случае, если удача высокая. Ptr - Количество Мафий
        int Target;
        int Temp_Luck;

        Temp_Luck =  (int) (Luck * 0.85);

        Random random = new Random();
        int Temp_Random = random.nextInt(99) + 1;       //1 - 100

        if (Temp_Random <= Temp_Luck)
        {
            //Везунчик!

        }



        return 1;
    }

    @Override
    public String getRole() {
        return "Mafia";
    }
}
