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

    public void setRole(Card role) {
        Role = role;
    }

    public void setDied(boolean died) {
        Died = died;
    }
}
