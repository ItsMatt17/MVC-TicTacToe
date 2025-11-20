package model;

import com.google.gson.Gson;
import netscape.javascript.JSObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Repository {

    private static Repository instance = null;
    private HashMap<String, Integer> cache = new HashMap<>(Map.of(
            "X", 0,
            "O", 0,
            "Total", 0,
            "Draw", 0
    ));

    private final String dir = "data.json";
    private Repository(){
        initialize();
    }

    private void initialize(){
        instance = this;
    }



    public void update(Model model){
        if (!model.isWon()) {
            cache.put("Draw", cache.getOrDefault("Draw", 0) + 1);
            cache.put("Total", cache.getOrDefault("Total", 0) + 1);
            return;
        }

        String mark = String.valueOf(model.getWinner().getMark());
        cache.put(mark, cache.getOrDefault(mark, 0) + 1);


    }

    public void save() {
        Gson gson = new Gson().newBuilder().create();
        String json = gson.toJson(cache);
        File file = new File(dir);
        FileWriter fw = null;
        if (!file.exists()) {
            try {
                boolean created = file.createNewFile();
                if (!created) return;
            } catch (IOException | SecurityException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        try {
            fw = new FileWriter(file);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        try {
            fw.write(json);
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);


        }
    }


    public static Repository getInstance(){
        if(instance != null) return instance;
        return new Repository();
    }


}
