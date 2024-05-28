package runner;

import models.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
public class FoodDeliveryService {

    protected List<Order> orders = new ArrayList<>();
    protected List<Driver> drivers = new ArrayList<>();
    protected List<Product> products = new ArrayList<>();
    protected List<User> users = new ArrayList<>();
    protected List<Location> locations = new ArrayList<>();

    private Map<String, User> userMap = new HashMap<>();

    protected List<Burger> burgers = new ArrayList<>();

    protected List<Pizza> pizzas = new ArrayList<>();

    protected List<Pasta> pastas = new ArrayList<>();

    private Scanner scanner = new Scanner(System.in);

    public void writeActionToCSV(String action) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("actions.csv", true))) {
            // Se obține un ștampila de timp curentă
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            // Se scrie acțiunea și timestamp-ul în fișierul CSV
            writer.println(action + "," + timestamp);
//            System.out.println("Acțiunea '" + action + "' a fost scrisă în fișierul CSV.");
        } catch (IOException e) {
            System.out.println("Eroare la scrierea în fișierul CSV: " + e.getMessage());
        }
    }

    public void addDriver() {
        Driver d = new Driver();
        System.out.println("Introdu prenumele șoferului:");
        d.setFirstName(scanner.nextLine());
        System.out.println("Introdu numele șoferului:");
        d.setLastName(scanner.nextLine());
        System.out.println("Introdu salariul:");
        d.setSalary(scanner.nextDouble());
        scanner.nextLine();
        System.out.println("Introdu orașul:");

        String newCity = "";
        boolean ok = false;
        while (!ok) {
            newCity = scanner.nextLine();
            if(Objects.equals(newCity, "?")){
                ok = false;
                break;
            }
            for (Location l : locations) {
                if (newCity.equals(l.getCity())) {
                    ok = true;
                    d.setLocation(l);
                    break;
                }
            }
            if (!ok) {
                System.out.println("Locația introdusă nu este validă, încearcă din nou:");
                System.out.println("Dacă vrei să ieși, apasă ?");

            }
        }

        if(ok) drivers.add(d);

        writeActionToCSV("addDriver");

    }

//    public void addDriver(Driver d){
//
//        drivers.add(d);
//    }
//
//    public void addProduct(Product product) {
//        products.add(product);
//    }
//
//    public void sortProductsByPrice() {
//        Collections.sort(products, (product1, product2) -> Double.compare(product1.getPrice(), product2.getPrice()));
//    }

    public void addProduct() {
        System.out.println("Pentru produs normal - 0");
        System.out.println("Pentru paste - 1");
        System.out.println("Pentru pizza - 2");
        System.out.println("Pentru burger - 3");
        System.out.println("Introdu comanda ta:");
        int x = scanner.nextInt();
        scanner.nextLine();

        if (x == 1) {
            Pizza p = new Pizza();
            System.out.println("Introdu numele produsului:");
            p.setName(scanner.nextLine());
            System.out.println("Introdu prețul:");
            p.setPrice(scanner.nextInt());
            scanner.nextLine();
            products.add(p);
            pizzas.add(p);

        } else if (x == 2) {
            Burger b = new Burger();

            System.out.println("Introdu numele produsului:");
            b.setName(scanner.nextLine());
            System.out.println("Introdu prețul:");
            b.setPrice(scanner.nextInt());
            scanner.nextLine();
            products.add(b);
            burgers.add(b);

        } else if (x == 3) {
            Pasta p = new Pasta();

            System.out.println("Introdu numele produsului:");
            p.setName(scanner.nextLine());
            System.out.println("Introdu prețul:");
            p.setPrice(scanner.nextInt());
            scanner.nextLine();
            products.add(p);
            pastas.add(p);

        } else if(x == 0){
            Product p = new Product();
            System.out.println("Introdu numele produsului:");
            p.setName(scanner.nextLine());
            System.out.println("Introdu prețul:");
            p.setPrice(scanner.nextInt());
            scanner.nextLine();
            products.add(p);
        }

        writeActionToCSV("addProduct");
    }

//    public void addUser(User user) {
//        users.add(user);
//    }
//
//    public void addUser() {
//        User u = new User();
//        System.out.println("Introdu prenumele utilizatorului:");
//        u.setFirstName(scanner.nextLine());
//        System.out.println("Introdu numele utilizatorului:");
//        u.setLastName(scanner.nextLine());
//        users.add(u);
//    }

    public void addOrder() {
        Order o = new Order();
        User u = new User();
        System.out.println("Introdu prenumele utilizatorului:");
        u.setFirstName(scanner.nextLine());
        System.out.println("Introdu numele utilizatorului:");
        u.setLastName(scanner.nextLine());
        System.out.println("Introdu numărul de telefon:");
        u.setPhoneNumber(scanner.nextLine());

        if (userMap.containsKey(u.getPhoneNumber())) {
            u = userMap.get(u.getPhoneNumber());
        } else {
            userMap.put(u.getPhoneNumber(), u);
            users.add(u);
        }

        o.setUser(u);
        System.out.println("Câte produse are comanda?");
        int nrOfProducts = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < nrOfProducts; i++) {
            System.out.println("Selectează un produs pentru comandă:");
            for (int j = 0; j < products.size(); j++) {
                System.out.println((j + 1) + " - " + products.get(j).getName() + " la prețul de: " + products.get(j).getPrice() + " lei");
            }
            System.out.println("Introdu numărul produsului:");
            int productChoice = scanner.nextInt() - 1;
            scanner.nextLine();

            if (productChoice >= 0 && productChoice < products.size()) {
                Product selectedProduct = products.get(productChoice);
                o.addProduct(selectedProduct);
            } else {
                System.out.println("Selecție invalidă.");
                i--;
            }
        }

        System.out.println("La ce locație vrei să fie comanda?");

        System.out.println("Selectează o locație pentru comandă:");
        for (int j = 0; j < locations.size(); j++) {
            System.out.println((j + 1) + " - " + locations.get(j).getCity());
        }
        System.out.println("Introdu numărul locației:");
        int locationChoice = scanner.nextInt() - 1;

        if (locationChoice >= 0 && locationChoice < products.size())
        {
            Location selectedLocation = locations.get(locationChoice);
            o.setLocation(selectedLocation);
        } else {
            System.out.println("Selecție invalidă.");
        }

        orders.add(o);

        o.updateTotalPrice();
        System.out.println("Prețul total este: " + o.getTotalPrice());
        System.out.println("Comanda a fost adăugată cu succes.");

        writeActionToCSV("addOrder");
    }

//    public void addLocation(Location l){
//        locations.add(l);
//    }

    public void addLocation(){
        Location l = new Location();
        System.out.println("Introdu orașul:");
        l.setCity(scanner.nextLine());
        locations.add(l);
        writeActionToCSV("addLocation");
    }

    public void addProductToLocation() {
        System.out.println("Alege un produs:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println(i + 1 + ". " + products.get(i).getName());
        }
        System.out.println("Introdu numărul produsului:");
        int productIndex = scanner.nextInt() - 1; // Ajustare pentru indexare de la 0
        scanner.nextLine();

        if (productIndex < 0 || productIndex >= products.size()) {
            System.out.println("Selecție invalidă.");
            return;
        }

        Product selectedProduct = products.get(productIndex);

        System.out.println("Alege o locație:");
        for (int i = 0; i < locations.size(); i++) {
            System.out.println(i + 1 + ". " + locations.get(i).getCity());
        }
        System.out.println("Introdu numărul locației:");
        int locationIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (locationIndex < 0 || locationIndex >= locations.size()) {
            System.out.println("Selecție invalidă.");
            return;
        }

        Location selectedLocation = locations.get(locationIndex);

        selectedLocation.addProduct(selectedProduct);
        System.out.println("Produsul '" + selectedProduct.getName() + "' a fost adăugat la locația din " + selectedLocation.getCity() + ".");

        writeActionToCSV("addProducttoLocation");
    }

//    public void addProductToLocation(Location givenLocation) {
//        if (givenLocation == null) {
//            System.out.println("Locația dată este null.");
//            return;
//        }
//
//        System.out.println("Alege un produs pentru locația din " + givenLocation.getCity() + ":");
//        for (int i = 0; i < products.size(); i++) {
//            System.out.println(i + 1 + ". " + products.get(i).getName());
//        }
//        System.out.println("Introdu numărul produsului:");
//        int productIndex = scanner.nextInt() - 1;
//        scanner.nextLine();
//
//        if (productIndex < 0 || productIndex >= products.size()) {
//            System.out.println("Selecție invalidă.");
//            return;
//        }
//
//        Product selectedProduct = products.get(productIndex);
//
//        // Adăugarea produsului selectat la locația dată
//        givenLocation.addProduct(selectedProduct);
//        System.out.println("Produsul '" + selectedProduct.getName() + "' a fost adăugat la locația din " + givenLocation.getCity() + ".");
//    }


    public void removeDriver() {
        if (drivers.isEmpty()) {
            System.out.println("Nu există șoferi de șters.");
            return;
        }
        System.out.println("Selectează șoferul pe care vrei să îl ștergi:");
        for (int i = 0; i < drivers.size(); i++) {
            System.out.println((i + 1) + " - " + drivers.get(i).getFirstName() + drivers.get(i).getLastName());
        }
        int choice = scanner.nextInt() - 1;
        scanner.nextLine();

        if (choice >= 0 && choice < drivers.size()) {
            drivers.remove(choice);
            System.out.println("Șoferul a fost șters.");
        } else {
            System.out.println("Selecție invalidă.");
        }
        writeActionToCSV("removeDriver");
    }

    public void removeLocation() {
        if (locations.isEmpty()) {
            System.out.println("Nu există locații de șters.");
            return;
        }
        System.out.println("Selectează locația pe care vrei să o ștergi:");
        for (int i = 0; i < locations.size(); i++) {
            System.out.println((i + 1) + " - " + locations.get(i).getCity());
        }
        int choice = scanner.nextInt() - 1;
        scanner.nextLine(); // Consumă newline
        if (choice >= 0 && choice < locations.size()) {
            locations.remove(choice);
            System.out.println("Locația a fost ștearsă.");
        } else {
            System.out.println("Selecție invalidă.");
        }

        writeActionToCSV("removeLocation");
    }

    public void removeProduct() {
        if (products.isEmpty()) {
            System.out.println("Nu există produse de șters.");
            return;
        }
        System.out.println("Selectează produsul pe care vrei să îl ștergi:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + " - " + products.get(i).getName());
        }
        int choice = scanner.nextInt() - 1;
        scanner.nextLine();
        if (choice >= 0 && choice < products.size()) {
            products.remove(choice);
            System.out.println("Produsul a fost șters.");
        } else {
            System.out.println("Selecție invalidă.");
        }

        writeActionToCSV("removeProduct");
    }

    public void displayOrders() {
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getId());
            for(Product p: order.getProducts()){
                System.out.println(p.getName());
            }
            System.out.println(order.getTotalPrice());
            System.out.println(order.getLocation());
            System.out.println();
        }
        writeActionToCSV("displayOrders");
    }

    public void displayDrivers() {
        for (Driver driver : drivers) {
            System.out.println("Driver: " + driver.getFirstName() + " " + driver.getLastName() + " " + driver.getSalary() + " " + driver.getLocation().getCity());
        }

        writeActionToCSV("displayDrivers");
    }

    public void displayProducts() {
        System.out.println("Displaying " + products.size() + " products."); // Debug print
        for (Product product : products) {
            System.out.println("Product Name: " + product.getName());
        }

        writeActionToCSV("displayProducts");
    }


    public void displayUsers() {
        for (User user : users) {
            System.out.println("User Name: " + user.getFirstName() + " " + user.getLastName());
        }

        writeActionToCSV("displayUsers");
    }

    public void displayPizzas(){
        for(Pizza p : pizzas){
            System.out.println("Pizza name:" + p.getName());
        }
        writeActionToCSV("displayPizzas");
    }

    public void displayBurgers(){
        for(Burger b : burgers){
            System.out.println("Burger name:" + b.getName());
        }

        writeActionToCSV("displayBurgers");
    }

    public void displayPastas(){
        for(Pasta p : pastas){
            System.out.println("Pasta name:" + p.getName());
        }
        writeActionToCSV("displayPastas");
    }

//    public void displayLocations(){
//        for(Location l: locations){
//            System.out.println("Location in: " + l.getCity());
//            System.out.println("Has the following products:");
//            for(Product p: l.getProductsAvailable()){
//                System.out.println(p.getName());
//            }
//        }
//    }
}
