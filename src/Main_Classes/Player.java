package Main_Classes;

//import javax.smartcardio.Card;
//import Main_Classes.Card;

//  Основной класс игрока. Содержит характеристики.

public class Player {
    private int Luck;       //показатель удачи игрока
    private String Name;         //имя игрока
    private boolean Died;       //жив ли игрок. False - живой
    private Card Role;          //карточка с ролью для игрока

    public void Init(int luck, String name)
    {
        Luck = luck;
        Name = name;
        Died = false;
        Role = new Card_Peaceful();
    }

    /*
    public void setRole(int Value)      //установка активной роли. 0 - Мафия, 1 - Доктор, 2 - Коммисар
    {
        switch (Value)
        {
            case 0:
            {
                Role = new Card_Mafia();
                break;
            }

            case 1:
            {
                Role = new Card_Doctor();
                break;
            }

            case 2:
            {
                Role = new Card_Policeman();
                break;
            }
        }

    }
    */

    public void Set_Role(Card role)
    {
        Role = role;
    }

    public Card Get_Role()
    {
        return Role;
    }

    public void setDied(boolean died) {
        Died = died;
    }
}
