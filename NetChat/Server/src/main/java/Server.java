import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

public class Server {

    private final int SERVER_PORT;
    private volatile boolean isConnect = true;
    private final Scanner scanner = new Scanner(System.in);
    private final LinkedList<Connection> allConnections = new LinkedList<>();


    public Server(int SERVER_PORT) {
        this.SERVER_PORT = SERVER_PORT;

    }

    public void startServer() {

        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {

            sendMsgFromServerToUsers();

            while (isConnect) {
                try {
                    Socket socket = serverSocket.accept();
                    new Connection(socket, this);
                } catch (IOException e) {
                    e.printStackTrace();
                    isConnect = false;
                    return;
                }
            }

        } catch (IOException e) {
            System.out.println("Соединение разорвано");
        }

    }


    public synchronized void addUsers(Connection connection) throws IOException {
        allConnections.add(connection);
        sendInfoAboutClients();
    }

    public synchronized void removeUsers(Connection connection) {
        try {
            allConnections.remove(connection);
            sendInfoAboutClients();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public synchronized void broadcastMsg(String sender, String msg) throws IOException {

        allConnections.stream()
                .filter(c -> !c.getNickname().equals(sender))
                .forEach(c -> c.sendCommand(Command.publicMessageCommand(sender, msg)));
    }

    public synchronized void sndPersonalMsg(Connection sender, String receiver, String msg) {

        allConnections.stream()
                .filter(c -> c.getNickname().equals(receiver))
                .forEach(c -> c.sendCommand(Command.publicMessageCommand(sender.getNickname(), msg)));

        sender.sendCommand(Command.errorMessageCommand("Участника " + receiver + " нет в чате"));
    }

    public void sendInfoAboutClients() throws IOException {
        LinkedList<String> list = new LinkedList<>();

        for (Connection connection : allConnections) {
            list.add(connection.getNickname());
        }

        for (Connection connection : allConnections) {
            connection.sendCommand(Command.sendInfoAboutUsers(list));
        }
    }

    public synchronized boolean isNickBusy(String nick) {

        for (Connection connection : allConnections) {
            if (connection.getNickname().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    private void sendMsgFromServerToUsers() {
        new Thread(() -> {
            while (isConnect) {
                String msgToUsers = scanner.nextLine();
                for (Connection c : allConnections) {
                    c.sendCommand(Command.publicMessageCommand("Server", msgToUsers));
                }
            }
        }).start();

    }


    public LinkedList<Connection> getAllConnections() {
        return allConnections;
    }


    public static void main(String[] args) {
        new Server(2022).startServer();
    }

}

