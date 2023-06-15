package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Cristiano", "Ronaldo", "CR7@mail.ru", new Car("Ferrari", 5)));
      userService.add(new User("Lionel", "Messi", "LeoMessi@mail.ru", new Car("Lamborghini", 7)));
      userService.add(new User("Robert", "Lewandowski", "RL9@mail.ru", new Car("Bentley", 8)));
      userService.add(new User("Erling", "Holland", "Holland@mail.ru", new Car("RollsRoyce", 1)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      // пользователи с машинами
      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }

      // достать юзера, владеющего машиной по ее модели и серии
      System.out.println(userService.getUserByCar("Lamborghini", 7));


      context.close();
   }
}
