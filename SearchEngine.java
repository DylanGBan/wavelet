import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
   
    String end = "There is no word!";

    ArrayList<String> stringList = new ArrayList<>();

    StringBuilder searchString = new StringBuilder();


    public String handleRequest(URI url) {

        if (url.getPath().equals("/")) {
            return end;
        } 

        if(url.getPath().contains("/add")){

        String[] temp = url.getQuery().split("=");

        if(temp[0].equals("w")){

        stringList.add(temp[1]);

        return String.format("The word %s", temp[1] + " has been entered");

        }

        }

        if(url.getPath().contains("/search")){

        String[] temp2 = url.getQuery().split("=");

        if(temp2[0].equals("w")){
    
        for(int i = 0; i < stringList.size(); i++){

        if(stringList.get(i).contains(temp2[1])){

        searchString.append(stringList.get(i));
        
        searchString.append(", ");
        
        } 

        }

        if(searchString.isEmpty()){

            return "Search returned empty!";
    
            }

        searchString.deleteCharAt(searchString.length() - 2);


        return String.format("Search returned: %s", searchString.toString());

   
    
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
