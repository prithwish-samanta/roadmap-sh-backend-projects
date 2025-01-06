package dev.prithwish.task_tracker.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.prithwish.task_tracker.demain.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileDBService {
    @Value("${task.json.file.path}")
    private String filePath;

    @Value("${task.json.file.name}")
    private String fileName;

    private final ObjectMapper objectMapper;

    public FileDBService() {
        this.objectMapper = new ObjectMapper();
        // Register JavaTimeModule to handle LocalDateTime
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.findAndRegisterModules();
    }

    public List<Task> readFile() throws IOException {
        File file = new File(filePath + File.separator + fileName);
        if (!file.exists()) {
            file.createNewFile();
            writeFile(new ArrayList<>());
        }
        return objectMapper.readValue(file, new TypeReference<List<Task>>() {
        });
    }

    public void writeFile(List<Task> tasks) throws IOException {
        objectMapper.writeValue(new File(filePath + File.separator + fileName), tasks);
    }
}
