import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ChangeNickController {
    @FXML
    public TextField nickname;

    public void changeNickname(ActionEvent event) {
            Controller.client.sendCommand(Command.changeNickname(nickname.getText()));
            NetChat.stageChangeNick.close();
    }
}
