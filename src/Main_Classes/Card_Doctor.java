package Main_Classes;

public class Card_Doctor extends Card {
    @Override
    public int Walk_At_Day(int All_Players) {
        return 2;
    }

    @Override
    public String getRole() {
        return "Doctor";
    }
}
