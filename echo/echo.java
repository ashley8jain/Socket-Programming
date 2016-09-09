import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class echo {
    public static void main(String args[]) throws Exception {

        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData;
        int RC=0;
        String send;

        while(true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            String rec = new String(receivePacket.getData());
            System.out.println(rec);

            String[] split = rec.split("\\s+");
            RC = Integer.parseInt(split[2]);
            RC--;

            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            send = split[0]+"\n"+split[1]+"\n"+RC+"\n"+split[3];
            sendData = send.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
        }
    }
}