package org.example.fileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Optional;

public class FileSearch {
    public static void main(String[] args) throws IOException {
        Path start = new File("C:\\Users\\Admin\\NewProject\\Server\\src\\main\\java\\org\\example").toPath();
//        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
//            @Override
//            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
//            {
//                Files.delete(file);
//                return FileVisitResult.CONTINUE;
//            }
//            @Override
//            public FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException
//            {
//                if (e == null) {
//                    Files.delete(dir);
//                    return FileVisitResult.CONTINUE;
//                } else {
//                    // directory iteration failed
//                    throw e;
//                }
//            }
//        });
        File file = new File(start.toString());
        var  any  = Arrays.stream(file.listFiles()).toList();
        any.stream().forEach(one-> System.out.println(one.toPath()));
        System.out.println(start.getFileName());


    }
}
