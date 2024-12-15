package com.argus.cms.orderManagement.services;


import com.argus.cms.canteenManagement.entities.Canteen;
import com.argus.cms.canteenManagement.services.CanteenService;
import com.argus.cms.exceptions.RecordNotFoundException;
import com.argus.cms.orderManagement.entities.Order;
import com.argus.cms.orderManagement.entities.OrderFoodCommon;
import com.argus.cms.orderManagement.entities.OrderItems;
import com.argus.cms.orderManagement.entities.Transactions;
import com.argus.cms.orderManagement.repositories.OrderRepository;
import com.argus.cms.userManagement.users.entities.Users;
import com.argus.cms.userManagement.users.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private UserService userService;
    private CanteenService canteenService;


    @Override
    public Order createOrder(Order order) throws RecordNotFoundException {

        Users loggedInUser = userService.getCurrentUser();
        order.setOrderer(loggedInUser);

        double amountPerUser = 0d;
        double totalAmount = 0d;

        List<OrderItems> orderItems = order.getOrderItems();
        for (OrderItems item : orderItems) {
            amountPerUser = 0d;

            List<OrderFoodCommon> orderFoodCommons = item.getOrderedFoodItems();
            for (OrderFoodCommon foodOrders : orderFoodCommons) {
                double foodPrice = foodOrders.getFoodItem().getPrice();
                amountPerUser += foodPrice * foodOrders.getQuantity();
            }
            item.setAmount(amountPerUser);
            totalAmount += amountPerUser;

            Transactions transactions = new Transactions();
            transactions.setToEmployee(order.getOrderer());
            transactions.setFromEmployee(item.getReceiver());
            transactions.setAmount(amountPerUser);
            item.setTransaction(transactions);
        }
        order.setOrderAmount(totalAmount);

        Transactions transactions = new Transactions();
//        transactions.setToEmployee();
        transactions.setFromEmployee(order.getOrderer());

        return orderRepository.save(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Order findOrderById(Long orderId) throws RecordNotFoundException {
        return orderRepository.findById(orderId).orElseThrow(() -> new RecordNotFoundException("No Order found with id given!"));
    }

    @Override
    @Transactional
    public Order cancelOrderById(Long orderId) throws RecordNotFoundException {
        Order order = this.findOrderById(orderId);
        order.setIsCancelled(true);
        return order;
    }

    @Override
    public List<Order> viewALlOrdersOfCanteen() throws RecordNotFoundException {
        Users user = userService.getCurrentUser();

        Canteen canteen = canteenService.findByUser(user);

        return null;
    }
}