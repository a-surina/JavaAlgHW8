public class Flat {
    private final int number;
    private final String ownerFullName;


    public Flat(int number, String surname) {
        this.number = number;
        this.ownerFullName = surname;
    }

    public int getKey() {
        return this.number;
    }

    public String printer() {
        if (this.number == -1) {
            return String.valueOf(this.number);
        } else {
            return "flat: " + this.number + " - owner: " + this.ownerFullName;
        }
    }
}
