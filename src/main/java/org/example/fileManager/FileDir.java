package org.example.fileManager;

import java.nio.file.Path;
import java.util.Map;

public class FileDir {

    private Map<Path, String> pathMap;
    private Path dir;

    public FileDir(Map<Path, String> pathMap, Path dir) {
        this.pathMap = pathMap;
        this.dir = dir;
    }

    public Map<Path, String> getPathMap() {
        return pathMap;
    }

    public FileDir setPathMap(Map<Path, String> pathMap) {
        this.pathMap = pathMap;
        return this;
    }

    public Path getDir() {
        return dir;
    }

    public FileDir setDir(Path dir) {
        this.dir = dir;
        return this;
    }
}
