import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ChangeNickController {

    @FXML
    private TextField nickname;


    public void changeNickname(ActionEvent event) {
        Controller.getClient().sendCommand(Command.changeNickname(nickname.getText()));
        NetChat.getChangeNickWindow().closeChatStage();
    }


}
