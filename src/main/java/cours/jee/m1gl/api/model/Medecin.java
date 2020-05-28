package cours.jee.m1gl.api.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
public class Medecin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(length = 30)
    String matricule;

    @Column(length = 30)
    String nom;

    @Column
    int tel;

    @Temporal(TemporalType.DATE)
    Date dateNaiss;

    @Column(length = 50)
    String email;

    @Column(length = 50)
    String adress;

    @ManyToOne
    @JoinColumn(name = "service_id")
    Service service;

    @ManyToOne
    @JoinColumn(name = "specialite_id")
    Specialite specialite;

    public  Medecin(){
        service= new Service();
        specialite= new Specialite();
    }
}
