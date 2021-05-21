package com.alethio.service.product.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Entity
@DiscriminatorValue("cloth")
public class Cloth extends Item{

    private Long id;

}
