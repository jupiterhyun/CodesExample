package com.mycompany.forkjoinpool;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.HashSet;

import java.util.concurrent.ForkJoinPool;
import java.util.HashSet;

/**
 *
 * @author jupiter
 */
public class WebCrawler implements LinkHandler {

    private final Collection<String> visitedLinks = Collections.synchronizedSet(new HashSet<String>());
//    private final Collection<String> visitedLinks = Collections.synchronizedList(new ArrayList<String>());    
    private String url;
    private ExecutorService execService;
    private ForkJoinPool mainPool;
// java6    
//    public WebCrawler(String startingURL, int maxThreads) {
//        this.url = startingURL;
//        execService = Executors.newFixedThreadPool(maxThreads);
//    }
//
//    @Override
//    public void queueLink(String link) throws Exception {
//        startNewThread(link);
//    }
//
//    @Override
//    public int size() {
//        return visitedLinks.size();
//    }
//
//    @Override
//    public void addVisited(String s) {
//        visitedLinks.add(s);
//    }
//
//    @Override
//    public boolean visited(String s) {
//        return visitedLinks.contains(s);
//    }
//
//    private void startNewThread(String link) throws Exception {
//        execService.execute(new LinkFinder(link, this));
//    }
//
//    private void startCrawling() throws Exception {
//        startNewThread(this.url);
//    }
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) throws Exception {
//        new WebCrawler("http://www.javaworld.com", 64).startCrawling();
//    }
    //java7
    public WebCrawler(String startingURL, int maxThreads) {
        this.url = startingURL;
        mainPool = new ForkJoinPool(maxThreads);
    }

    private void startCrawling() {
        mainPool.invoke(new LinkFinderAction(this.url, this));
    }

    @Override
    //we don't need this, but just because I don't want to create a new class
    public void queueLink(String link) throws Exception{
    
    };

    
    @Override
    public int size() {
        return visitedLinks.size();
    }

    @Override
    public void addVisited(String s) {
        visitedLinks.add(s);
    }

    @Override
    public boolean visited(String s) {
        return visitedLinks.contains(s);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        new WebCrawler("http://www.javaworld.com", 64).startCrawling();
    }

}
