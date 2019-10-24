package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {

    private static ArrayList<Album> albums = new ArrayList<Album>();

    public static void main(String[] args) {

        Album album = new Album("Stormbringer", "Deep Purple");
        album.addSong("Stormbringer", 4.6);
        album.addSong("Stormbringer 2", 4.22);
        album.addSong("Stormbringer 3", 4.3);
        album.addSong("Stormbringer 4", 5.6);
        album.addSong("Stormbringer 5", 3.21);
        album.addSong("Stormbringer 6", 6.23);
        album.addSong("Stormbringer 7", 4.27);
        album.addSong("Stormbringer 8", 4.2);
        album.addSong("Stormbringer 9", 3.13);

        albums.add(album);

        album = new Album("For those about to rock", "AC-DC");
        album.addSong("For those about to rock", 5.44);
        album.addSong("For those about to rock 2", 3.25);
        album.addSong("For those about to rock 3", 3.45);
        album.addSong("For those about to rock 4", 3.33);
        album.addSong("For those about to rock 5", 4.51);
        album.addSong("For those about to rock 6", 3.45);
        album.addSong("For those about to rock 7", 5.25);
        album.addSong("For those about to rock 8", 5.32);
        album.addSong("For those about to rock 9", 5.12);

        albums.add(album);

        LinkedList<Song> playList = new LinkedList<>();
        albums.get(0).addToPlaylist("Stormbringer", playList);
        albums.get(0).addToPlaylist("Stormbringer 9", playList);
        albums.get(0).addToPlaylist("blabla", playList);
        albums.get(1).addToPlaylist(2, playList);
        albums.get(1).addToPlaylist(8, playList);
        albums.get(1).addToPlaylist(82, playList);

        play(playList);
    }


    private static void play(LinkedList<Song> playList) {

        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;

        ListIterator<Song> listIterator = playList.listIterator();

        printMenu();

        if (playList.size() == 0) {
            System.out.println("No songs!");
            return;
        } else {
            System.out.println("Now playing: " + listIterator.next().toString());
        }

        while (!quit) {

            System.out.println("Choose option from menu: ");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0:
                    System.out.println("Playlist ended");
                    quit = true;
                    break;
                case 1:
                    if (!forward) {
                        if (listIterator.hasNext()) {
                            listIterator.next();
                        }
                        forward = true;
                    }
                    if (listIterator.hasNext()) {
                        System.out.println("Now playing: " + listIterator.next().toString());
                    } else {
                        System.out.println("End of playlist");
                        forward = false;
                    }
                    break;
                case 2:
                    if (forward) {
                        if (listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        forward = false;
                    }
                    if (listIterator.hasPrevious()) {
                        System.out.println("Now playing: " + listIterator.previous().toString());
                    } else {
                        System.out.println("Start of playlist");
                        forward = true;
                    }
                    break;
                case 3:
                    if (forward) {
                        if(listIterator.hasPrevious()) {
                            System.out.println("Now replaying: " + listIterator.previous().toString());
                            forward = false;
                        } else {
                            System.out.println("Start of the list...");
                        }
                    } else {
                        if(listIterator.hasNext()) {
                            System.out.println("Now replaying: " + listIterator.next().toString());
                            forward = true;
                        } else {
                            System.out.println("End of the list...");
                        }
                    }
                    break;
                case 4:
                    printList(playList);
                    break;
                case 5:
                    printMenu();
                    break;
                case 6:
                    if(playList.size()>0){
                        listIterator.remove();
                        if (listIterator.hasNext()) {
                            System.out.println("Now playing: " + listIterator.next().toString());
                        } else if (listIterator.hasPrevious()){
                            System.out.println("Now playing: " + listIterator.previous().toString());
                        }
                    }
                    break;

            }

        }


    }

    private static void printList(LinkedList<Song> playList) {
        System.out.println("\nPlaylist: ");

        for (Song song : playList) {
            System.out.println(song.toString());
        }
    }

    private static void printMenu() {
        System.out.println("\nMenu: \n" +
                "0 - quit\n" +
                "1 - next track\n" +
                "2 - previous track\n" +
                "3 - replay track\n" +
                "4 - print playlist\n" +
                "5 - print menu\n"+
                "6 - delete song\n");
    }


}
