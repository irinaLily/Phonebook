package workToFile;


import com.google.gson.Gson;
import contact.Person;

import java.io.*;

import java.util.*;

public class WriteFile {
 // SearchPersonImpl searchPerson=new SearchPersonImpl();
  public void writeCollectionsperson(Collection<Person> collections) {
     System.out.println("_______________-"+collections);
     System.out.println();


        try {
            write("output.json",collections);
        }catch (IOException e){
            e.printStackTrace();
        }
  }
  public void write(String path,Collection<Person> collections)throws IOException {
    File file = new File(path);
    if (file.isDirectory()) {
      throw new WrongDestExeption("Can not write" + path);
    }
    try (
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
    ) {
      pw.print(personToGson(collections));
      pw.flush();
        pw.close();

    }
  }
public String personToGson(Collection<Person> collections) {
  Gson gson = new Gson();
  String json = gson.toJson(collections);
  return json;
}

}


