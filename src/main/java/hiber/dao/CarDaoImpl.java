package hiber.dao;

import hiber.model.Car;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;
import java.util.List;

public class CarDaoImpl implements CarDao{
    private final SessionFactory sessionFactory;

    public CarDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public List<Car> listCars() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("FROM Car");
        return query.getResultList();
    }

    @Override
    public Car getCarById(Long id) {
        return sessionFactory.getCurrentSession().get(Car.class, id);
    }
}
