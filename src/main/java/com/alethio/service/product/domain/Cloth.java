package com.alethio.service.product.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Entity
@Table(name="Cloths")
@NoArgsConstructor
@DiscriminatorValue("cloth")
public class Cloth extends Item {
}
