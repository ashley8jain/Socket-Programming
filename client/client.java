import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;


public class client {

    private static DatagramSocket clientSocket;
    static BufferedWriter bw;

    public static void main(String args[]) throws Exception {

        File file = new File(args[3]);

        // if file doesnt exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }

        //writer of output file
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        bw = new BufferedWriter(fw);

        for(int i=0;i<50;i++)
            sendRecieve(args[0],Integer.parseInt(args[1]),Integer.parseInt(args[2]));

        bw.close();
    }

    static void sendRecieve(String host,int packetSize,int RC) throws IOException {

        clientSocket = new DatagramSocket();
        clientSocket.setSoTimeout(1000);

        ArrayList<Long> time = new ArrayList<>();

        int initialRC = RC;
        int Seq = 0;
        String sentence;
        long time1,time2;

        long initial = System.nanoTime();
        int packetLoss = 0;
        while(RC!=0){
            Seq++;
            InetAddress ipAddress = InetAddress.getByName(host);
            byte[] receiveData = new byte[1024];
            byte[] sendData;
            long tim = System.nanoTime();

            sentence = Seq+"\n"+((tim-initial)/1000)+"microsec\n"+RC+"\ncfdcsdvcfdcdfcdcedfcfefcreferfcerfred012345678901234567890123456789012345678901234567890123456789cfdcsdvcfdcdfcdcedfcfefcreferfcerfredvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvcfdcsdvcfdcdfcdcedfcfefcreferfcerfredvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvcfdcsdvcfdcdfcdcedfcfefcreferfcerfredvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvcfdcsdvcfdcdfcdcedfcfefcreferfcerfredvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvcfdcsdvcfdcdfcdcedfcfefcreferfcerfredvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvcfdcsdvcfdcdfcdcedfcfefcreferfcerfredvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvcfdcsdvcfdcdfcdcedfcfefcreferfcerfredvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvcfdcsdvcfdcdfcdcedfcfefcreferfcerfredvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvcfdcsdvcfdcdfcdcedfcfefcreferfcerfredvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvcfdcsdvcfdcdfcdcedfcfefcreferfcerfredvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvcfdcsdvcfdcdfcdcedfcfefcreferfcerfredvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvcfdcsdvcfdcdfcdcedfcfefcreferfcerfredvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvcfdcsdvcfdcdfcdcedfcfefcreferfcerfredvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvcfdcsdvcfdcdfcdcedfcfefcreferfcerfredvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvcfdcsdvcfdcdfcdcedfcfefcreferfcerfredvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvcfdcsdvcfdcdfcdcedfcfefcreferfcerfredvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvcfdcsdvcfdcdfcdcedfcfefcreferfcerfredvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvcfdcsdvcfdcdfcdcedfcfefcreferfcerfredvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvcfdcsdvcfdcdfcdcedfcfefcreferfcerfredvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv";

            sendData = sentence.getBytes();
            int sendSizeByte = sendData.length;
            if(sendSizeByte>packetSize)
                sendSizeByte = packetSize;

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendSizeByte, ipAddress, 9876);
            time1 = System.currentTimeMillis();
            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            try {
                clientSocket.receive(receivePacket);
            } catch (SocketTimeoutException e) {
                // resend
                continue;
            }
            time2 = System.currentTimeMillis();

            String rec = new String(receivePacket.getData());
            String[] split = rec.split("\\s+");
            RC = Integer.parseInt(split[2]);
            RC--;

            time.add(time2 - time1);
        }

        long sum = 0;
        for (int i = 0; i < time.size(); i++) {
            sum = sum + time.get(i);
        }

        packetLoss = Seq - initialRC/2;

        double meanTime = (double) sum / time.size();

        String s1,s2;
        s1 = "Cummulative RTT: " + meanTime+"millisec \n";
        s2 = "No. of Packet lost: "+packetLoss+"\n";
        bw.write(s1,0,s1.length());
        bw.write(s2,0,s2.length());

        clientSocket.close();

    }
}