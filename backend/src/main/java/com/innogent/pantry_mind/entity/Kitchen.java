package com.innogent.pantry_mind.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> 6bd847bd126260b6bd160a5f6fc8318ae04d4487

@Entity
@Table(name = "kitchens")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kitchen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
<<<<<<< HEAD

    @OneToMany(mappedBy = "kitchen", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users;
=======
>>>>>>> 6bd847bd126260b6bd160a5f6fc8318ae04d4487
}
