/*
 * Copyright (c) 1995, 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicIntegerArray;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class PongServer {

    private static PrintWriter first, second;

    public static void main(String[] args) throws IOException {

        int portNumber = 3000;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (true) {
                new ChatServerThread(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }

    public static class ChatServerThread extends Thread {
        private Socket socket = null;
        private boolean identity;

        public ChatServerThread(Socket socket) {
            super("PongServerThread");
            this.socket = socket;
        }

        public void run() {

            try (
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()))
            ) {
                String inputLine;
                java.lang.reflect.Type mapType = new TypeToken<HashMap<String, String>>(){
    			}.getType();
                Gson gson = new Gson();
                out.println("Connected");

                if (first == null){
                	// This is the first user.
                	identity = true;
                	first = out;
                }
                else {
                	// This is the second user.
                	identity = false;
                	second = out;
                }

                try{
                	// If the second client has connected, then we start the game.
                	if (!identity) {
                		long t = Calendar.getInstance().getTimeInMillis();
                		first.println("Start" + (t + 1000));
                		second.println("Start" + (t + 1000));
                	}
                	
                	// Continuously accept user data
                    while ((inputLine = in.readLine()) != null) {
                        if (inputLine.equals("Disconnect"))
                            break;
                    	// We parse the message
                        HashMap<String, String> pro = gson.fromJson(inputLine, mapType);
                        
                        // If the first client sent us the data, we send the data to the second client.
                        if (identity) {
	                        second.println(gson.toJson(pro, HashMap.class));
                        } else {
	                        first.println(gson.toJson(pro, HashMap.class));
                        }
                    }
                } catch (JsonSyntaxException e){
                    e.printStackTrace();
                } finally {
                    if(identity){
                    	first.close();
                    	first = null;
                    } else {
                    	second.close();
                    	second = null;
                    }
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}