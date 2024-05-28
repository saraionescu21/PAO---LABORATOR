import DAO.ProductDAO;
import DAO.UserDAO;
import models.User;
import runner.FoodDeliveryService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    static void start(FoodDeliveryService myFoodDelivery){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bine ai venit în administrarea noului tau FoodDelivery Restaurant!");
        System.out.println();
        System.out.println("Momentan restaurantul tau este gol, așa că te rugăm să adaugi cel puțin un produs pe care vrei să îl poți livra");
        System.out.println();
        System.out.println("Câte produse vrei să adaugi în meniul tău ?");

        int nrOfProducts = scanner.nextInt();
        scanner.nextLine();
        for(int i = 0; i < nrOfProducts; i++){
            myFoodDelivery.addProduct();
        }

        System.out.println("Super!");
        System.out.println("Hai să creăm și locații noi!");

        System.out.println("Câte locații vrei să adaugi pentru restaurantul tau ?");

        int nrOfLocations = scanner.nextInt();
        scanner.nextLine();
        for(int i = 0; i < nrOfLocations; i++){
            myFoodDelivery.addLocation();
        }

        System.out.println("Câți șoferi vrei să adaugi ? ");


        int nrOfDrivers = scanner.nextInt();
        scanner.nextLine();
        for(int i = 0; i < nrOfDrivers; i++){
            myFoodDelivery.addDriver();
        }

        System.out.println("Super, restaurantul tau a fost inițializat cu succes !");
    }

    static void menu(){
        System.out.println("Apasă:");
        System.out.println("0 - pentru a te opri");
        System.out.println("1 - adaugă produse");
        System.out.println("2 - adaugă șoferi");
        System.out.println("3 - adaugă locații");
        System.out.println("4 - afișează toate comenzile");
        System.out.println("5 - afișează toți șoferii");
        System.out.println("6 - afișează toate produsele");
        System.out.println("7 - afișează toți userii");
        System.out.println("8 - adaugă produse în locații");
        System.out.println("9 - șterge locație / produs / șofer");
        System.out.println("10 - adaugă comandă");
        System.out.println("11 - afișează pizza / paste / burger");
        System.out.println("12 - elimină șofer / produs / locație");
    }

    public static void main(String[] args) {
        FoodDeliveryService myFoodDelivery = new FoodDeliveryService();
        Scanner scanner = new Scanner(System.in);
        start(myFoodDelivery);

        boolean running = true;
        while (running) {
            menu();
            System.out.println("Alege o opțiune:");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    System.out.println("Ieșire din program...");
                    running = false;
                    break;
                case 1:
                    System.out.println("Adăugare produs:");
                    myFoodDelivery.addProduct();
                    break;
                case 2:
                    System.out.println("Adăugare șofer:");
                    myFoodDelivery.addDriver();
                    break;
                case 3:
                    System.out.println("Adăugare locație:");
                    myFoodDelivery.addLocation();
                    break;
                case 4:
                    System.out.println("Afișare comenzi:");
                    myFoodDelivery.displayOrders();
                    break;
                case 5:
                    System.out.println("Afișare șoferi:");
                    myFoodDelivery.displayDrivers();
                    break;
                case 6:
                    System.out.println("Afișare produse:");
                    myFoodDelivery.displayProducts();
                    break;
                case 7:
                    System.out.println("Afișare useri:");
                    myFoodDelivery.displayUsers();
                    break;
                case 8:
                    myFoodDelivery.addProductToLocation();
                    break;
                case 9:
                    System.out.println("Ce dorești să ștergi?");
                    System.out.println("1 - Locație");
                    System.out.println("2 - Produs");
                    System.out.println("3 - Șofer");
                    int deleteChoice = scanner.nextInt();
                    scanner.nextLine();
                    switch (deleteChoice) {
                        case 1:
                            myFoodDelivery.removeLocation();
                            break;
                        case 2:
                            myFoodDelivery.removeProduct();
                            break;
                        case 3:
                            myFoodDelivery.removeDriver();
                            break;
                        default:
                            System.out.println("Opțiune invalidă.");
                    }
                    break;
                case 10:
                    myFoodDelivery.addOrder();
                    break;
                case 11:
                    System.out.println("Ce dorești să afișezi?");
                    System.out.println("1 - Paste");
                    System.out.println("2 - Pizza");
                    System.out.println("3 - Burgeri");
                    int showChoice = scanner.nextInt();
                    scanner.nextLine();
                    switch (showChoice) {
                        case 1:
                            myFoodDelivery.displayPastas();
                            break;
                        case 2:
                            myFoodDelivery.displayPizzas();
                            break;
                        case 3:
                            myFoodDelivery.displayBurgers();
                            break;
                        default:
                            System.out.println("Opțiune invalidă.");
                    }
                    break;
                case 12:
                    System.out.println("Ce dorești să elimini?");
                    System.out.println("1 - șofer");
                    System.out.println("2 - produs");
                    System.out.println("3 - locație");
                    int showOption = scanner.nextInt();
                    scanner.nextLine();
                    switch (showOption) {
                        case 1:
                            myFoodDelivery.removeDriver();
                            break;
                        case 2:
                            myFoodDelivery.removeProduct();
                            break;
                        case 3:
                            myFoodDelivery.removeLocation();
                            break;
                        default:
                            System.out.println("Opțiune invalidă.");
                    }
                    break;
                default:
                    System.out.println("Opțiune invalidă. Te rog să alegi din nou.");
            }
        }
        scanner.close();
    }
}