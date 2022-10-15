import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    ArrayList<String> listOfWords = new ArrayList<>();
    public String handleRequest(URI url) {
        if (url.getPath().contains("/add")){
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                listOfWords.add(parameters[1]);
            }
            return String.format("Added:" + parameters[1]);
        }else if (url.getPath().contains("/search")){
            ArrayList<String> wordsWithSub = new ArrayList<>();
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                for(int i = 0; i < listOfWords.size(); i++){
                    if(listOfWords.get(i).contains(parameters[1])){
                        wordsWithSub.add(listOfWords.get(i));
                    }
                }
                return String.format("List of words with substring:" + wordsWithSub);
            }
        }else{
            return "Add a String!";
        }
        return "Add a String!";
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }
        int port = Integer.parseInt(args[0]);
        Server.start(port, new Handler());
    }
}
