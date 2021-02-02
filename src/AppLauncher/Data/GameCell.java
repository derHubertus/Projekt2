package AppLauncher.Data;

import javafx.scene.control.ListCell;

public class GameCell extends ListCell<Game> {

    @Override
    protected void updateItem(Game item, boolean empty){
        super.updateItem(item, empty);
        if (item!=null&!empty){
            setText(item.getName());
        }
    }

}
