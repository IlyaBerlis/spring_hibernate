package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("Ilya", "Berlis", "Berlis@pochta.net");
        User user2 = new User("Arny", "Shwartz", "Shwartz@pochta.net");
        User user3 = new User("Christina", "Agilera", "Agilera@pochta.net");
        User user4 = new User("Someone", "Guy", "Guy@pochta.net");

        Car car1 = new Car("Volvo", 2468);
        Car car2 = new Car("Lada", 13579);
        Car car3 = new Car("Nissan", 8642);
        Car car4 = new Car("Chevrolet", 97531);

        userService.add(user1.setCar(car1).setUser(user1));
        userService.add(user2.setCar(car2).setUser(user2));
        userService.add(user3.setCar(car3).setUser(user3));
        userService.add(user4.setCar(car4).setUser(user4));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println(user.getCar());
            System.out.println();
        }

        User user = userService.getUserByCarModelAndSeries("Volvo", 2468);
        System.out.println("User by Car Model and Series:");
        System.out.println("Id = " + user.getId());
        System.out.println("First Name = " + user.getFirstName());
        System.out.println("Last Name = " + user.getLastName());
        System.out.println("Email = " + user.getEmail());

        context.close();
    }
}