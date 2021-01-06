import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class ChangeNickController {

    @FXML
    private TextField nickname;


    public void changeNickname(ActionEvent event) {
        Controller.getClient().sendCommand(Command.changeNickname(nickname.getText()));
        NetChat.getChangeNickWindow().closeChatStage();
    }


}
