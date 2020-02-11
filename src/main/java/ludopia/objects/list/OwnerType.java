package ludopia.objects.list;

/**
 * Permet de différencier les utilisateurs des associations notamment dans les listes
 * (les utilisateurs et les associations pouvant avoir la même valeur d'ID car étant dans deux tables différentes dans la BDD)
 */
public enum OwnerType {
    USER,
    ASSO;
}
