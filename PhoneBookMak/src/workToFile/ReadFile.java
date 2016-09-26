package workToFile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import contact.Person;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Scanner;

/**
 * Created by Ирина on 17.02.2016.
 */
public class ReadFile {
    public void readPerson(String path){
        try {
            read( path);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void read(String path) throws IOException{
        String str="";
        File file=new File(path);
        if(file.isDirectory()){
            throw  new IncorrectFileNameException("Can not read "+path);
        }
        try (
                FileReader fr=new FileReader(file);
                BufferedReader br=new BufferedReader(fr);
                Scanner scanner=new Scanner(br);
                ){
             str=scanner.nextLine();

        }
        Gson gson = new Gson();
        Type listType = new TypeToken<Collection<Person>>() {}.getType();

       Collection<Person> p = gson.fromJson(str, listType);
        System.out.println("***************************READ***********************");
        System.out.println(p);
    }

}
