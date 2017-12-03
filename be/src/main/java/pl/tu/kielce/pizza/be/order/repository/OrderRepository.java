package pl.tu.kielce.pizza.be.order.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.tu.kielce.pizza.be.order.model.jpa.Order;
import pl.tu.kielce.pizza.common.common.enums.OrderStatus;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>{



    List<Order> findAllByOrderStatus(OrderStatus orderStatus);

    @Modifying
    @Query("update Order set orderStatus = pl.tu.kielce.pizza.common.common.enums.OrderStatus.IN_PROGRESS where id = :orderId")
    void  setOrderAsAccepted(@Param("orderId") Long orderId);

    @Modifying
    @Query("update Order set orderStatus = pl.tu.kielce.pizza.common.common.enums.OrderStatus.DURING_DELIVERY where id = :orderId")
    void  setOrderAsDuringDelivery(@Param("orderId") Long orderId);

    @Modifying
    @Query("update Order set orderStatus = pl.tu.kielce.pizza.common.common.enums.OrderStatus.DONE where id = :orderId")
    void  setOrderAsDone(@Param("orderId") Long orderId);

    @Query("select o from Order o where o.orderStatus = pl.tu.kielce.pizza.common.common.enums.OrderStatus.WAITING_FOR_APPROVAL and o.department.id = :departmentId ")// or o.user.department.id = :departmentId)")
    List<Order> findAllWaitingForApprovalInGivenDepartment(@Param("departmentId") Long departmentId);

    @Query("select o from Order o where o.orderStatus = pl.tu.kielce.pizza.common.common.enums.OrderStatus.IN_PROGRESS and o.department.id = :departmentId ")// or o.user.department.id = :departmentId)")
    List<Order> findAllInProgress(@Param("departmentId") Long departmentId);

    @Query("select o from Order o where o.orderStatus = pl.tu.kielce.pizza.common.common.enums.OrderStatus.DURING_DELIVERY and o.department.id = :departmentId ")// or o.user.department.id = :departmentId)")
    List<Order> findAllDuringDelivery(@Param("departmentId") Long departmentId);
}
