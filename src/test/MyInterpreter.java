package test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import Commands.CommandFactory;
import Lexer.Lexer;
import Parser.Parser;
import ClientServer.Client;
import ClientServer.Server;


public class MyInterpreter {

    public static Double Value;

    public static  int interpret(String[] lines){
        ConcurrentHashMap<String, Double> DoublesymbolTable = new ConcurrentHashMap<>(); // x = 5
        ConcurrentHashMap<String, String> SymbolTable       = new ConcurrentHashMap<>(); // x -> path x
        ConcurrentHashMap<String, Double> PathTable         = new ConcurrentHashMap<>(); //path simx -> value

        String[] DMnames = new String[] {
                "simX",
                "simY",
                "simZ",
                "instrumentation/airspeed-indicator/indicated-speed-kt",
                "instrumentation/altimeter/indicated-altitude-ft",
                "instrumentation/altimeter/pressure-alt-ft",
                "instrumentation/attitude-indicator/indicated-pitch-deg",
                "instrumentation/attitude-indicator/indicated-roll-deg",
                "instrumentation/attitude-indicator/internal-pitch-deg",
                "instrumentation/attitude-indicator/internal-roll-deg",
                "instrumentation/encoder/indicated-altitude-ft",
                "instrumentation/encoder/pressure-alt-ft",
                "instrumentation/gps/indicated-altitude-ft",
                "instrumentation/gps/indicated-ground-speed-kt",
                "instrumentation/gps/indicated-vertical-speed",
                "instrumentation/heading-indicator/indicated-heading-deg",
                "instrumentation/magnetic-compass/indicated-heading-deg",
                "instrumentation/slip-skid-ball/indicated-slip-skid",
                "instrumentation/turn-indicator/indicated-turn-rate",
                "instrumentation/vertical-speed-indicator/indicated-speed-fpm",
                "controls/flight/aileron",
                "controls/flight/elevator",
                "controls/flight/rudder",
                "controls/flight/flaps",
                "controls/engines/current-engine/throttle",
                "engines/engine/rpm"
        };

        Server server = new Server(DMnames,PathTable); //original (DMnames)
        Client client = new Client(DMnames, PathTable);
        CommandFactory command_factory = new CommandFactory(server,client,SymbolTable,DoublesymbolTable,PathTable);
        Lexer lex = new Lexer();
        Parser parse = new Parser(command_factory);
        for (String str : lines) {
            parse.parse(lex.processLine(str));
        }
        server.stop();
        return Value.intValue();


    }
}
