package com.chaonghong.data.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class WordCounter
{

    public static void main(String[] args)
    {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        File file = new File("e:/file");
        Folder folder = Folder.getDocumentAndChildForldFromFile(file);
        long l = forkJoinPool.invoke(new FolderSearchTask(folder, "apple"));
        System.out.println(l);
    }

    static String[] wordsIn(String line)
    {

        return line.trim().split("(\\s|\\p{Punct})+");
    }

    public static Long occurrencesCount(Document document, String searchedWord)
    {
        long count = 0;
        for (String line : document.getLines())
        {
            for (String word : wordsIn(line))
            {
                if (searchedWord.equals(word))
                {
                    count = count + 1;
                }
            }
        }
        return count;
    }

}

/**
 * 文档类
 * 
 * @author CSY
 *
 */
class Document
{
    List<String> lines;

    Document(List<String> lines)
    {
        this.lines = lines;
    }

    List<String> getLines()
    {
        return lines;
    }

    static Document getWordFromFile(File file)
    {

        List<String> lines = new ArrayList<String>();
        try (BufferedReader buff = new BufferedReader(new FileReader(file)))
        {
            String readLine = buff.readLine();
            while (null != readLine)
            {
                lines.add(readLine);
                readLine = buff.readLine();
            }
        }
        catch (Exception e)
        {
        }

        return new Document(lines);
    }
}

class Folder
{

    public List<Document> getDocuments()
    {
        return documents;
    }

    public List<Folder> getFolders()
    {
        return folders;
    }

    List<Document> documents;
    List<Folder> folders;

    public Folder(List<Document> documents, List<Folder> folders)
    {
        this.documents = documents;
        this.folders = folders;
    }

    static Folder getDocumentAndChildForldFromFile(File file)
    {
        List<Document> docs = new ArrayList<Document>();

        List<Folder> fs = new ArrayList<Folder>();

        try
        {
            if (file.isDirectory())
            {
                File[] listFiles = file.listFiles();
                for (File f : listFiles)
                {
                    if (f.isDirectory())
                    {
                        fs.add(getDocumentAndChildForldFromFile(f));
                    }
                    else if (f.isFile())
                    {
                        docs.add(Document.getWordFromFile(f));
                    }
                }
            }
            else
            {
                System.out.println("你傻逼啊，这个方法的参数需要传文件夹");
            }
        }
        catch (Exception e)
        {
        }

        return new Folder(docs, fs);
    }

}

class DocumentSearchTask extends RecursiveTask<Long>
{
    private final Document document;
    private final String searchedWord;

    DocumentSearchTask(Document document, String searchedWord)
    {
        super();
        this.document = document;
        this.searchedWord = searchedWord;
    }

    @Override
    protected Long compute()
    {
        return WordCounter.occurrencesCount(document, searchedWord);
    }
}

class FolderSearchTask extends RecursiveTask<Long>
{
    private final Folder folder;
    private final String searchedWord;

    FolderSearchTask(Folder folder, String searchedWord)
    {
        this.folder = folder;
        this.searchedWord = searchedWord;
    }

    @Override
    protected Long compute()
    {
        long count = 0L;
        List<RecursiveTask<Long>> forks = new LinkedList<>();
        for (Folder subFolder : folder.getFolders())
        {
            FolderSearchTask task = new FolderSearchTask(subFolder, searchedWord);
            forks.add(task);
            task.fork();
        }
        for (Document document : folder.getDocuments())
        {
            DocumentSearchTask task = new DocumentSearchTask(document, searchedWord);
            forks.add(task);
            task.fork();
        }
        for (RecursiveTask<Long> task : forks)
        {
            count = count + task.join();
        }
        return count;
    }
}
