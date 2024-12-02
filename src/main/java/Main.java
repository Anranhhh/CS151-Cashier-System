import cashierUI.Frame1;
import model.DataModel;

public class Main {
    public static void main(String[] args) {
        DataModel model = new DataModel();
        Frame1 frame = new Frame1(model);
        frame.setVisible(true);
    }
}
