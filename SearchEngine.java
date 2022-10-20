import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.    
    ArrayList<String> list = new ArrayList<String>();

    public String handleRequest(URI url) {

        String searched = "";

        if (url.getPath().equals("/")) {
            return String.format("Search engine home");
        } 
        else if (url.getPath().equals("/add")) {
            String[] parameters = url.getQuery().split("=");
            if(parameters[0].equals("s")){
                list.add(parameters[1]);
                return "adding to history...";
            }
        }
        else if (url.getPath().equals("/search")) {
            String[] parameter = url.getQuery().split("=");
            if(parameter[0].equals("s")) {
                for(String s : list){
                    if(s.contains(parameter[1])){
                        searched = searched + " " + s; 
                    }
                }
                return searched;
            }
        } 
            return "404 Not Found!";
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



