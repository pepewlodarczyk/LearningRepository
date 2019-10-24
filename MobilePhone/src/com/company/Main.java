package com.company;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static MobilePhone mobilePhone = new MobilePhone("(+48) 503-640-136");

    public static void main(String[] args) {

        boolean quit = false;
        printActions();

        while (!quit) {
            System.out.println("Enter action: (9 for menu)");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0:
                    System.out.println("Shutting down...");
                    quit = true;
                    break;
                case 1:
                    mobilePhone.printContacts();
                    break;
                case 2:
                    addNewContact();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    removeContact();
                    break;
                case 5:
                    queryContact();
                    break;
                case 9:
                    printActions();
                    break;

            }

        }

    }

    private static void addNewContact() {
        System.out.println("Enter new contact name: ");
        String name = scanner.nextLine();



        System.out.println("Enter phone number: ");
        String number = scanner.nextLine();

        Contact newContact = new Contact(name, number);
        if (mobilePhone.addNewContact(newContact)) {
            System.out.println(name + ": " + number + " contact created");
        } else {
            System.out.println("Cannot add, " + name + " is already in phonebook");
        }
    }

    private static void updateContact() {
        System.out.println("Enter contact name you want to change: ");
        String oldName = scanner.nextLine();
        Contact existingContact = mobilePhone.queryContact(oldName);

        if (existingContact == null) {
            System.out.println("Contact not found");
            return;
        }
        System.out.println("Enter new name: ");
        String newName = scanner.nextLine();

        if (mobilePhone.queryContact(newName) != null) {
            System.out.println("chuj kurwa nie wolno kontakt " + newName + " już istnieje!");
            return;
        }

        System.out.println("Enter new number: ");
        String newNumber = scanner.nextLine();

        mobilePhone.updateContact(existingContact, new Contact(newName, newNumber));

    }

    private static void removeContact() {
        System.out.println("Enter contact you want to remove: ");
        String name = scanner.nextLine();

        if (mobilePhone.queryContact(name) == null) {
            System.out.println("Contact not found");
            return;
        }

        mobilePhone.removeContact(mobilePhone.queryContact(name));

    }

    private static void queryContact() {
        System.out.println("Enter contact you want to search for: ");
        String name = scanner.nextLine();

        if (mobilePhone.queryContact(name) == null) {
            System.out.println("Contact not found");
            return;
        }

        System.out.println(mobilePhone.queryContact(name).getName() + " phone number is " +
                mobilePhone.queryContact(name).getNumber());

    }

    private static void printActions() {
        System.out.println("Available options, press: \n");
        System.out.println("0 - to shutdown\n" +
                "1 - to print contacts\n" +
                "2 - to add contact\n" +
                "3 - to update contact\n" +
                "4 - to remove contact\n" +
                "5 - to check if exists\n" +
                "9 - to print menu");
    }


}
