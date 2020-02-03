package ludopia.objects.list.repository;

import ludopia.objects.list.GameList;
import ludopia.objects.list.OwnerType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ListRepository extends CrudRepository<GameList, String> {
    /**
     * Récupère la liste des listes de jeu d'un utilisateur our d'une association
     * @param idOwner l'id du proprétaire des potentielles listes
     * @param ownerType le type de propriétaire (étant donné que les associations et les utilisateurs peuvent potentiellement avoir le même ID)
     * @return un Iterable de liste de jeux
     */
    Iterable<GameList> findByIdOwnerAndOwnerType(int idOwner, OwnerType ownerType);

    /**
     * Récupère une liste par son ID
     * @param id l'id de la liste
     * @return un objet Optional contenant potentiellement un objet GameList
     */
    Optional<GameList> findById(int id);
}
