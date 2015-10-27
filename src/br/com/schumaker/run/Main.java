package br.com.schumaker.run;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class Main {

    public static void main(String[] args) throws Exception {

        FileSystem fileSystem = FileSystems.getDefault();
        WatchService watchService = fileSystem.newWatchService();
        //Path directory = Paths.get("\\\\SPW9716NTW7P\\c$\\Users\\alex.carneiro\\AppData\\Roaming\\.purple\\logs\\meanwhile\\bp12232");
      Path directory = Paths.get("C:/Temp");
      Path directory2 = Paths.get("C:/Temp/Nova pasta/db");
        WatchEvent.Kind<?>[] events = {
            StandardWatchEventKinds.ENTRY_CREATE,
            StandardWatchEventKinds.ENTRY_DELETE,
            StandardWatchEventKinds.ENTRY_MODIFY
        };
        directory.register(watchService, events);
        directory2.register(watchService, events);
        while (true) {
            System.out.println("Waiting for a watch event");
           
            
            for(int k=0;k<2;k++){
                WatchKey watchKey = watchService.take();
            System.out.println("Path being watched: " + watchKey.watchable());
            if (watchKey.isValid() == false) {
                return;
            }
            for (WatchEvent<?> event : watchKey.pollEvents()) {
                System.out.println("Kind: " + event.kind());
                System.out.println("Context: " + event.context());
                System.out.println("Count: " + event.count());
                System.out.println();
            }
            boolean valid = watchKey.reset();
            System.out.println(valid);
            }
            
        }
    }
}