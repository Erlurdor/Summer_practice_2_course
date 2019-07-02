package Main_Classes;

public class Card_Policeman extends Card {
    @Override
    public int Walk_At_Day(int All_Players) {
        return 3;
    }

    @Override
    public String getRole() {
        return "Policeman";
    }
}
