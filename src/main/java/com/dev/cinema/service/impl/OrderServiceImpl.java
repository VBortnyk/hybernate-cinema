package com.dev.cinema.service.impl;

import com.dev.cinema.dao.interfaces.OrderDao;
import com.dev.cinema.dao.interfaces.ShoppingCartDao;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.service.interfaces.OrderService;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public Order create(List<Ticket> tickets, User user) {
        Order order = new Order(tickets, user);
        ShoppingCart shoppingCart = shoppingCartDao.getByUser(user);
        order = orderDao.create(order);
        shoppingCart.getTickets().clear();
        shoppingCartDao.update(shoppingCart);
        return order;
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getOrderHistory(user);
    }
}
