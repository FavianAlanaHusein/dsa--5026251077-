package lw01.Unguided;

public class ProjectorRental extends Rental {

    public ProjectorRental(String id, int days) {
        super(id, days);
    }

        @Override
        public int calculateCharge() {
            if (getDays() <= 3) {
            return (getDays() * 60000) + 20000;
            }
            return (3 * 60000) + ((getDays() - 3) * 45000) + 20000;
        }
        @Override
        public String label() {
            return "Projector";
        }
}