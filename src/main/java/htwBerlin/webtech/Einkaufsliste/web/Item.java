package htwBerlin.webtech.Einkaufsliste.web;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="item")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;

}