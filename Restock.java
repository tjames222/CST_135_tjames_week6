/** Project: Milestone 6
 * Created For the Bug Smashers Group
 * Summary: Source code for Restock
 * Class: CST-135
 * Date: March 24, 2018
 * Author: Tim James
 */
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Restock {

    protected int lowStock;
    protected Image img;
    private ObservableList<Product> purOrderList = FXCollections.observableArrayList();
    private TableView<Product> purOrder = new TableView<>();

    public Restock() {
    }

    Restock(int lowStockIn, Image imgIn) {
        lowStock = lowStockIn;
        img = imgIn;
    }

    public void setLowStockIn(int lowStockIn) {
        this.lowStock = lowStockIn;
    }

    public int getLowStockIn() {
        return this.lowStock;
    }

    public void setImg(Image imgIn) {
        this.img = imgIn;
    }

    public Image getImg() {
        return this.img;
    }

    public void lowStock() {

        Dispenser disp = new Dispenser();
        for (int i = 0; i < disp.productsForSale.length; i++) {

            while (disp.getProduct(i).quantity <= 3) {
                ImageView img = new ImageView(new Image(
                        "file:src/DispenserDesign/Background&ButtonImages/LowStock.png"));
                purOrderList.add(disp.getProduct(i));
            }
        }
    }
}
