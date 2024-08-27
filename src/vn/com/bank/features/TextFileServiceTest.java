package vn.com.bank.features;

import org.junit.After;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TextFileServiceTest {

    Path path1 = FileSystems.getDefault().getPath("test1.txt");
    Path path2 = FileSystems.getDefault().getPath("test2.txt");
    Path path3 = FileSystems.getDefault().getPath("test3.txt");

    @Test
    public void readFile_fileNotExisted() throws IOException {
        Files.deleteIfExists(path2);
        List<List<String>> list = TextFileService.readFile("test2.txt");
        assertTrue(list.isEmpty());
    }

    @Test
    public void readFile_fileNotValid() throws IOException {
        BankFunction.createsFile(path1);
        BufferedWriter writer = Files.newBufferedWriter(path1);
        writer.write("123456789123,Name1\n");
        writer.write("678901234567,Name2");
        writer.close();
        List<List<String>> list = TextFileService.readFile("test1.txt");
        assertEquals(2, list.size());
    }

    @Test
    public void readFile_fileValid() throws IOException {
        BankFunction.createsFile(path3);
        BufferedWriter writer = Files.newBufferedWriter(path3);
        writer.write("123456789123;Name1\n");
        writer.write("678901234567;Name2");
        writer.close();
        List<List<String>> list = TextFileService.readFile("test3.txt");
        assertTrue(list.isEmpty());
    }

    @After
    public void teardown() throws IOException {
        Files.deleteIfExists(path1);
        Files.deleteIfExists(path2);
        Files.deleteIfExists(path3);
    }
}