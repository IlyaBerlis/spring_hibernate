package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.ResultSet;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("Volvo", 2468, null);
        Car car2 = new Car("Lada", 13579, null);
        Car car3 = new Car("Nissan", 8642, null);
        Car car4 = new Car("Chevrolet", 97531, null);

        User user1 = new User("Ilya", "Berlis", "Berlis@pochta.net", car1);
        User user2 = new User("Arny", "Shwartz", "Shwartz@pochta.net", car2);
        User user3 = new User("Christina", "Agilera", "Agilera@pochta.net", car3);
        User user4 = new User("Someone", "Guy", "Guy@pochta.net", car4);

        car1.setUser(user1);
        car2.setUser(user2);
        car3.setUser(user3);
        car4.setUser(user4);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println(user.getCar());
            System.out.println();
        }

        User user = userService.getUserByCarModelAndSeries("Nissan", 8642);
        System.out.println("User by Car Model and Series:");
        System.out.println("Id = " + user.getId());
        System.out.println("First Name = " + user.getFirstName());
        System.out.println("Last Name = " + user.getLastName());
        System.out.println("Email = " + user.getEmail());

        context.close();
    }
}