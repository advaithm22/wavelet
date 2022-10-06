import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;


class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> listOfStrings = new ArrayList<>();	
    public String handleRequest(URI url) {
        if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                listOfStrings.add(parameters[1]);
                return String.format("String is now %s", parameters[1]);
                }
            }
        if(url.getPath().contains("/search")){
            String result = "";
            String[] words = url.getQuery().split("=");
            if(words[0].equals("s")){
                for(String val: listOfStrings){
                    if(val.contains(words[1])){
                        result+=val;
                    }
                }
                return String.format(result);
            }
        }
            return "404 Not Found!";
        }
    }

class SearchEngine{
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}

