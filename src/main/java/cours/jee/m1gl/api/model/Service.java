package cours.jee.m1gl.api.model;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(length = 30)
    String libelle;

    @OneToMany(mappedBy = "service")
    List<Medecin> medecins;

    @ManyToMany(mappedBy = "services")
    List<Specialite> specialites;

    public Service(){
        medecins= new ArrayList<>();
        specialites=new ArrayList<>();
    }
}
