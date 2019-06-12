package com.sarcasm.app.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public final class Connection {
    private final Socket socket;

    public Connection(final String ip){
        this(ip, 1337);
    }

    public Connection(final String ip, final int port){
        try{
            final InetAddress address = InetAddress.getByAddress(ip.getBytes());
            this.socket = new Socket(address, port);

            System.out.printf("Connected to %s:%d", ip, port);
        }catch(final IOException e){
            throw new IllegalArgumentException("Invalid ip: " + ip);
        }
    }

    /** Reads from this connection */
    public final String read(){
        if(!isConnected() || this.socket.isInputShutdown()) return null;

        try{
            final InputStream input = this.socket.getInputStream();
            final StringBuilder output = new StringBuilder();

            for(int i; (i = input.read()) != '\n'; output.append((char) i));

            return Encryption.decrypt(output.toString());
        }catch(final IOException e){
            e.printStackTrace();
        }

        return null;
    }

    /** Writes to this connection */
    public final boolean write(final String msg){
        if(!isConnected() || this.socket.isOutputShutdown()) return false;

        try{
            final OutputStream output = this.socket.getOutputStream();
            output.write(Encryption.encrypt(msg).getBytes());
            output.flush();
            return true;
        }catch(final IOException e){
            e.printStackTrace();
        }

        return false;
    }

    /** Disconnects from the socket */
    public final boolean disconnect(){
        if(!isConnected()) return true;

        try{
            this.socket.shutdownInput();
            this.socket.shutdownOutput();
            this.socket.close();
            return true;
        }catch(final IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /** Gets wether this connection is open */
    public final boolean isConnected(){
        return this.socket.isConnected() && !this.socket.isClosed();
    }
}
