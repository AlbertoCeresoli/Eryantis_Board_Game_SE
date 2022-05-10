package it.polimi.ingsw.Client.CLI.Parser;

public class DefaultParser implements Parser {
    @Override
    public void parse(String s) {
        System.out.println(s);
    }
}