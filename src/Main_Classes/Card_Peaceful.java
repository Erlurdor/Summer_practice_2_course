package Main_Classes;

public class Card_Peaceful extends Card{
    @Override
    public int Walk_At_Day(int All_Players) {
        return 0;
    }

    @Override
    public String getRole() {
        return "Peaceful";
    }
}
