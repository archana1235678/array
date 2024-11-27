import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Pizza {
    private String name;
    private double price;

    public Pizza(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class Menu {
    private List<Pizza> pizzas;

    public Menu() {
        pizzas = new ArrayList<>();
        loadPizzas();
    }

    private void loadPizzas() {
        pizzas.add(new Pizza("Margherita", 150));
        pizzas.add(new Pizza("Pepperoni", 200));
        pizzas.add(new Pizza("BBQ Chicken", 350));
        pizzas.add(new Pizza("Vegetarian", 100));
    }

    public void displayMenu() {
        System.out.println("Available Pizzas:");
        for (int i = 0; i < pizzas.size(); i++) {
            Pizza pizza = pizzas.get(i);
            System.out.printf("%d. %s - $%.2f%n", i + 1, pizza.getName(), pizza.getPrice());
        }
    }

    public Pizza getPizza(int index) {
        if (index < 0 || index >= pizzas.size()) {
            throw new IndexOutOfBoundsException("Invalid pizza index.");
        }
        return pizzas.get(index);
    }

    public int getPizzaCount() {
        return pizzas.size();
    }
}

class Order {
    private List<Pizza> orderedPizzas;

    public Order() {
        orderedPizzas = new ArrayList<>();
    }

    public void addPizza(Pizza pizza) {
        orderedPizzas.add(pizza);
    }

    public double calculateTotal() {
        double total = 0;
        for (Pizza pizza : orderedPizzas) {
            total += pizza.getPrice();
        }
        return total;
    }

    public void displayOrder() {
        System.out.println("Your Order:");
        for (Pizza pizza : orderedPizzas) {
            System.out.println("- " + pizza.getName() + ": $" + pizza.getPrice());
        }
        System.out.printf("Total: $%.2f%n", calculateTotal());
    }
}

public class PizzaShop {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Order order = new Order();
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                menu.displayMenu();
                System.out.print("Select a pizza (1-" + menu.getPizzaCount() + ") or 0 to finish: ");
                int choice = scanner.nextInt();

                if (choice == 0) {
                    break;
                }

                if (choice > 0 && choice <= menu.getPizzaCount()) {
                    Pizza selectedPizza = menu.getPizza(choice - 1);
                    order.addPizza(selectedPizza);
                    System.out.println(selectedPizza.getName() + " added to your order.");
                } else {
                    System.out.println("Invalid choice, please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            order.displayOrder();
            scanner.close();
        }
    }
}