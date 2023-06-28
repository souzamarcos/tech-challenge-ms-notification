package com.fiap.burger.persistence.order.model;

import com.fiap.burger.persistence.misc.common.BaseDomainJPA;
import com.fiap.burger.persistence.product.model.ProductJPA;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.List;

//@Entity(name = "orderItem")
//public class OrderModelItemJPA extends BaseDomainJPA {
//
//
//    @Column()
//    ProductJPA productId;
//    @Column()
//    List<Long> additionalIds;
//    @Column()
//    String comment;
//
//    public OrderModelItemJPA() {
//
//    }
//
//    public OrderModelItemJPA(
//            Long id,
//            ProductJPA productId,
//            List<Long> additionalIds,
//            String comment
//    )
//    {
//        this.id = id;
//        this.productId = productId;
//        this.additionalIds = additionalIds;
//        this.comment = comment;
//    }
//}
