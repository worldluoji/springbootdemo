package com.jpa.simplejpa.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.joda.money.Money;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="T_COFFEE")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
    private Money price;

    @Column(updatable = false)
    @CreationTimestamp
    private Date createTime;

    @Column
    @UpdateTimestamp
    private Date updateTime;
}
