package Main_Classes;

import java.util.Random;

public class Card_Doctor extends Card {
    @Override
    public int Walk_At_Day(int All_Players) {
        return 2;
    }

    @Override
    public int Walk_At_Night(Player[] Bots, int Num_Of_Players, int Target)
    {
        //Target - цель, в которую стрелялиа Мафия
        //Num_Of_Players - индекс Доктора

        int Luck;               //базовая удача игрока
        int Temp_Luck;          //Временная удача, равная базовая удача * коэфф.
        double Coef;               //коэфф. для временной удачи
        Random random = new Random();
/*
        Player[] Alive_Players = new Player[Bots.length];
        int Ptr_AP = 0;

        //вычисляю всех живых игроков
        for (int i = 0; i < Num_Of_Players; i++)
        {
            if (Bots[i].Get_Diead() == false)
            {
                Alive_Players[Ptr_AP++] = Bots[i];
            }
        }

        //раненного игрока добавляю в конец массива
        Alive_Players[Ptr_AP++] = Bots[Target];

        //перемещаю раненного игрока с последнего места в рандомное
        int Random_Position = random.nextInt(Ptr_AP);
        Alive_Players[Random_Position] = Alive_Players[Ptr_AP - 1];

*/
        Luck = Bots[Num_Of_Players].Get_Luck();
        Coef = 0.85;
        Temp_Luck =  (int) (Luck * Coef);


        int Temp_Random_For_Luck = random.nextInt(99) + 1;       //1 - 100

        //int Temp_Random_For_Target = random.nextInt(99) + 1;        //1 - 100

        //если удача улыбнулась игроку
        if (Temp_Random_For_Luck <= Temp_Luck)
        {
            Bots[Target].Set_Died(false);
            return 1;
        }
        else
        {
            //если неповезло
            int Target_For_Save = random.nextInt(Bots.length);

            //если ему попалась цель тот, в кого стреляли
            if (Target == Target_For_Save)
            {
                //он спас раненного
                Bots[Target].Set_Died(false);
                return 1;
            }
            else
            {
                //он не спас его
                return  0;
            }
        }
    }

    @Override
    public String getRole() {
        return "Doctor";
    }
}
